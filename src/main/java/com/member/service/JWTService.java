package com.member.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JWTService {

    String generateToken(UserDetails userDetails);
   String extractUserName(String token);
    boolean isTokenValid(String token,UserDetails userDetails);
    String generateRefreshToken(Map<String,Object> exactClaims, UserDetails userDetails);
}
