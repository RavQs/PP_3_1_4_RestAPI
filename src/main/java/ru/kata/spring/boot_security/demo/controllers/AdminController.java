package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/simple")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping
    public String adminIndex() {
        return "redirect:/login";
    }

    @GetMapping("/user")
    public String getUsers(Model model, Principal principal) {
        model.addAttribute("userName", userService.findByUsername(principal.getName()));
        return "user";
    }


    @GetMapping("/admin")
    public String test(Model model, Principal principal) {
        model.addAttribute("userList", userService.userList());
        model.addAttribute("userName", userService.findByUsername(principal.getName()));
        model.addAttribute("userNew", new User());
        Set<Role> roles = roleService.getRoleList();
        model.addAttribute("allRoles", roles);
        return "admin";
    }

    @PostMapping("/admin/createUser")
    public String create(@ModelAttribute("user") User user,
                         @RequestParam("role_authorities") List<String> role_value) {

        user.setRoles(userService.getSetOfRoles(role_value));
        userService.saveUser(user);
        return "redirect:/simple/admin";
    }


    @PatchMapping("/admin/{id}")
    public String update(@ModelAttribute("user") User user, BindingResult bindingResult, @PathVariable("id") int id,
                         @RequestParam("role_authorities") List<String> role_value) {
        if (bindingResult.hasErrors())
            return "edit";
        user.setRoles(userService.getSetOfRoles(role_value));
        userService.update(id, user);
        return "redirect:/simple/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/simple/admin";
    }
}
