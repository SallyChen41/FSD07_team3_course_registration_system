package com.example.c.repository;

import com.example.c.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    List<User> findByRole(User.Role role);
    List<User> findByRole(String role);


}
