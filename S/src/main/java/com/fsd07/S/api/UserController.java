package com.fsd07.S.api;

import com.fsd07.S.entity.User;
import com.fsd07.S.repository.UserRepository;
import com.fsd07.S.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/register")
    ModelAndView registerUser(){
        ModelAndView mav = new ModelAndView("register_page");
        User newUser = new User();
        mav.addObject("user", newUser);
        return mav;
    }

    @PostMapping("/register")
    public String saveUser(@Valid @ModelAttribute User user, BindingResult result){
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
        userRepository.save(user);
        return "login";
    }

    @GetMapping("/login")
    public String login() {
        return "student_login";
    }

    @PostMapping("/login")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}
