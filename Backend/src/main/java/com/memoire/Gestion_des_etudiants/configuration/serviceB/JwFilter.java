package com.memoire.Gestion_des_etudiants.configuration.serviceB;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwFilter extends OncePerRequestFilter {

    @Autowired
    private JwUtil jwUtil;

    @Autowired
    private CustomerUserDetailService service;

    private String userName = null;
    private Claims claims = null;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String path = httpServletRequest.getServletPath();
        // Exclude login paths for both users and students
        if (path.matches("/gestionEtudiant/user/login|/gestionEtudiant/user/signUp|/gestionEtudiant/user/forgotPassword|/gestionEtudiant/etudiant/login")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            String authorizationHeader = httpServletRequest.getHeader("Authorization");
            String token = null;

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
                token = authorizationHeader.substring(7);
                userName = jwUtil.extractUserName(token);
                claims = jwUtil.extractallClaims(token);
                System.out.println("extracted claim: " + claims);
            }

            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = service.loadUserByUsername(userName);
                System.out.println("jwtFilter if username not null sns " + userDetails);

                if (jwUtil.validateToken(token, userName)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(httpServletRequest)
                    );
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    System.out.println("Authenticated user: " + userName + " with role: " + userDetails.getAuthorities());
                } else {
                    System.out.println("token validation failed for user: " + userName);
                }
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    public boolean isAdmin() {
        return "admin".equalsIgnoreCase((String) claims.get("role"));
    }

    public boolean isUser() {
        return "user".equalsIgnoreCase((String) claims.get("role"));
    }

    public String getCurrentUser() {
        return userName;
    }
}