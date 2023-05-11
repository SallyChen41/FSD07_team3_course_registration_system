package com.fsd07team3.CourseRegistrationSystem.service;

import com.fsd07team3.CourseRegistrationSystem.config.UserNotFoundException;
import com.fsd07team3.CourseRegistrationSystem.entity.CustomUserDetails;
import com.fsd07team3.CourseRegistrationSystem.entity.User;
import com.fsd07team3.CourseRegistrationSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Transactional
    public boolean emailExists(String email){
        User findUser = userRepository.findByEmail(email);
        return (findUser != null);
    }

    @Transactional
    public boolean usernameExists(String username){
        User findUser = userRepository.findByUsername(username);
        return (findUser != null);
    }

    public void updateResetPasswordToken(String resetPasswordToken, String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setResetPasswordToken(resetPasswordToken);
            userRepository.save(user);
        } else {
            throw new UserNotFoundException("Could not find any user with this email: " + email);
        }
    }
    public User findByResetPasswordToken(String resetPasswordToken) {
        return userRepository.findByResetPasswordToken(resetPasswordToken);
    }

    public void updatePassword(User user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);
        userRepository.save(user);
    }


}

