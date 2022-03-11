package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface userDao {
    List<User> index();

    User findById(long id);

    void saveUser(User user);

    void update(long id, User user);

    void delete(long id);
}
