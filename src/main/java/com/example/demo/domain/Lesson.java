package com.example.demo.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Lob //  (large object) - longtext
    @Column
    @Type(type = "org.hibernate.type.TextType")
    private String text;

    // Атрибут optional = false указывает на то, что не должно быть возможности
    // добавить урок, который не относится ни к одному из курсов
    @ManyToOne(optional = false)
    // ссылка на экземпляр сущности Course, в который входит данный
    // урок. Об этом Hibernate сообщает аннотация @ManyToOne.
    private Course course;

    public Lesson() {
    }

    public Lesson(String title, String text, Course course) {
        this.title = title;
        this.text = text;
        this.course = course;
    }

    public Lesson(Long lesson_id, String title, String text, Course course) {
        this.id = lesson_id;
        this.title = title;
        this.text = text;
        this.course = course;
    }

    // --------------------- GET & SET ----------------------------

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
}