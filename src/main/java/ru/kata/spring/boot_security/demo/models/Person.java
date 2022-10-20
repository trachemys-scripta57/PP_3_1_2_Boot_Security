package ru.kata.spring.boot_security.demo.models;


import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "person")
@Component
public class Person implements UserDetails {
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

    @ManyToMany
    @JoinTable(name = "person_roles",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles;


    public Person(String name, int age, String email, String password, Set<Role> roles) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public Set<? extends SimpleGrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities =
                getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                        .collect(Collectors.toSet());
        return authorities;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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
