package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRESTController {
    private final UserService userService;

    @Autowired
    public AdminRESTController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUserList() {
        return userService.userList();
    }

    @GetMapping("/users/{id}")
    public User User(@PathVariable long id) {
        return userService.findById(id);
    }

    @PostMapping("/users")
    public User addNewUser(@RequestBody User user, @RequestParam("roles") List<String> roles) {
        user.setRoles(userService.getSetOfRoles(roles));
        return userService.saveUser(user);
    }

    @PatchMapping("/users/{id}")
    public User updateNewUser(@PathVariable("id") long id, @RequestBody User user, @RequestParam("roles") List<String> roles) {
        user.setRoles(userService.getSetOfRoles(roles));
        return userService.update(id,user);
    }
}
