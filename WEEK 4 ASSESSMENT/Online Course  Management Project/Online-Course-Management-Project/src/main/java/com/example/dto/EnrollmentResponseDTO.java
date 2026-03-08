package com.example.dto;



import com.example.entity.EnrollmentStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EnrollmentResponseDTO {

    private Long id;

    private String courseTitle;

    private String studentName;

    private EnrollmentStatus status;

    private double progressPercentage;

    private LocalDateTime enrollmentDate;

}