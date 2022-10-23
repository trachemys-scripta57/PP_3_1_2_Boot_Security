package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.services.PersonService;
import ru.kata.spring.boot_security.demo.services.RoleService;

import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final PersonService personService;
    private final RoleService roleService;

    public UsersController(PersonService personService, RoleService roleService) {
        this.personService = personService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String showUserInfo(Model model, Principal principal) {
        StringBuilder roles = new StringBuilder();
        for (Role role : personService.findUserByEmail(principal.getName()).getRoles()) {
            roles.append(role.toString());
            roles.append(" ");
        }
        model.addAttribute("thisUserRole", roles);
        model.addAttribute("thisUser", personService.findUserByEmail(principal.getName()));

        return "user";
    }
}
