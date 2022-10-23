package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PersonRepositoryImpl implements PersonRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public PersonRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Person findUserByEmail(String email) {
        return entityManager.createQuery(
                        "SELECT person FROM Person person join fetch person.roles WHERE person.email =:email", Person.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public void save(Person person) {
        entityManager.persist(person);
    }

    @Override
    public void deleteById(int id) {
        entityManager.remove(entityManager.find(Person.class, id));
    }

    @Override
    public void update(Person person) {
        entityManager.merge(person);
    }

    @Override
    public Person getById(int id) {
        return entityManager.find(Person.class, id);
    }

    @Override
    public List<Person> findAll() {
        return entityManager.createQuery("SELECT u FROM Person u", Person.class).getResultList();
    }
}
