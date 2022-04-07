package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRESTController {
    private final UserService userService;
    private final RestTemplate restTemplate;
    //private final String URL = "http://localhost:8080/api/users";

    @Autowired
    public AdminRESTController(UserService userService, RestTemplate restTemplate) {
        this.userService = userService;
        this.restTemplate = restTemplate;
    }

   /* @GetMapping("/users")
    public List<User> getUserList() {
        return userService.userList();
    }*/

    @GetMapping("/users")
    public List<User> getUsersList() {
        return ResponseEntity.ok().body(userService.userList()).getBody();
    }

    /*@GetMapping("/users")
    public List<User> getUserList() {
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(URL,
                HttpMethod.GET,
                null, //Передача body Http методу(У Get тела нет, все находится в URL)
                new ParameterizedTypeReference<List<User>>() {
                });
        List<User> allUsers = responseEntity.getBody();
        return allUsers;
    }*/ //Не работает, виснет

    @GetMapping("/users/{id}")
    public User User(@PathVariable long id) {
        return ResponseEntity.ok().body(userService.findById(id)).getBody();
    }

    @PostMapping("/users")
    public User addNewUser(@RequestBody User user, @RequestParam("roles") List<String> roles) {
        user.setRoles(userService.getSetOfRoles(roles));
        ResponseEntity.ok().body(userService.saveUser(user)).getBody();
        return userService.saveUser(user);
    }

    @PatchMapping("/users/{id}")
    public User updateNewUser(@PathVariable("id") long id, @RequestBody User user, @RequestParam("roles") List<String> roles) {
        user.setRoles(userService.getSetOfRoles(roles));
        return ResponseEntity.ok().body(userService.update(id, user)).getBody();
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteById(id);
        return "User with id:" + id + " was deleted";
    }
}
