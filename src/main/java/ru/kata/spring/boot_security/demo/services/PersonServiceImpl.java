package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Person;
import ru.kata.spring.boot_security.demo.repositories.PersonRepository;

import java.util.List;

@Service
public class PersonServiceImpl implements UserDetailsService, PersonService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Person person = personRepository.findByName(s);

        if (person == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return person;
    }
    @Override
    @Transactional
    public Person findUserByUsername(String s) {
        return personRepository.findByName(s);
    }

    @Override
    public Person getById(int id) {
        return personRepository.getById(id);
    }

    @Override
    public void save(Person person) {
        personRepository.save(passwordCoder(person));
    }

    @Override
    public void deleteById(int id) {
        personRepository.deleteById(id);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person passwordCoder(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return person;
    }
}
