package com.fsd07.S.api;

import ch.qos.logback.classic.Logger;
import com.fsd07.S.entity.User;
import com.fsd07.S.repository.UserRepository;
import com.fsd07.S.service.CustomUserDetailsService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    UserRepository userRepository;

    public UserController(CustomUserDetailsService customUserDetailsService){
        this.customUserDetailsService = customUserDetailsService;
    }

    @GetMapping("/")
    public String homePage(){
        return "home_page";
    }

    //Student Register
    @GetMapping("/register")
    ModelAndView registerUser(){
        ModelAndView mav = new ModelAndView("register_page");
        User newUser = new User();
        mav.addObject("user", newUser);
        return mav;
    }

    @PostMapping("/register")
    public String saveUser(@Valid @ModelAttribute User user, BindingResult result,RedirectAttributes redirectAttributes){
        // Check if username exists
        if(customUserDetailsService.usernameExists(user.getUsername())){
            result.addError(new FieldError("user", "username", "Username already exists, please choose another one"));
        }
        // Check if email exists
        if(customUserDetailsService.emailExists(user.getEmail())){
            result.addError(new FieldError("user", "email", "Email already exists, please choose another email"));
        }
        if (result.hasErrors()){
            return "register_page";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(user.getRole());
        userRepository.save(user);
        redirectAttributes.addFlashAttribute("flashMessage", "Register Success!");
        return "redirect:/studentlogin";
    }

    // Student Login
    @GetMapping("/studentlogin")
    public String showStudentLoginForm() {
        return "student_login";
    }


    @GetMapping("/studentlogin_err")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "student_login";
    }

    @GetMapping("/student/homepage")
    public String showStudentHomePage() {
        return "student_homepage";
    }

    //Instructor Login
    @GetMapping("/instructorlogin")
    public String showInstructorLoginForm() {
        return "instructor_login";
    }

    //Admin Login
    @GetMapping("/adminlogin")
    public String showAdminLoginForm() {
        return "admin_login";
    }


    // Admin listing user
    @GetMapping("/admin/userlist")
    public ModelAndView getUserList(){
        ModelAndView mav = new ModelAndView("admin_list_users");
        List<User> users = userRepository.findAll();
        mav.addObject("users", users);
        return mav;
    }

    // Admin add and upadate user form
    @GetMapping("/admin/userform")
    ModelAndView addUser(){
        ModelAndView mav = new ModelAndView("admin_user_form");
        User newUser = new User();
        mav.addObject("user", newUser);
        return mav;
    }

    @PostMapping("/admin/userform")
    String saveArticle(@Valid @ModelAttribute User user, BindingResult result,RedirectAttributes redirectAttributes){
        // Check if username exists
        if(customUserDetailsService.usernameExists(user.getUsername())){
            result.addError(new FieldError("user", "username", "Username already exists, please choose another one"));
        }
        // Check if email exists
        if(customUserDetailsService.emailExists(user.getEmail())){
            result.addError(new FieldError("user", "email", "Email already exists, please choose another email"));
        }
        if (result.hasErrors()){
            return "admin_user_form";
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        redirectAttributes.addFlashAttribute("flashMessage", "Submit Success!");
        return "redirect:/admin/userlist";
    }

    @GetMapping("/admin/updateUser")
    public ModelAndView updateUser(@RequestParam Long id){
        ModelAndView mav = new ModelAndView("admin_user_form");
        User user = userRepository.findById(id).get();
        mav.addObject("user", user);
        return mav;
    }

    //Admin Delete User
    @GetMapping("/admin/deleteUser")
    public String deleteUser(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        userRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("flashMessage", "Delete Success!");
        return "redirect:/admin/userlist";
    }
}
