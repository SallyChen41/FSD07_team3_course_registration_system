package com.example.c.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "courses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @NotEmpty(message="Title cannot be empty.")
    @Size(min=1, max=300, message="Title has to be 1-300 characters long.")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", nullable = false)
    private User instructor;

    @NotEmpty(message="Please provide course description.")
    @Size(min=10, max=200, message="Description has to be 10-200 characters long.")
    private String courseDescription;

    private String dayOfWeek;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotEmpty(message="Please choose the Start Date.")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotEmpty(message="Please choose the End Date.")
    private LocalDate endDate;

    @NotEmpty(message="Please choose the Start Time.")
    private int startTime;

    @NotEmpty(message="Please choose the End Time.")
    private int endTime;

    @NotEmpty(message="Please provide student limits for this course.")
    private int studentLimit;

}
