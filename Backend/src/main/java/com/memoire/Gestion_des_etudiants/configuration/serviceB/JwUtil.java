package com.memoire.Gestion_des_etudiants.configuration.serviceB;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwUtil {
    private String secret= "tiavina";
    public String extractUserName(String token){
        return extractClaims(token,Claims::getSubject);
    }
    public <T> T extractClaims(String token, Function<Claims,T> claimsResolver){
    final Claims claims= extractallClaims(token);
    return claimsResolver.apply(claims);

    }
    public String generateToken(String username,String role){
        Map<String,Object> claims=new HashMap<>();
        claims.put("role",role);
        return  createToken(claims,username);
    }
    private String createToken(Map<String, Object> claims,String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256,secret).compact();
    }
    public Date extractExpiration(String token){
        return extractClaims(token,Claims::getExpiration);
    }
    private Boolean estExpire(String token){
        return extractExpiration(token).before(new Date());
    }
    public Boolean validateToken(String token, String email){
        final String username= extractUserName(token);
        System.out.println("hahaha"+username+email);

        return (username.equals(email) && !estExpire(token));
    }
    public Claims extractallClaims(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
}
