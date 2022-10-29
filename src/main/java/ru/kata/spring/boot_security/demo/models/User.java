package ru.kata.spring.boot_security.demo.models;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@NamedEntityGraph(name = "User.role", attributeNodes = @NamedAttributeNode("roleList"))
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 30, message = "Имя должно быть в пределах от 2 до 30 символов")
    private String name;

    @Min(value = 0, message = "Возраст должен быть больше, чем 0")
    private int age;

    @Column(name = "email")
    @Email(message = "Несоответствие форме Email")
    private String email;

    @NotEmpty(message = "Password не должен быть пустым")
    private String password;

    @ManyToMany(mappedBy = "userList")
    @Cascade(CascadeType.DELETE)
    private Set<Role> roleList;

    public User() {
    }

    public User(int id, String name, int age, String email, String password) {
        this(name, age, email, password);
        this.id = id;
    }

    public User(String name, int age, String email, String password) {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Role> getRoleList() {
        if (roleList == null) {
            roleList = new HashSet<>();
        }
        return roleList;
    }

    public void setRoleList(Set<Role> roleList) {
        this.roleList = roleList;
    }

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
