package com.fsd07.S.service;

import com.fsd07.S.entity.CustomUserDetails;
import com.fsd07.S.entity.User;
import com.fsd07.S.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
