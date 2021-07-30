package com.example.demo.service;

import com.example.demo.dao.LessonRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.domain.Course;
import com.example.demo.domain.Lesson;
import com.example.demo.domain.LessonDto;
import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserLister {

    private final UserRepository userRepository;

    @Autowired
    public UserLister(UserRepository repository) {
        this.userRepository = repository;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public Optional <User> findById(long id) {
        return userRepository.findById(id);
    }

    public User getOne(long id) {
        return userRepository.getOne(id);
    }
}

