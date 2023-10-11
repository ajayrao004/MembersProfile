package com.member.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    public String token;
    private String refreshToken;
}
