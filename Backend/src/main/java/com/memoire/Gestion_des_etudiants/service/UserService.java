package com.memoire.Gestion_des_etudiants.service;

import com.memoire.Gestion_des_etudiants.entites.User;
import com.memoire.Gestion_des_etudiants.entites.bref.UserNonDetaille;
import com.memoire.Gestion_des_etudiants.exception.ErrorDetails;
import com.memoire.Gestion_des_etudiants.DAO.UserRepository;
import com.memoire.Gestion_des_etudiants.configuration.serviceB.CustomerUserDetailService;
import com.memoire.Gestion_des_etudiants.configuration.serviceB.JwFilter;
import com.memoire.Gestion_des_etudiants.configuration.serviceB.JwUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

//for the login purpose
@Service
public class UserService implements UserServiceInt{
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    CustomerUserDetailService customerUserDetailService;
    @Autowired
    JwUtil jwUtil;
    @Autowired
    JwFilter jwFilter;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("sugnUp info{}",requestMap);
        try {
            if (signUpValidation(requestMap)) {
               Optional<User> user = userRepository.findByEmailId(requestMap.get("email"));
                System.out.println("UserService :"+user);
                if (!user.isPresent()) {

                    userRepository.save(getUserFromMap(requestMap));

                    //ceci n'est pas une erreur
                    return ErrorDetails.getResponseEntity("Enregistré avec succès.", HttpStatus.OK);

                } else {
                    return ErrorDetails.getResponseEntity("email déjà existe.", HttpStatus.BAD_REQUEST);
                }
           } else {
                return ErrorDetails.getResponseEntity("Non valide", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ErrorDetails.getResponseEntity("Il y a une erreur.",HttpStatus.INTERNAL_SERVER_ERROR);
    }

        @Override
        public ResponseEntity<Map<String, String>> login(Map<String, String> requestMap) {
            Map<String, String> response = new HashMap<>();

            try {
                String email = requestMap.get("email");
                String password = requestMap.get("password");

                if (email == null || password == null) {
                    response.put("message", "Email or password missing");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }


                Authentication auth = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(email, password)
                );


                if (auth.isAuthenticated()) {
                    System.out.println("lancé ito");
                    String userStatus = customerUserDetailService.getUserDetail().get().getStatus();

                    if ("true".equalsIgnoreCase(userStatus)) {

                        String token = jwUtil.generateToken(email, customerUserDetailService.getUserDetail().get().getRole());
                        response.put("token", token);
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    } else {
                        response.put("message", "Attendez l'administrateur");
                        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                    }
                }
            } catch (BadCredentialsException ex) {
                response.put("message", "Email ou Mot de passe invalide");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } catch (Exception ex) {
                response.put("message", "Verifieo le email sy mot de passe fa tsy norm ah" +
                        "");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.put("message", "Email ou mot de passe non valide");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    @Override
    public ResponseEntity<List<UserNonDetaille>> getAllUser() {
        try{
            if(jwFilter.isAdmin()){
                return new ResponseEntity<>(userRepository.loadUserNonDetaille(),HttpStatus.OK);

            }else{
                return new ResponseEntity<>(new ArrayList<>(),HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception ex){
            log.info("not found");
            ex.printStackTrace();
        }
        log.info("not found2");
        return  new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> checkToken() {
        return ErrorDetails.getResponseEntity("true",HttpStatus.OK);
    }

    //
    private boolean signUpValidation(Map<String,String>requestMap) {
        if (requestMap.containsKey("name") && requestMap.containsKey("tel") && requestMap.containsKey("email") && requestMap.containsKey("password")) {
            return true;
        } else {
            return false;
        }
    }
    private User getUserFromMap(Map<String,String>requestMap){
        User user=new User();
        user.setName(requestMap.get("name"));
        user.setTel(requestMap.get("tel"));
        user.setPassword(requestMap.get("password"));
        user.setEmail(requestMap.get("email"));
        user.setRole(requestMap.get("false"));
        user.setStatus(requestMap.get("user"));

        return user;
    }
}
