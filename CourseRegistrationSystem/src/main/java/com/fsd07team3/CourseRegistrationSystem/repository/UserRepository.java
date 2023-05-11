package com.fsd07team3.CourseRegistrationSystem.repository;

import com.fsd07team3.CourseRegistrationSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findByRole(String role);

//    @Query("SELECT u FROM User u WHERE u.resetPasswordToken = :resetPasswordToken")
//    User findByResetPasswordToken(@Param("resetPasswordToken") String resetPasswordToken);
    User findByResetPasswordToken(String resetPasswordToken);


}
