package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api")
public class AdminRESTController {
    private final UserService userService;


    @Autowired
    public AdminRESTController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/user")
    public User getUserPage(Principal principal) {
        return ResponseEntity.ok().body(userService.findByUsername(principal.getName())).getBody();
    }


    //AdminPage
    @GetMapping("/admin")
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
    }*/

    @GetMapping("/admin/{id}")
    public User User(@PathVariable long id) {
        return ResponseEntity.ok().body(userService.findById(id)).getBody();
    }



    @PostMapping("/admin")
    public User addNewUser(@RequestBody User user, @RequestParam("roles") List<String> roles) {
        user.setRoles(userService.getSetOfRoles(roles));
        ResponseEntity.ok().body(userService.saveUser(user)).getBody();
        return userService.saveUser(user);
    }

    @PatchMapping("/admin/{id}")
    public User updateNewUser(@PathVariable("id") long id, @RequestBody User user, @RequestParam("roles") List<String> roles) {
        user.setRoles(userService.getSetOfRoles(roles));
        return ResponseEntity.ok().body(userService.update(id, user)).getBody();
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteById(id);
        return "User with id:" + id + " was deleted";
    }
}
