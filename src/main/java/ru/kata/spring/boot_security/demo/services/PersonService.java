package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Person;

import java.util.List;
import java.util.Optional;

@Service
public interface PersonService {
    Optional<Person> findUserByUsername(String username);
    Person getById(int id);
    void save(Person person);
    void deleteById(int id);
    List<Person> findAll();
    Person passwordCoder(Person person);
}
