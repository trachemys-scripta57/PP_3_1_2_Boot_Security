package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Person;
import ru.kata.spring.boot_security.demo.services.PersonService;
import ru.kata.spring.boot_security.demo.services.RoleService;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PersonService personService;
    private final RoleService roleService;

    @Autowired
    public AdminController(PersonService personService, RoleService roleService) {
        this.personService = personService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String pageForAdmin(Model model) {
        model.addAttribute("users", personService.findAll());
        return "admin";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") Person person, Model model) {
        model.addAttribute("listRoles", roleService.findAllRoles());
        return "newperson";
    }

    @PostMapping("/new")
    public String create(@RequestParam("role") ArrayList<Integer> roles,
                         @ModelAttribute("user") @Valid Person person,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "newperson";

        }
        person.setRoles(roleService.findRoleById(roles));
        personService.save(person);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        personService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", personService.getById(id));
        model.addAttribute("listRoles", roleService.findAllRoles());
        return "editperson";
    }

    @PatchMapping("{id}")
    public String update(@RequestParam("role") ArrayList<Integer> roles,
                         @PathVariable("id") int id,
                         @ModelAttribute("user") @Valid Person person,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editperson";
        }
        person.setRoles(roleService.findRoleById(roles));
        personService.save(person);
        return "redirect:/admin";
    }
}
