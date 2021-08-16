package com.example.crud_security_springboot1.service;

import com.example.crud_security_springboot1.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {
    List<User> getUserList();

    void add(User user);

    void delete(User user);

    void update(User user);

    User getUserById(Long id);

    User findByUsername(String name);
}
