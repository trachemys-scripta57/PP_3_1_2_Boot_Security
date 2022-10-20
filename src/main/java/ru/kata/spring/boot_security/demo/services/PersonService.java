package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Person;

import java.util.List;

public interface PersonService {
    Person findUserByUsername(String username);
    Person getById(int id);
    void save(Person person);
    void deleteById(int id);
    List<Person> findAll();
    Person passwordCoder(Person person);
}
