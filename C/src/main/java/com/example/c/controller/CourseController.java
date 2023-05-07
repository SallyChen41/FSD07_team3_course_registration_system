package com.example.c.controller;

import com.example.c.entity.Course;
import com.example.c.entity.Semester;
import com.example.c.entity.User;
import com.example.c.repository.CourseRepository;
import com.example.c.repository.SemesterRepository;
import com.example.c.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CourseRepository courseRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private SemesterRepository semesterRepo;


    /******************* Admin *******************/

    // SHOW courses list
    @GetMapping("/admin/courses")
    public String showCourses(Model model) {
        List<Course> courses = courseRepo.findAll();
        model.addAttribute("courses", courses);
        return "coursesList";
    }

    // ADD course
    @GetMapping("/admin/courses/add")
    public String addCourse(Model model) {
        model.addAttribute("course", new Course());
        List<Semester> semesters = semesterRepo.findAll();
        model.addAttribute("semesters", semesters);
        List<User> instructors = userRepo.findByRole(User.Role.INSTRUCTOR);
        model.addAttribute("instructors", instructors);
        return "addCourse";
    }
    @PostMapping("/admin/courses/add")
    public String addCourse(@ModelAttribute("course") @Valid Course course, BindingResult result,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "addCourse";
        }
        courseRepo.save(course);
        redirectAttributes.addFlashAttribute("message", "Course '" + course.getTitle() + "' added.");
        return "redirect:/admin/courses";
    }

    // UPDATE course
    @GetMapping("/admin/courses/edit")
    public String editCourse(@RequestParam Long id, Model model) {
        Course course = courseRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Course not found with ID " + id));
        model.addAttribute("course", course);
        List<Semester> semesters = semesterRepo.findAll();
        model.addAttribute("semesters", semesters);
        List<User> instructors = userRepo.findByRole(User.Role.INSTRUCTOR);
        model.addAttribute("instructors", instructors);
        return "editCourse";
    }
    @PostMapping("/admin/courses/edit")
    public String editCourse(@ModelAttribute("course") @Valid Course course,
                             BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "editCourse";
        }
        courseRepo.save(course);
        redirectAttributes.addFlashAttribute("message", "Course '" + course.getTitle() + "' updated.");
        return "redirect:/admin/courses";
    }

    // DELETE course
    @GetMapping("/admin/courses/delete")
    public String deleteCourseInEdit(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        courseRepo.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Course deleted.");
        return "redirect:/admin/courses";
    }

    @PostMapping("/admin/courses/delete/{id}")
    public String deleteCourse(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            Course course = courseRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Course not found with ID " + id));
            courseRepo.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Course '" + course.getTitle() + "' deleted.");
        } catch (EntityNotFoundException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/admin/courses";
    }



    /******************* Instructor *******************/

    // Show list of courses
    @GetMapping("/instructor/{id}") // id = user.id
    public String showCourseList(@PathVariable("id") Long id, Model model) {
        List<User> instructors = userRepo.findByRole(User.Role.INSTRUCTOR);
        model.addAttribute("instructors", instructors);
        User instructor = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        List<Course> courses = courseRepo.findByInstructor(instructor);
        model.addAttribute("courses", courses);
        return "instructorListOfCourses";
    }


    // ** a BETTER way using authentication
//    @GetMapping("/professor/courses")
//    public String showCourseList(Model model, Authentication authentication) {
//        String username = authentication.getName(); // Retrieve the currently authenticated user's username
//        User user = userRepo.findByUsername(username); // Retrieve the User object for the currently authenticated user
//        List<User> instructors = userRepo.findByRole(User.Role.INSTRUCTOR);
//        model.addAttribute("instructors", instructors);
//        model.addAttribute("message", "Welcome back " + user.getFirstName()); // Concatenate the user's first name with the welcome message
//        return "instructorListOfCourses";
//    }



    /******************* Student *******************/

}
