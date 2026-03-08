package com.example.service;

import com.example.dto.EnrollmentRequestDTO;
import com.example.dto.EnrollmentResponseDTO;
import com.example.entity.*;
import com.example.repository.CourseRepository;
import com.example.repository.EnrollmentRepository;
import com.example.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public EnrollmentResponseDTO enrollStudent(EnrollmentRequestDTO dto) {

        User student = userRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setStatus(EnrollmentStatus.ACTIVE);
        enrollment.setEnrollmentDate(LocalDateTime.now());

        Enrollment saved = enrollmentRepository.save(enrollment);

        EnrollmentResponseDTO response = new EnrollmentResponseDTO();
        response.setId(saved.getId());
        response.setCourseTitle(course.getTitle());
        response.setStudentName(student.getFullName());
        response.setStatus(saved.getStatus());
        response.setEnrollmentDate(saved.getEnrollmentDate());

        return response;
    }

    public List<Enrollment> getStudentEnrollments(Long studentId) {

        return enrollmentRepository.findByStudentId(studentId);
    }
    public List<Enrollment> getCourseEnrollments(Long courseId) {

        return enrollmentRepository.findByCourseId(courseId);
    }
}