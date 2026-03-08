package com.example.controller;

import com.example.dto.EnrollmentRequestDTO;
import com.example.dto.EnrollmentResponseDTO;
import com.example.entity.Enrollment;
import com.example.service.EnrollmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping
    public EnrollmentResponseDTO enrollStudent(
            @RequestBody EnrollmentRequestDTO dto) {

        return enrollmentService.enrollStudent(dto);
    }
    @GetMapping("/student/{studentId}")
    public List<Enrollment> getStudentEnrollments(
            @PathVariable Long studentId){

        return enrollmentService.getStudentEnrollments(studentId);
    }
    @GetMapping("/course/{courseId}")
    public List<Enrollment> getCourseEnrollments(
            @PathVariable Long courseId){

        return enrollmentService.getCourseEnrollments(courseId);
    }

}