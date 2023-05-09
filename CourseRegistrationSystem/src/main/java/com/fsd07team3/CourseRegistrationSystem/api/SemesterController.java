package com.fsd07team3.CourseRegistrationSystem.api;

import com.fsd07team3.CourseRegistrationSystem.entity.User;
import com.fsd07team3.CourseRegistrationSystem.entity.Course;
import com.fsd07team3.CourseRegistrationSystem.entity.Semester;
import com.fsd07team3.CourseRegistrationSystem.repository.UserRepository;
import com.fsd07team3.CourseRegistrationSystem.repository.CourseRepository;
import com.fsd07team3.CourseRegistrationSystem.repository.SemesterRepository;
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
public class SemesterController {

    @Autowired
    private CourseRepository courseRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private SemesterRepository semesterRepo;


    // SHOW semester list
    @GetMapping("/admin/semesters")
    public String showCourses(Model model) {
        List<Semester> semesters = semesterRepo.findAll();
        model.addAttribute("semesters", semesters);
        List<Course> courses = courseRepo.findAll();
        model.addAttribute("courses", courses);
        return "admin_list_semesters";
    }

    @GetMapping("/admin/semesters/{id}")
    public String showEachCourses(@PathVariable("id") Long id, Model model) {
        Semester semester = semesterRepo.findById(id).orElse(null);
        List<Course> courses = courseRepo.findBySemesterId(id);
        model.addAttribute("semesters", semester);
        model.addAttribute("courses", courses);
        return "admin_list_semesterById";
    }

    // ADD semester
    @GetMapping("/admin/semesters/add")
    public String addSemester(Model model) {
        model.addAttribute("semester", new Semester());
        List<Semester> semesters = semesterRepo.findAll();
        model.addAttribute("semesters", semesters);
        model.addAttribute("semesters", semesters);
        List<User> instructors = userRepo.findByRole("INSTRUCTOR");
        model.addAttribute("instructors", instructors);
        return "admin_semesterAdd_form";
    }
    @PostMapping("/admin/semesters/add")
    public String addSemester(@ModelAttribute("semester") @Valid Semester semester, BindingResult result,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin_semesterAdd_form";
        }
        semesterRepo.save(semester);
        redirectAttributes.addFlashAttribute("message", "Semester added.");
        return "redirect:/admin/semesters";
    }

    // DELETE semester
    @GetMapping("/admin/semesters/delete/{id}")
    public String deleteSemester(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            Semester semester = semesterRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Semester not found with ID " + id));
            semesterRepo.deleteById(id);
        } catch (EntityNotFoundException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/admin/semesters";
    }


}
