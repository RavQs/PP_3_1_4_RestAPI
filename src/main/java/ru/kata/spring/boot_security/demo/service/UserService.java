package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> index();

    User findById(long id);

    void saveUser(User user);

    void update(long id, User user);

    void delete(long id);
}
