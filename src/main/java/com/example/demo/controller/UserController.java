package com.example.demo.controller;

import com.example.demo.domain.Course;
import com.example.demo.domain.User;
import com.example.demo.service.CourseLister;
import com.example.demo.service.UserLister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping
public class UserController {

    private final UserLister userRepository;
    private final CourseLister courseRepository;

    @Autowired
    public UserController(UserLister repository, CourseLister courseRepository) {
        this.userRepository = repository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/user")
    public String userProfile(Model model) {

        model.addAttribute("user", userRepository.findById(1));
//        model.addAttribute("user", new User());
//        model.addAttribute("courses", repository.findByTitleLike(titlePrefix + "%"));
        model.addAttribute("title", "Редактировать профиль");
//        return "user/user_courses";
        return "user/user_profile";
    }

    @GetMapping("/user/courses")
    public String userCourses(Model model) {

        model.addAttribute("user", userRepository.findById(1).get());
        model.addAttribute("courses", userRepository.findById(1).get().getCourses());
        return "user/user_courses";
    }

    @PostMapping("/user")
    public String submitUser(Model model, @Valid User user, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("message", "Ошибка");
            model.addAttribute("user", user);
            return "user/user_profile";
        }
        userRepository.save(user);
        model.addAttribute("message", "Данные сохранены");
//        return "redirect:/course";

        model.addAttribute("user", userRepository.findById(1).get());
        return "user/user_profile";
    }

    @DeleteMapping("/user/course/{id}")
    public String deleteCourse(@PathVariable("id") Long courseId) {

        long userId = 1;

        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        Course course = courseRepository.findById(courseId).orElseThrow(NotFoundException::new);
        user.getCourses().remove(course);
        course.getUsers().remove(user);
        courseRepository.save(course);


        return "redirect:/user/courses";
    }
}
