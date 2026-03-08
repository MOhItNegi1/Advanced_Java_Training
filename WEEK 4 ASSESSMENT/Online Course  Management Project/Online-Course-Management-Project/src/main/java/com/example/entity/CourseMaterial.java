package com.example.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseMaterial {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;



    private String fileName;



    private String fileType;


    private String fileUrl;

    private LocalDateTime uploadDate;



    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

}