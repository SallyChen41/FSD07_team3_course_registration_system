package com.example.c.dto;

import com.example.c.entity.Course;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private Long id;
    private String title;
    private String start;
    private String end;

    public CourseDTO(Course course) {
        this.id = course.getId();
        this.title = course.getTitle();
        this.start = course.getStartDate().toString() + "T" + course.getStartTime() + ":00";
        this.end = course.getEndDate().toString() + "T" + course.getEndTime() + ":00";
    }

//    public CourseDTO(Course course) {
//        this.id = course.getId();
//        this.title = course.getTitle();
//        this.start = course.getStartDate().atTime(course.getStartTime()).toString();
//        this.end = course.getEndDate().atTime(course.getEndTime()).toString();
//    }

}

