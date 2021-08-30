package com.example.crud_security_springboot1.controller;

import com.example.crud_security_springboot1.model.User;
import com.example.crud_security_springboot1.service.RoleService;
import com.example.crud_security_springboot1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class AdminRestController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getUserList());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/users")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        userService.add(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/users/")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        userService.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        User user = userService.getUserById(id);
        userService.delete(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
