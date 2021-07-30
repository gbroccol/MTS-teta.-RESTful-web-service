package com.example.demo.controller;

import com.example.demo.domain.Course;
import com.example.demo.domain.Lesson;
import com.example.demo.domain.LessonDto;
import com.example.demo.service.CourseLister;
import com.example.demo.service.LessonLister;
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
@RequestMapping("/lesson")
public class LessonController {

    private final LessonLister lessonRepository;

    @Autowired
    public LessonController(LessonLister repository) {
        this.lessonRepository = repository;
    }

    @GetMapping("/new")
    public String lessonNew(Model model, @RequestParam("course_id") long courseId) {
        model.addAttribute("courseId", courseId);
        model.addAttribute("lesson", new LessonDto(courseId));
        return "lesson/new";
    }

    @GetMapping("/{id}/edit")
    public String lessonEdit(Model model, @PathVariable("id") long id) {

        model.addAttribute("lesson", lessonRepository.findById(id)
                .map(l ->  new LessonDto(l.getId(), l.getTitle(), l.getText(), l.getCourse().getId())));
        return "lesson/new";
    }

    @PostMapping
    public String submitLesson(@Valid LessonDto lesson, BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            if (request.getHeader("referer").contains("/lesson/new"))
                return "lesson/new";
        }
        lessonRepository.save(lesson);
        return "redirect:/course/" + lesson.getCourseId() + "/edit";
    }

    @DeleteMapping("/{id}")
    public String deleteLesson(@PathVariable("id") Long id) {
        long course_id = lessonRepository.findById(id).get().getCourse().getId();
        lessonRepository.deleteById(id);
        return "redirect:/course/" + course_id + "/edit";
    }
}
