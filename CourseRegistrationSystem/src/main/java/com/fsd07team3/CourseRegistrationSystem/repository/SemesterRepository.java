package com.fsd07team3.CourseRegistrationSystem.repository;

import com.fsd07team3.CourseRegistrationSystem.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SemesterRepository  extends JpaRepository<Semester, Long> {

}
