package com.example.demo.domain;

import com.example.demo.annotations.AuthorAllowed;
import com.example.demo.annotations.TitleCase;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "Автор курса должен быть заполнен")
//    @AuthorAllowed(authors = {"Вася", "Настя", "Петя"})
    @Column
    private String author;

    @TitleCase(message = "Название курса недопустимо")
    @NotBlank(message = "Название курса должно быть заполнено")
    @Column
    private String title;

    @Column
    private String description;

    // mappedBy делает связь двусторонней
    // В качестве значения этого атрибута мы указываем имя поля
    // course в сущности Lesson, которое ссылается на сущность Course.
    // Атрибут mappedByвсегда указывается на стороне-владельце
    // настройки каскадных операций - при удалении курса часто удаляют и все входящие
    // в него уроки.
    // Именно такое поведение мы формируем при помощи атрибута cascade = CascadeType.ALL.
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Lesson> lessons;

    @ManyToMany
    private Set<User> users;

    public Course() {}

    public Course(Long id, String author, String title) {
        this.id = id;
        this.author = author;
        this.title = title;
    }

    // --------------------- GET & SET ----------------------------

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public List<Lesson> getLessons() { return lessons; }
    public void setLessons(List<Lesson> lessons) { this.lessons = lessons; }

    public Set<User> getUsers() { return users; }
    public void setUsers(Set<User> users) { this.users = users; }
}