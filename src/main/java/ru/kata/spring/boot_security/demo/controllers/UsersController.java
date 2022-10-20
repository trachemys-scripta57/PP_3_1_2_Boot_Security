package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.Person;
import ru.kata.spring.boot_security.demo.services.PersonService;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final PersonService personService;

    public UsersController(PersonService personService) {
        this.personService = personService;
    }
    @GetMapping()
    public String showUserInfo(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person person = personService.findUserByUsername(auth.getName());
        model.addAttribute("user", person);

        return "users";
    }
}
