package com.example.demo.domain;

import com.example.demo.annotations.TitleCase;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "Введите имя")
    @Column
    private String username;

    @NotNull
    @NotBlank(message = "Введите фамилию")
    @Column
    private String surname;

    //  Атрибут mappedBy
    //  делает сторону пользователя владельцем отношения и делает его двусторонним.
    @ManyToMany(mappedBy = "users")
    private Set<Course> courses;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }


    // --------------------- GET & SET ----------------------------

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Course> getCourses() { return courses; }
    public void setCourses(Set<Course> courses) { this.courses = courses; }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    // --------------------- equals & hashCode ----------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}