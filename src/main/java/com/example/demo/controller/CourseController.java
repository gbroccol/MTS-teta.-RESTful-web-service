// rename file name to CoursesController (REST)

package com.example.demo.controller;

import com.example.demo.domain.Course;
import com.example.demo.domain.User;
import com.example.demo.service.CourseLister;
import com.example.demo.service.LessonLister;
import com.example.demo.service.UserLister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/course")
public class CourseController {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private final CourseLister courseRepository;
    private final UserLister userRepository;
    private final LessonLister lessonRepository;

    @Autowired
    public CourseController(CourseLister courseRepository, UserLister userRepository, LessonLister lessonRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
    }

    @GetMapping
    public String courseTable(Model model, @RequestParam(name = "titlePrefix", required = false, defaultValue = "") String titlePrefix) {

        model.addAttribute("courses", courseRepository.findByTitleLike(titlePrefix + "%"));
        model.addAttribute("activePage", "courses");
        return "index";
    }

    @RequestMapping("/{id}/edit")
    public String courseEdit(Model model, @PathVariable("id") Long courseId) {
        model.addAttribute("course", courseRepository.findById(courseId).get());
        model.addAttribute("lessons", courseRepository.findById(courseId).get().getLessons());
//        model.addAttribute("lessons", lessonRepository.findAllForLessonIdWithoutText(courseId));
        model.addAttribute("users", courseRepository.findById(courseId).get().getUsers());

//        model.addAttribute("course", courseRepository.findById(id).orElseThrow(NotFoundException::new));
        return "course/edit";
    }

    @RequestMapping("/{id}")
    public String courseData(Model model, @PathVariable("id") Long courseId) {
        model.addAttribute("course", courseRepository.findById(courseId).get());
        model.addAttribute("lessons", courseRepository.findById(courseId).get().getLessons());
//        model.addAttribute("lessons", lessonRepository.findAllForLessonIdWithoutText(courseId));
        model.addAttribute("users", courseRepository.findById(courseId).get().getUsers());

//        model.addAttribute("course", courseRepository.findById(id).orElseThrow(NotFoundException::new));
        return "course/edit";
    }

//    @RequestMapping("/{id}")
//    public String courseDescription(Model model, @PathVariable("id") Long id) {
//        model.addAttribute("course", repository.findById(id).get());
////        model.addAttribute("course", courseRepository.findById(id).orElseThrow(NotFoundException::new));
//        return "course/edit";
//    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable("id") Long id) {
        courseRepository.deleteById(id);
        return "redirect:/course/";
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("not_found");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }

    @GetMapping("/new")
    public String createNewCourse(Model model) {
        model.addAttribute("course", new Course());
        return "course/new";
    }

    @PostMapping
    public String submitCourse(@Valid Course course, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {

            if (request.getHeader("referer").contains("/course/new"))
                return "course/new";
            return "course/edit";
        }
        courseRepository.save(course);
        if (request.getHeader("referer").contains("/course/new"))
            return "course/edit";
        return "redirect:/course";
    }

    @PostMapping("/{id}/assign")
    public String addUser(Model model, @PathVariable("id") Long courseId) { // @RequestParam("userId") Long id

        System.out.println(ANSI_RED + "hi" + ANSI_RESET);

        User user = userRepository.findById(1).get();
        Course course = courseRepository.findById(courseId).get();
        course.getUsers().add(user);
        user.getCourses().add(course);
        courseRepository.save(course);

        model.addAttribute("course", courseRepository.findById(courseId).get());
        model.addAttribute("lessons", courseRepository.findById(courseId).get().getLessons());
//        model.addAttribute("lessons", lessonRepository.findAllForLessonIdWithoutText(courseId));
        model.addAttribute("users", courseRepository.findById(courseId).get().getUsers());

        return "course/edit";
    }
}


