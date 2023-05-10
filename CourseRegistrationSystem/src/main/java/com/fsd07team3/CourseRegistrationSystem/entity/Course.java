package com.fsd07team3.CourseRegistrationSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

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
    @JoinColumn(name = "semester", nullable = false)
    private Semester semester;

    @NotEmpty(message="Title cannot be empty.")
    @Size(min=1, max=300, message="Title has to be 1-300 characters long.")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor", nullable = false)
    private User instructor;

    @NotEmpty(message="Please provide course description.")
    @Size(min=10, max=1000, message="Description has to be 10-1000 characters long.")
    private String courseDescription;

    private String dayOfWeek;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message="Please indicate Start Date.")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message="Please indicate End Date.")
    private LocalDate endDate;

    @NotEmpty(message="Please indicate Start Time.")
    private String startTime;

    @NotEmpty(message="Please indicate End Time.")
    private String endTime;

    @NotNull(message="Please provide student limits for this course.")
    private int studentLimit;

    private int available;

    @OneToMany(mappedBy = "course")
    private List<StudentRegistration> registrations;

    public void decrementAvailable() {
        this.available--;
    }
    public void incrementAvailable() {
        this.available++;
    }

    public Course(Semester semester, String title, User instructor, String courseDescription, String dayOfWeek, LocalDate startDate, LocalDate endDate, String startTime, String endTime, int studentLimit) {
        this.semester = semester;
        this.title = title;
        this.instructor = instructor;
        this.courseDescription = courseDescription;
        this.dayOfWeek = dayOfWeek;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.studentLimit = studentLimit;
        this.available = studentLimit; // set available to the same as studentLimit
    }

}
