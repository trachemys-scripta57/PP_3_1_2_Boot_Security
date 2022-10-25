package ru.kata.spring.boot_security.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Person;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.services.PersonService;
import ru.kata.spring.boot_security.demo.services.RoleService;

import javax.annotation.PostConstruct;
import java.util.*;

/* login - admin
 *  password - admin*/

@Component
public class AdminAccOnStart {
    private final PersonService personService;
    private final RoleService roleService;

    @Autowired
    public AdminAccOnStart(PersonService personService, RoleService roleService) {
        this.personService = personService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void adminAccount() {
        Person admin = new Person("admin", 99, "admin@admin","$2y$10$Ts1iGg.d/UAH0.Z0FZAtmO906EJFxfeJ0vfOkUvMgI2yImFCbiP/G");
        Role roleAdmin = new Role("ROLE_ADMIN");
        admin.setRoles(new HashSet<>(List.of(roleAdmin)));
        roleAdmin.setPersonList(new ArrayList<>(Collections.singletonList(admin)));

        if (personService.findUserByUsername(admin.getName()).isEmpty()) {
            personService.save(admin);
            roleService.addRole(roleAdmin);
        }
    }
}
