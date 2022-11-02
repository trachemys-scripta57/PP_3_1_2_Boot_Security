package ru.kata.spring.boot_security.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class AdminAccount {
    private final UserService userService;

    @Autowired
    public AdminAccount(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void admin() {
        User admin = new User("admin", 100, "admin@admin",
                "admin");
        Role roleUser = new Role("ROLE_USER");
        Role roleAdmin = new Role("ROLE_ADMIN");
        if (userService.userByUsername(admin.getName()).isEmpty()) {
            userService.save(admin, new HashSet<>(Set.of(roleUser, roleAdmin)));
        }
    }
}
