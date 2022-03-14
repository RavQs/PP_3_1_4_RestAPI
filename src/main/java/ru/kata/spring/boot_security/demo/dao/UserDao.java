package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


public interface UserDao {
    List<User> userList();

    User findById(long id);

    void saveUser(User user);

    void update(long id, User user);

    void deleteById(long id);

    User findByEmail(String username);


}
