package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


public interface UserDao {
    List<User> userList();

    User findById(long id);

    User saveUser(User user);

    User update(long id, User user);

    void deleteById(long id);

    User findByEmail(String username);


}
