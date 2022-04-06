package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Transactional
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleService roleService) {
        this.roleService = roleService;
        this.userDao = userDao;
    }

    @Override
    public List<User> userList() {
        return userDao.userList();
    }

    @Override
    public User findById(long id) {
        return userDao.findById(id);
    }

    @Override
    public User saveUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public User update(long id, User user) {
        return userDao.update(id, user);
    }

    @Override
    public void deleteById(long id) {
        userDao.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByEmail(username);
    }

    @Override
    public Set<Role> getSetOfRoles(List<String> role_string) {
        Set<Role> roles = new HashSet<>();
        for (String roleOfName : role_string) {
            roles.add(roleService.findByRole(roleOfName));
        }
        return roles;
    }
}
