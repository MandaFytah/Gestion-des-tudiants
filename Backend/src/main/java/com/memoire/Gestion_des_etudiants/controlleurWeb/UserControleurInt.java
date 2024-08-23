package com.memoire.Gestion_des_etudiants.controlleurWeb;

import com.memoire.Gestion_des_etudiants.entites.bref.UserNonDetaille;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@RequestMapping("/gestionEtudiant/user")
public interface UserControleurInt {
    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody(required = true)Map<String,String> requestMap);
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> requestMap);
    @GetMapping("/all")
    public ResponseEntity<List<UserNonDetaille>> getAll();
    @GetMapping("/checktoken")
    ResponseEntity<String> checkToken();

}
