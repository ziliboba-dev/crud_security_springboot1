package com.example.crud_security_springboot1.controller;


import com.example.crud_security_springboot1.model.Role;
import com.example.crud_security_springboot1.model.User;
import com.example.crud_security_springboot1.service.RoleService;
import com.example.crud_security_springboot1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashSet;
import java.util.Set;

@Controller
@EnableWebMvc
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.getUserList());
        model.addAttribute("roles", roleService.getRolesList());
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getRolesList());
        return "show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping
    public String createUser
            (@ModelAttribute("user") User user, @RequestParam(required = false) String adminRole) {
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleService.getRoleById(1L));
        if (adminRole != null && adminRole.equals(roleService.getRoleById(2L).getRole())) {
            userRoles.add(roleService.getRoleById(2L));
        }
        user.setRoles(userRoles);
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(@ModelAttribute("user") User user) {
        return "edit";
    }

    @PostMapping ("/{id}")
    public String update(@ModelAttribute("user") User user, @RequestParam(required = false) String adminRole) {
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleService.getRoleById(1L));
        if (adminRole != null) {
            userRoles.add(roleService.getRoleById(2L));
        }
        user.setRoles(userRoles);
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        userService.delete(user);
        return "redirect:/admin";
    }
}
