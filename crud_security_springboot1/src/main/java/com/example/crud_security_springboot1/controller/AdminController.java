package com.example.crud_security_springboot1.controller;


import com.example.crud_security_springboot1.model.User;
import com.example.crud_security_springboot1.service.RoleService;
import com.example.crud_security_springboot1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.security.Principal;

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
    public String index(Model model, Principal principal) {
        model.addAttribute("users", userService.getUserList());
        model.addAttribute("roles", roleService.getRolesList());
        model.addAttribute("principalUser", userService.findByUsername(principal.getName()));
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

    @GetMapping("/getUserById/{id}")
    @ResponseBody
    public User getUserById(@PathVariable("id") long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/new")
    public String createUser
            (@ModelAttribute("user") User user, @RequestParam(required = false, name = "listRoles") String[] roleNames) {
        user.setRoles(roleService.getRolesByName(roleNames));
        userService.add(user);
        return "redirect:/admin";
    }


    @PatchMapping ("/update")
    public String update(@ModelAttribute("user") User user, @RequestParam(required = false, name = "listRoles") String[] roleNames) {
        user.setRoles(roleService.getRolesByName(roleNames));
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete")
    public String delete(Long id) {
        userService.delete(userService.getUserById(id));
        return "redirect:/admin";
    }
}
