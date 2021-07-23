// маппинги
// @GetMapping
// @PostMapping
// @PutMapping
// @DeleteMapping
// @PatchMapping

package com.example.demo.controller;

import com.example.demo.dao.CourseRepository;
import com.example.demo.domain.Course;
import com.example.demo.service.CourseLister;
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

    private final CourseLister courseLister;

    @Autowired
    public CourseController(CourseLister courseLister) {
        this.courseLister = courseLister;
    }

    @GetMapping
    public String courseTable(Model model, @RequestParam(name = "titlePrefix", required = false) String titlePrefix) {
        model.addAttribute("courses", courseLister.coursesFindByTitleWithPrefix(titlePrefix == null ? "" : titlePrefix));
        model.addAttribute("activePage", "courses");
        return "all_courses";
    }

//    @RequestMapping("/{id}")
//    public String courseForm(Model model, @PathVariable("id") Long id) {
//        model.addAttribute("course", courseRepository.findById(id).orElseThrow(NotFoundException::new));
//        return "course_edit";
//    }

    @GetMapping("/new")
    public String createNewCourse(Model model) {
        model.addAttribute("course", new Course());
        return "course_form";
    }

    @RequestMapping("/{id}/edit")
    public String courseForm(Model model, @PathVariable("id") Long id) {
        model.addAttribute("course", courseLister.coursesFindById(id).orElseThrow(NotFoundException::new));
        return "course_edit"; // вернуть представление (view)
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("not_found");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }

    @PostMapping
    public String submitCourseForm(@Valid Course course, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {

            if (request.getHeader("referer").contains("/course/new"))
                return "course_form";
            return "course_edit";
        }
        courseLister.coursesSave(course);
        return "redirect:/course";
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable("id") Long id) {
        courseLister.coursesDelete(id);
        return "redirect:/course";
    }
}


