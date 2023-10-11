package com.member.dao;

import com.member.model.Role;
import com.member.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserDao extends JpaRepository<User, Integer>  {
    Optional<User> findByEmail(String email);
    User findByRole(Role role);
}

