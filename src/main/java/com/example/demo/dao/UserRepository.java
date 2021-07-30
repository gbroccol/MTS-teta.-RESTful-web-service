package com.example.demo.dao;

import com.example.demo.domain.Course;
import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    List<Course> findByTitleLike(String title);

}