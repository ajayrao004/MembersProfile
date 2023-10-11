package com.member.service.impl;

import com.member.dao.UserDao;
import com.member.model.User;
import com.member.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public ResponseEntity<List<User>> register(List<User> user)
    {

        try
        {
            return new ResponseEntity<>(userDao.saveAll(user), HttpStatus.OK);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public UserDetailsService userDetailService()
    {
        return new UserDetailsService()
        {
            @Override
            public UserDetails loadUserByUsername(String username){
                return userDao.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
            }
        };
    }
    public ResponseEntity<List<User>> allMembers() {
        try {
            return new ResponseEntity<>(userDao.findAll(),HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Optional<User>> getMembersById(Integer id) {
        return new ResponseEntity<>(userDao.findById(id),HttpStatus.OK);

    }
}
