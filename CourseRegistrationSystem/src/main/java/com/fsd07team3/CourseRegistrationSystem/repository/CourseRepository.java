package com.fsd07team3.CourseRegistrationSystem.repository;

import com.fsd07team3.CourseRegistrationSystem.entity.User;
import com.fsd07team3.CourseRegistrationSystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByTitle(String title);
    List<Course> findByInstructor(User instructor);

    List<Course> findBySemesterId(Long semesterId);

//    List<Course> findByStudentLimitGreaterThan(int studentLimit);
//
//    List<Course> findByStudent(User student);

}
