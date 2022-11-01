package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    @Transactional
    public void save(User user, Set<Role> roles) {
        user.setRoleList(roles);
        roles.forEach(f -> f.setUserList(new ArrayList<>(Collections.singletonList(user))));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        roleService.saveAll(roles);
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void update(int id, User updatedUser, Set<Role> roles) {
        updatedUser.setId(id);
        save(updatedUser, roles);
    }

    @Override
    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> userByUsername(String name) {
        return userRepository.findByName(name);
    }
}
