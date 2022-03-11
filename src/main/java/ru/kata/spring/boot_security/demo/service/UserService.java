package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public interface UserService {
    List<User> userList();

    User findById(long id);

    void saveUser(User user);

    void update(long id, User user);

    void deleteById(long id);

    User findByUsername(String username);
}
