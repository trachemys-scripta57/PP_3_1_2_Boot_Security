package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface UserService {
    void save(User user, Set<Role> roles);
    List<User> findAllUser();
    void update(int id, User updatedUser, Set<Role> roles);
    void delete(int id);
    Optional<User> userByUsername(String name);
}
