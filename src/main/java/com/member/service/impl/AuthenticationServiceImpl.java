package com.member.service.impl;

import com.member.dao.UserDao;
import com.member.dto.JwtAuthenticationResponse;
import com.member.dto.RefreshTokenRequest;
import com.member.dto.SigninRequest;
import com.member.dto.SignupRequest;
import com.member.model.Role;
import com.member.model.User;
import com.member.service.AuthenticationService;
import com.member.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private UserDao userDao;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JWTService jwtService;
    @Autowired
    public AuthenticationServiceImpl(
            UserDao userDao,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JWTService jwtService) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }
    public User signUp(SignupRequest signupRequest)
    {
        User user=new User();
        user.setEmail(signupRequest.getEmail());
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
       return userDao.save(user);

    }
    public JwtAuthenticationResponse signin(SigninRequest signinRequest)
    {
     authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail()
     ,signinRequest.getPassword()));
     var user=userDao.findByEmail(signinRequest.getEmail()).orElseThrow(()->new IllegalArgumentException("Invalid email or password"));
     var jwt=jwtService.generateToken(user);
     var refreshToken=jwtService.generateRefreshToken(new HashMap<>(),user);
      JwtAuthenticationResponse jwtAuthenticationResponse=new JwtAuthenticationResponse();
      jwtAuthenticationResponse.setToken(jwt);
      jwtAuthenticationResponse.setRefreshToken(refreshToken);
      return jwtAuthenticationResponse;

    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest)
    {
        String userEmail= jwtService.extractUserName(refreshTokenRequest.getToken());
        User user=userDao.findByEmail(userEmail).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(),user))
        {
            var jwt=jwtService.generateToken(user);
            JwtAuthenticationResponse jwtAuthenticationResponse=new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;

        }
        return  null;
    }


}
