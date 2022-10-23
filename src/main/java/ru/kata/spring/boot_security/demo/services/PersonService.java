package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Person;

import java.util.List;

@Service
public interface PersonService {
    Person findUserByEmail(String email);

    void save(Person person);

    @Transactional
    void update(int id, Person updatedPerson);

    void deleteById(int id);

    List<Person> findAll();

    Person passwordCoder(Person person);
}
