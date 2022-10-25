package ru.kata.spring.boot_security.demo.models;

import org.hibernate.annotations.Cascade;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.Objects;
import java.util.Set;

@Entity
@NamedEntityGraph(name = "person.roles", attributeNodes = @NamedAttributeNode("roles"))
@Table(name = "person")
@Component
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Поле имя не должно быть пустым")
    @Size(min = 2, max = 30, message = "Имя должно иметь от 2 до 30 символов")
    @Column(name = "name")
    private String name;

    @Min(value = 0, message = "Возраст должен быть больше чем 0")
    @Max(value = 150, message = "Нужен возраст человека, а не баобаба")
    @Column(name = "age")
    private int age;

    @Column(name = "email")
    @NotEmpty(message = "Поле email необходимо заполнить")
    @Email(message = "Введите действующий адрес")
    private String email;

    @NotEmpty
    @Column(name = "password")
    private String password;


    public Person() {
    }

    @ManyToMany(mappedBy = "personList")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private Set<Role> roles;

    public Person(int id, String name, int age, String email, String password) {
        this(name, age, email, password);
        this.id = id;
    }

    public Person(String name, int age, String email, String password) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && age == person.age && name.equals(person.name) && email.equals(person.email) && password.equals(person.password) && roles.equals(person.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, email, password, roles);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
