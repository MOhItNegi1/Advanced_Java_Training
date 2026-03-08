package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseRequestDTO {

    @NotBlank(message = "Title cannot be empty")
    private String title;

    private String description;

    @Positive(message = "Price must be greater than zero")
    private double price;

    private String duration;

    private String level;
}