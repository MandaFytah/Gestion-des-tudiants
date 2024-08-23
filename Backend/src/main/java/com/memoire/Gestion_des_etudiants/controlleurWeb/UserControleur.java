package com.memoire.Gestion_des_etudiants.controlleurWeb;

import com.memoire.Gestion_des_etudiants.entites.bref.UserNonDetaille;
import com.memoire.Gestion_des_etudiants.exception.ErrorDetails;
import com.memoire.Gestion_des_etudiants.service.UserServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 @Controller
public class UserControleur implements UserControleurInt {
    @Autowired
    UserServiceInt userService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            return userService.signUp(requestMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ErrorDetails.getResponseEntity("Il y a une erreur", HttpStatus.INTERNAL_SERVER_ERROR);
    }
         @PostMapping("/login")
         public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> requestMap) {
             try {
                 return userService.login(requestMap);
             } catch (Exception ex) {
                 ex.printStackTrace();
             }
             Map<String, String> response = new HashMap<>();
             response.put("message", "Il y a une erreur");
             return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
         }

     @Override
     public ResponseEntity<List<UserNonDetaille>> getAll() {
         try{
             return userService.getAllUser();

         }catch (Exception ex){
             ex.printStackTrace();
         }
         return new ResponseEntity<List<UserNonDetaille>>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
     }

     @Override
     public ResponseEntity<String> checkToken() {
         try{
            return userService.checkToken();
         }catch (Exception ex){
            ex.printStackTrace();
         }
         return ErrorDetails.getResponseEntity("SOMETHING_WENT_WRONG",HttpStatus.INTERNAL_SERVER_ERROR);
     }


 }
