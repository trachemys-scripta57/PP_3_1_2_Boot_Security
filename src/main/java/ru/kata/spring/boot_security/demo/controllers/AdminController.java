package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.kata.spring.boot_security.demo.models.Person;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.services.PersonService;
import ru.kata.spring.boot_security.demo.services.RoleService;

import javax.validation.Valid;
import java.security.Principal;


@RequestMapping("/admin")
@Controller
public class AdminController {
    private final PersonService personService;
    private final RoleService roleService;

    @Autowired
    public AdminController(PersonService personService, RoleService roleService) {
        this.personService = personService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String pageForAdmin(Model model, Principal principal) {
        StringBuilder roles = new StringBuilder();
        for (Role role : personService.findUserByEmail(principal.getName()).getRoles()) {
            roles.append(role.toString());
            roles.append(" ");
        }
        model.addAttribute("thisUserRole", roles);
        model.addAttribute("users", personService.findAll());
        model.addAttribute("this_user", personService.findUserByEmail(principal.getName()));
        model.addAttribute("new_user", new Person());
        model.addAttribute("roles", roleService.findAllRoles());
        return "admin";
    }

    @PostMapping("/new")
    public String createPerson(@ModelAttribute("user") @Valid Person person) {
        personService.save(person);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        personService.deleteById(id);
        return "redirect:/admin";
    }

    @PatchMapping("/edit/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute @Valid Person person) {
        personService.update(person.getId(), person);
        return "redirect:/admin";
    }
}
