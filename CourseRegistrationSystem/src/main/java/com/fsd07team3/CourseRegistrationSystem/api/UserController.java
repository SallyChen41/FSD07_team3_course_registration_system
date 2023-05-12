package com.fsd07team3.CourseRegistrationSystem.api;

import ch.qos.logback.classic.Logger;
import com.fsd07team3.CourseRegistrationSystem.entity.User;
import com.fsd07team3.CourseRegistrationSystem.repository.UserRepository;
import com.fsd07team3.CourseRegistrationSystem.service.CustomUserDetailsService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    UserRepository userRepository;

//    @Autowired
//    private S3Service s3Service;

//    public UserController(CustomUserDetailsService customUserDetailsService){
//        this.customUserDetailsService = customUserDetailsService;
//        this.s3Service = s3Service;
//    }

    @GetMapping("/")
    public String homePage(){
//        String logoUrl = getObjectUrl("logo.png");
//        System.out.println(logoUrl);
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
        return "redirect:/login";
    }

    // Student Login
    @GetMapping("/login")
    public String showStudentLoginForm() {
        return "user_login";
    }


    @GetMapping("/login_err")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "user_login";
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

    @GetMapping("/student/myprofile")
    public String showProfile(Model model, Principal principal) {
        // Retrieve user information based on the currently authenticated user
        User user = userRepository.findByUsername(principal.getName());
        // Add user information to the model
        model.addAttribute("user", user);
        // Return the Thymeleaf template to be rendered
        return "student_myprofile";
    }

    @GetMapping("/student/updateprofile")
    public String showUpdateProfileForm(Model model, Principal principal) {
        // Retrieve user information based on the currently authenticated user
        User user = userRepository.findByUsername(principal.getName());

        // Add user information to the model
        model.addAttribute("user", user);

        // Return the Thymeleaf template to be rendered
        return "student_edit_myprofile";
    }

    @PostMapping("/student/updateprofile")
    public String updateProfile(@Valid @ModelAttribute("user") User updatedUser,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                Model model) {
        System.out.println(updatedUser);
        // Check for validation errors
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
            redirectAttributes.addFlashAttribute("user", updatedUser);
            return "redirect:/student/updateprofile";
        }
        // Update the user in the database
        User currentUser = userRepository.findByUsername(updatedUser.getUsername());
        System.out.println(currentUser);
        currentUser.setFirstName(updatedUser.getFirstName());
        currentUser.setLastName(updatedUser.getLastName());
        currentUser.setAddress(updatedUser.getAddress());
        currentUser.setEmail(updatedUser.getEmail());
        currentUser.setPhoneNum(updatedUser.getPhoneNum());
        currentUser.setPostalCode(updatedUser.getPostalCode());
        currentUser.setCity(updatedUser.getCity());

        userRepository.saveAndFlush(updatedUser);

        redirectAttributes.addFlashAttribute("flashMessage", "Update Profile Success!");
        return "redirect:/student/myprofile";
    }

    @GetMapping("/instructor/myprofile")
    public String showInstructorProfile(Model model, Principal principal) {
        // Retrieve user information based on the currently authenticated user
        User user = userRepository.findByUsername(principal.getName());
        // Add user information to the model
        model.addAttribute("user", user);
        // Return the Thymeleaf template to be rendered
        return "instructor_myprofile";
    }

    @GetMapping("/instructor/updateprofile")
    public String showUpdateInstructorProfileForm(Model model, Principal principal) {
        // Retrieve user information based on the currently authenticated user
        User user = userRepository.findByUsername(principal.getName());

        // Add user information to the model
        model.addAttribute("user", user);

        // Return the Thymeleaf template to be rendered
        return "instructor_edit_myprofile";
    }

    @PostMapping("/instructor/updateprofile")
    public String updateInstructorProfile(@Valid @ModelAttribute("user") User updatedUser,
                                          BindingResult bindingResult,
                                          RedirectAttributes redirectAttributes,
                                          Model model) {
        System.out.println(updatedUser);
        // Check for validation errors
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
            redirectAttributes.addFlashAttribute("user", updatedUser);
            return "redirect:/instructor/updateprofile";
        }
        // Update the user in the database
        User currentUser = userRepository.findByUsername(updatedUser.getUsername());
        System.out.println(currentUser);
        currentUser.setFirstName(updatedUser.getFirstName());
        currentUser.setLastName(updatedUser.getLastName());
        currentUser.setAddress(updatedUser.getAddress());
        currentUser.setEmail(updatedUser.getEmail());
        currentUser.setPhoneNum(updatedUser.getPhoneNum());
        currentUser.setPostalCode(updatedUser.getPostalCode());
        currentUser.setCity(updatedUser.getCity());

        userRepository.saveAndFlush(updatedUser);

        redirectAttributes.addFlashAttribute("flashMessage", "Update Profile Success!");
        return "redirect:/instructor/myprofile";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Perform any necessary logout operations
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}
