package com.member.service;

import com.member.dto.JwtAuthenticationResponse;
import com.member.dto.RefreshTokenRequest;
import com.member.dto.SigninRequest;
import com.member.dto.SignupRequest;
import com.member.model.User;

public interface AuthenticationService {
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
    User signUp(SignupRequest signupRequest);
    JwtAuthenticationResponse signin(SigninRequest signinRequest);
}
