// rename file name to CoursesController (REST)

package com.example.demo.controller;

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
@RequestMapping
public class IndexController {

    private final CourseLister repository;

    @Autowired
    public IndexController(CourseLister courseRepository) {
        this.repository = courseRepository;
    }

    @GetMapping
    public String index(Model model, @RequestParam(name = "titlePrefix", required = false, defaultValue = "") String titlePrefix) {

        model.addAttribute("courses", repository.findByTitleLike(titlePrefix + "%"));
        model.addAttribute("activePage", "courses");
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
//        model.addAttribute("courses", repository.findByTitleLike(titlePrefix + "%"));
        model.addAttribute("activePage", "Войти");
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {

//        model.addAttribute("courses", repository.findByTitleLike(titlePrefix + "%"));
        model.addAttribute("activePage", "Зарегистрироваться");
        return "registration";
    }
}
