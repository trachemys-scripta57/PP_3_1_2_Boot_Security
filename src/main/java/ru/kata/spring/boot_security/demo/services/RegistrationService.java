package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Person;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.PersonRepository;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

@Service
public class RegistrationService {
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(PersonRepository personRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public void register(Person person) {
        Role role = new Role("ROLE_USER");
        person.setRoles(new HashSet<>(Collections.singletonList(role)));
        role.setPersonList(new ArrayList<>(Collections.singletonList(person)));
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepository.save(person);
        roleRepository.save(role);
    }
}
