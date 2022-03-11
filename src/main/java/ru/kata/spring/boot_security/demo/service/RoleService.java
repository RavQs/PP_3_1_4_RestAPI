package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Set;

@Service
public interface RoleService {
    Set<Role> getRoleList();
    Role findByRole(String role);
    Role findById(long id);
}
