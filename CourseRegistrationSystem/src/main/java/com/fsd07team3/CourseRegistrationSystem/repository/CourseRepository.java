package com.fsd07team3.CourseRegistrationSystem.repository;

import com.fsd07team3.CourseRegistrationSystem.entity.User;
import com.fsd07team3.CourseRegistrationSystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
    List<Course> findByInstructor(User instructor);
    List<Course> findBySemesterId(Long semesterId);
    @Query("SELECT c FROM Course c WHERE c.id NOT IN " +
            "(SELECT sr.course.id FROM StudentRegistration sr WHERE sr.student.id = :studentId) " +
            "OR (c.id IN (SELECT sr.course.id FROM StudentRegistration sr WHERE sr.student.id = :studentId AND sr.status = 'Dropped'))")
    List<Course> findAvailableCoursesForStudent(@Param("studentId") Long studentId);


}
