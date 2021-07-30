package com.example.demo.service;

import com.example.demo.dao.CourseRepository;
import com.example.demo.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CourseLister {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseLister(CourseRepository repository) {
        this.courseRepository = repository;
    }

    public List<Course> findByTitleLike(String title) {
        return courseRepository.findByTitleLike(title);
    }

    public void save(Course course) { courseRepository.save(course); }

    public Optional <Course> findById(Long id) { return courseRepository.findById(id); }

    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    public Course getOne(Long id) {
        return courseRepository.getOne(id);
    }
}