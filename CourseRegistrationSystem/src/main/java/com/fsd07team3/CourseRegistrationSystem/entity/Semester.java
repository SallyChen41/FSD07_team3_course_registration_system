package com.fsd07team3.CourseRegistrationSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "semesters")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message="Please provide year.")
    private String year;

    @Column(nullable = false)
    @NotEmpty(message="Please provide quarter.")
    private String quarter;

}
