package com.memoire.Gestion_des_etudiants.configuration.serviceB;

import com.memoire.Gestion_des_etudiants.DAO.EtudiantRepository;
import com.memoire.Gestion_des_etudiants.DAO.UserRepository;
import com.memoire.Gestion_des_etudiants.entites.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;


@Service
public class CustomerUserDetailService implements UserDetailsService {
    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    UserRepository userRepository;
    private static final Logger log= LoggerFactory.getLogger(CustomerUserDetailService.class);
    private Optional<User> userDetail;


    @Override
    public  UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        //Optional<User> user=userRepository.findByEmailId(emailId);
        userDetail= userRepository.findByEmailId(emailId);

        if(userDetail !=null){
            log.info("User Found: {}",userDetail.get().getEmail());
            return  new User(userDetail.get().getEmail(),userDetail.get().getPassword(),userDetail.get().getRole(),new ArrayList<>());
        } else{
                log.warn("user not found with emailId: {}", emailId);
                throw new UsernameNotFoundException("user not found.");
        }





    }
    public Collection<? extends GrantedAuthority> getAutorities(String role){
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
    public Optional<User> getUserDetail(){

        return userDetail;

    }
}