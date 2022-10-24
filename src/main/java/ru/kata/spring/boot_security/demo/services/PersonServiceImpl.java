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
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Person person = personRepository.findUserByEmail(s);

        if (person == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return person;
    }

    @Override
    public Person findUserByEmail(String email) {
        return personRepository.findUserByEmail(email);
    }
    @Transactional
    @Override
    public void save(Person person) {
        personRepository.save(passwordCoder(person));
    }

    @Transactional
    @Override
    public void update(int id, Person updatedPerson) {
        Person person = personRepository.getById(id);
        if (updatedPerson.getPassword().equals(person.getPassword())) {
            personRepository.save(updatedPerson);
        } else {
            String pass = passwordEncoder.encode(updatedPerson.getPassword());
            updatedPerson.setPassword(pass);
            personRepository.save(updatedPerson);
        }
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        personRepository.deleteById(id);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Transactional
    @Override
    public Person passwordCoder(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return person;
    }
}
