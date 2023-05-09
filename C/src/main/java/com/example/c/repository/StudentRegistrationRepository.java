package com.example.c.repository;

import com.example.c.entity.Course;
import com.example.c.entity.StudentRegistration;
import com.example.c.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRegistrationRepository  extends JpaRepository<StudentRegistration, Long> {

    List<StudentRegistration> findByStudentId(Long userId);
    StudentRegistration findByStudentAndCourse(User student, Course course);

}
