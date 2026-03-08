package com.example.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CourseResponseDTO {

    private Long id;

    private String title;

    private String description;

    private double price;

    private String duration;

    private String level;

    private String instructorName;

    private LocalDateTime createdAt;

}