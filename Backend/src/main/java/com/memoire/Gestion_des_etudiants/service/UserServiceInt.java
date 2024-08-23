package com.memoire.Gestion_des_etudiants.service;

import com.memoire.Gestion_des_etudiants.entites.bref.UserNonDetaille;
import org.apache.catalina.LifecycleState;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public interface UserServiceInt {
    ResponseEntity<String> signUp(Map<String, String> requestMap);

    public ResponseEntity<Map<String, String>> login(Map<String, String> requestMap);

    public ResponseEntity<List<UserNonDetaille>> getAllUser();

    ResponseEntity<String> checkToken();
}
