package com.example.c.controller;

import com.example.c.entity.Course;
import com.example.c.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CourseRepository courseRepo;

    // SHOW coureses list
    @GetMapping("/courses")
    public String showCourses(Model model) {
        List<Course> courses = courseRepo.findAll();
        model.addAttribute("courses", courses);
        return "coursesList";
    }

    // ADD course
    @GetMapping("/courses/add")
    public String addCourse(Model model) {
        model.addAttribute("course", new Course());
        return "addCourse";
    }
    @PostMapping("/courses/add")
    public String addCourse(@ModelAttribute("course") @Valid Course course, BindingResult result,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "addCourse";
        }
        courseRepo.save(course);
        redirectAttributes.addFlashAttribute("message", "Course Added!");
        return "redirect:/courses";
    }

    // UPDATE course


    // DELETE course


}
