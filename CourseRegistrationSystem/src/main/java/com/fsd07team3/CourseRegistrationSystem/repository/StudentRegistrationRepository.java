package com.fsd07team3.CourseRegistrationSystem.repository;

import com.fsd07team3.CourseRegistrationSystem.entity.Course;
import com.fsd07team3.CourseRegistrationSystem.entity.StudentRegistration;
import com.fsd07team3.CourseRegistrationSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRegistrationRepository  extends JpaRepository<StudentRegistration, Long> {

    List<StudentRegistration> findByStudentId(Long userId);
    StudentRegistration findByStudentAndCourse(User student, Course course);
    List<StudentRegistration> findByCourseId(Long courseId);

}
