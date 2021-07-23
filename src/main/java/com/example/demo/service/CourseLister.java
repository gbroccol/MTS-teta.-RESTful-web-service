package com.example.demo.service;


import com.example.demo.dao.CourseRepository;
import com.example.demo.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CourseLister {
    private final CourseRepository repository;

    @Autowired
    public CourseLister(CourseRepository repository) {
        this.repository = repository;
    }

    public List<Course> coursesByAuthor(String name) {
        List<Course> allCourses = repository.findAll();
        return allCourses.stream().filter(course -> course.getAuthor().equals(name)).collect(Collectors.toList());
    }

    public List<Course> coursesAll() {
        List<Course> allCourses = repository.findAll();
        return allCourses;
    }

    public List<Course> coursesFindByTitleWithPrefix(String prefix) {
        List<Course> courses = repository.findByTitleWithPrefix(prefix);
        return courses;
    }

    public Optional<Course> coursesFindById(long id) {
        Optional<Course> course = repository.findById(id);
        return course;
    }

    public void coursesSave(Course course) {
        repository.save(course);
    }

    public void coursesDelete(long id) {
        repository.delete(id);
    }
}
