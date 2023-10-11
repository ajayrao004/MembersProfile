package com.member.controller;

import com.member.model.User;
import com.member.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/allmembers")

    public ResponseEntity<List<User>> register(@RequestBody List<User> member)
    {
        return userService.register(member);

    }
    @GetMapping
    public ResponseEntity<String> sayHello()
    {
        return  ResponseEntity.ok("Hi User");
    }

    @GetMapping("/allmembers")

    public ResponseEntity<List<User>> allMembers()
    {
        return userService.allMembers();
    }
    @GetMapping("/memberById/{id}")

    public ResponseEntity<Optional<User>> getMembersById(@PathVariable Integer id)
    {
        return userService.getMembersById(id);
    }

}

