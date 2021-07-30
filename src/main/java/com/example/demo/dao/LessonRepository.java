package com.example.demo.dao;

import com.example.demo.domain.Course;
import com.example.demo.domain.Lesson;
import com.example.demo.domain.LessonDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

//    @Query("select new com.example.dto.LessonDto(l.id, l.title, l.course.id) " + "from Lesson l where l.course.id = :id")
//    List<LessonDto> findAllForLessonIdWithoutText(@Param("id") long id);

}