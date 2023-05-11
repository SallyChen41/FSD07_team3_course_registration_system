package com.fsd07team3.CourseRegistrationSystem.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

@Entity
@Table(name="user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,columnDefinition = "VARCHAR(255) default 'STUDENT'")
    @NotNull(message = "Role cannot be null")
    private String role;

    @Column(nullable = false, unique = true, length = 25)
    @NotEmpty(message="Username is required")
    @Size(min=4, max=25, message="Username must be between 4 and 25 characters")
    @Pattern(regexp = "^[a-z0-9]+$", message = "User name must only consist of lower case letters and numbers")
    private String username;

    @Column(nullable = false, length = 150)
    @NotNull(message = "Email is required")
    @Size(min = 7, max = 150, message = "Email must be between 7 and 150 characters")
    private String email;

    @Column(nullable = false, length = 150)
    @NotNull(message = "Password is required")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d|\\W).+$", message = "Password must contain at least one uppercase letter, one lower case letter, and one number or special character.")
    private String password;

    @Column(name = "FirstName", nullable = false, length = 100)
    @NotNull(message = "First name is required")
    @Size(min = 1, max = 100, message = "First name must be between 1 and 100 characters")
    private String firstName;

    @Column(name = "lastName", nullable = false, length = 100)
    @NotNull(message = "Last name is required")
    @Size(min = 1, max = 100, message = "Last name must be between 1 and 100 characters")
    private String lastName;

    @NotNull (message = "Birth date is required")
    @Past (message = "Birth date must be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

//    @AssertTrue(message = "User must be over 16 years old.")
//    public boolean isUserAbove16() {
//        LocalDate now = LocalDate.now();
//        int age = Period.between(dob, now).getYears();
//        return age >= 16;
//    }

    @Column(name = "phoneNum", nullable = false, length = 20)
    @NotNull(message = "Phone Number is required")
    @Length(min = 10, max = 10, message = "Phone Number must be 10 numbers")
    @Pattern(regexp="[0-9]+")
    private String phoneNum;

    @Column(nullable = false, length = 100)
    @NotNull (message = "Address is required")
    @Size(min = 1, max = 100, message = "Address must be between 1 and 100 characters")
    private String address;

    @Column(nullable = false, length = 50)
    @NotNull (message = "City is required")
    @Size(min = 1, max = 50, message = "City must be between 1 and 50 characters")
    private String city;

    @Column(name = "postalCode", nullable = false, length = 20)
    @NotNull (message = "Postal code is required")
    @Size(min = 1, max = 20, message = "Postal code must be between 1 and 20 characters")
    private String postalCode;

    @Column(nullable = true, length = 50)
    @Size(max=50)
    private String profession;

    @Column(name = "resetPasswordToken", unique = true)
    private String resetPasswordToken;

    public String getRole() {
        return role;
    }


}
