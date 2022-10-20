package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface RoleService {
    Role getByIdRoles(int id);
    List<Role> findAllRoles();
    Set<Role> findRoleById(ArrayList<Integer> roles);
}
