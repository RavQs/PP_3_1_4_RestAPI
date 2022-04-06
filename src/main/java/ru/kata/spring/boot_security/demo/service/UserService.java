package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

@Service
public interface UserService {
    List<User> userList();

    User findById(long id);

    User saveUser(User user);

    User update(long id, User user);

    void deleteById(long id);

    User findByUsername(String username);

    Set<Role> getSetOfRoles(List<String> role_value);
}
