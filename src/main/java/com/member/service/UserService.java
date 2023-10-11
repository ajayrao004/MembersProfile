package com.member.service;

import com.member.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService {
    ResponseEntity<List<User>> register(List<User> user);
    UserDetailsService userDetailService();
    ResponseEntity<List<User>> allMembers();
    ResponseEntity<Optional<User>> getMembersById(Integer id);
}
