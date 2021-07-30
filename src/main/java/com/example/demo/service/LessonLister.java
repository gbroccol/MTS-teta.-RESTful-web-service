package com.example.demo.service;

import com.example.demo.dao.CourseRepository;
import com.example.demo.dao.LessonRepository;
import com.example.demo.domain.Course;
import com.example.demo.domain.Lesson;
import com.example.demo.domain.LessonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LessonLister {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public LessonLister(LessonRepository lessonRepository, CourseRepository courseRepository) {
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
    }

    public void save(LessonDto lessonDto) {
        Course course = courseRepository.getById(lessonDto.getCourseId());

        Lesson lesson = new Lesson(lessonDto.getId(),
                                    lessonDto.getTitle(),
                                    lessonDto.getText(),
                                    course);

        lessonRepository.save(lesson);
    }

    public void deleteById(Long id) {
        lessonRepository.deleteById(id);
    }

    public Optional<Lesson> findById(Long id) {
        return lessonRepository.findById(id);
    }


//    public Object findAllForLessonIdWithoutText(Long courseId) {
//        return lessonRepository.findAllForLessonIdWithoutText(courseId);
//    }
}

