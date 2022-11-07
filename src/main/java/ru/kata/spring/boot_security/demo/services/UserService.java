package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    void save(User user);
    List<User> findAllUser();
    void update(int id, User updatedUser);
    void delete(int id);
    Optional<User> findUser(int id);
    Optional<User> userByUsername(String email);
    User convertToUser(UserDTO userDTO);
    UserDTO convertToDTO(User user);
}
