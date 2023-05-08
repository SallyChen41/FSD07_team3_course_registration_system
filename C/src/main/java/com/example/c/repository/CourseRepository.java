package com.example.c.repository;

import com.example.c.entity.Course;
import com.example.c.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByTitle(String title);
    List<Course> findByInstructor(User instructor);
    List<Course> findBySemesterId(Long semesterId);

}
