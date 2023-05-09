package com.example.c.controller;

import com.example.c.entity.Course;
import com.example.c.entity.Semester;
import com.example.c.entity.StudentRegistration;
import com.example.c.entity.User;
import com.example.c.repository.CourseRepository;
import com.example.c.repository.SemesterRepository;
import com.example.c.repository.StudentRegistrationRepository;
import com.example.c.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.*;

@Controller
public class CourseController {

    @Autowired
    private CourseRepository courseRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private SemesterRepository semesterRepo;
    @Autowired
    private StudentRegistrationRepository studentRegistrationRepo;


    /******************* Admin *******************/

    // SHOW courses list
    @GetMapping("/admin/courses")
    public String showCourses(Model model) {
        List<Course> courses = courseRepo.findAll();
        // Get available spots for each course
        for (Course course : courses) {
            int registeredStudents = course.getRegistrations().size();
            int availableSpots = course.getStudentLimit() - registeredStudents;
            if (availableSpots < 0) {
                availableSpots = 0;
            }
            course.setAvailable(availableSpots);
        }
        model.addAttribute("courses", courses);
        return "admin_list_courses";
    }

    // ADD course
    @GetMapping("/admin/courses/add")
    public String addCourse(Model model) {
        model.addAttribute("course", new Course());
        List<Semester> semesters = semesterRepo.findAll();
        model.addAttribute("semesters", semesters);
        List<User> instructors = userRepo.findByRole("INSTRUCTOR");
        model.addAttribute("instructors", instructors);
        return "admin_courseAdd_form";
    }
    @PostMapping("/admin/courses/add")
    public String addCourse(@ModelAttribute("course") @Valid Course course, BindingResult result,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin_courseAdd_form";
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
        List<User> instructors = userRepo.findByRole("INSTRUCTOR");
        model.addAttribute("instructors", instructors);
        return "admin_courseEdit_form";
    }
    @PostMapping("/admin/courses/edit")
    public String editCourse(@ModelAttribute("course") @Valid Course course,
                             BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin_courseEdit_form";
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
        } catch (EntityNotFoundException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/admin/courses";
    }


    // SHOW courseRegistration list
    @GetMapping("/admin/studentregistrations")
    public String showStudentRegistrations(Model model) {
        List<StudentRegistration> studentRegistrations = studentRegistrationRepo.findAll();
        model.addAttribute("studentRegistrations", studentRegistrations);
        return "admin_list_registrations";
    }



    /******************* Instructor *******************/

    // Show list of courses
    @GetMapping("/instructor/courses")
    public String showInstructorCourse(Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        String username = authentication.getName();
        User user = userRepo.findByUsername(username);
        List<Course> courses = courseRepo.findByInstructor(user);
        model.addAttribute("instructors", user);
        model.addAttribute("courses", courses);
        // Get available spots for each course
        for (Course course : courses) {
            int registeredStudents = course.getRegistrations().size();
            int availableSpots = course.getStudentLimit() - registeredStudents;
            if (availableSpots < 0) {
                availableSpots = 0;
            }
            course.setAvailable(availableSpots);
        }
        redirectAttributes.addFlashAttribute("message", "You're logged in! Professor " + user.getFirstName());
        return "instructor_list_courses";
    }


    /******************* Student *******************/

    // Show list of registered courses
    @GetMapping("/student/courses")
    public String showStudentCourse(Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        String username = authentication.getName();
        User user = userRepo.findByUsername(username);
        List<StudentRegistration> registrations = studentRegistrationRepo.findByStudentId(user.getId());
        List<Course> courses = new ArrayList<>();
        for (StudentRegistration registration : registrations) {
            if (registration.getStatus().equals("Registered")) {
                courses.add(registration.getCourse());
            }
        }
        model.addAttribute("courses", courses);
        redirectAttributes.addFlashAttribute("message", "You're logged in! ");
        return "student_list_courses";
    }

    // Register courses (ADD)
    @GetMapping("/student/registercourses")
    public String showAvailableCourses(Model model, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepo.findByUsername(username);
        List<Course> availableCourses = courseRepo.findAvailableCoursesForStudent(user.getId());
        // Get available spots for each course
        for (Course course : availableCourses) {
            int registeredStudents = course.getRegistrations().size();
            int availableSpots = course.getStudentLimit() - registeredStudents;
            if (availableSpots < 0) {
                availableSpots = 0;
            }
            course.setAvailable(availableSpots);
        }
        model.addAttribute("courses", availableCourses);
        List<Semester> semesters = semesterRepo.findAll();
        model.addAttribute("semesters", semesters);
        return "student_registerCourses";
    }

    @PostMapping("/student/registercourses")
    public String registerCourse(@RequestParam("courseId") Long courseId, Model model, Authentication authentication) {
        String username = authentication.getName();
        User student = userRepo.findByUsername(username);
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new RuntimeException("Invalid course id: " + courseId));
        StudentRegistration registration = new StudentRegistration();
        registration.setStudent(student);
        registration.setCourse(course);
        registration.setStatus("Registered");
        studentRegistrationRepo.save(registration);
        course.decrementAvailable();
        return "redirect:/student/courses";
    }

    // DROP the course
    @GetMapping("/student/courses/drop/{id}")
    public String dropCourse(@PathVariable("id") Long courseId, Authentication authentication) {
        String username = authentication.getName();
        User student = userRepo.findByUsername(username);
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new RuntimeException("Invalid course id: " + courseId));
        StudentRegistration registration = studentRegistrationRepo.findByStudentAndCourse(student, course);
        registration.setStatus("Dropped");
        studentRegistrationRepo.save(registration);
        course.setAvailable(course.getAvailable() + 1);
        courseRepo.save(course);
        return "redirect:/student/courses/dropped";
    }

    @GetMapping("/student/courses/dropped")
    public String showDroppedCourses(Model model, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepo.findByUsername(username);
        List<StudentRegistration> registrations = studentRegistrationRepo.findByStudentId(user.getId());
        List<Course> droppedCourses = new ArrayList<>();
        for (StudentRegistration registration : registrations) {
            if (registration.getStatus().equals("Dropped")) {
                droppedCourses.add(registration.getCourse());
            }
        }
        model.addAttribute("courses", droppedCourses);
        return "student_list_droppedCourses";
    }

}
