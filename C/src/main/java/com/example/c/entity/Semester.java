package com.example.c.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    private String year;

//    public enum Quarter {
//        Winter,
//        Spring,
//        Summer,
//        Fall
//    };
//
//    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
//    private Quarter quarter;

    @Column(nullable = false)
    private String quarter;

}