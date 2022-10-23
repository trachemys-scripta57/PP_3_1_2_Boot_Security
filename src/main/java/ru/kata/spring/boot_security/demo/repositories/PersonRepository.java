package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Person;

import java.util.List;

@Repository
public interface PersonRepository{
    Person findUserByEmail(String email);
    void save(Person person);
    void deleteById(int id);
    void update(Person person);
    Person getById(int id);
    List<Person> findAll();
}
