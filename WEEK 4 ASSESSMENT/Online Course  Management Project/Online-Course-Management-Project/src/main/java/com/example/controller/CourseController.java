package com.example.controller;

import com.example.dto.CourseRequestDTO;
import com.example.dto.CourseResponseDTO;
import com.example.entity.Course;
import com.example.service.CourseService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/{instructorId}")
    public CourseResponseDTO createCourse(
            @PathVariable Long instructorId,
            @Valid @RequestBody CourseRequestDTO dto) {

        return courseService.createCourse(instructorId, dto);
    }

    @GetMapping
    public Page<Course> getCourses(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy){

        return courseService.getAllCourses(page,size,sortBy);
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id) {

        return courseService.getCourseById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {

        courseService.deleteCourse(id);
    }
    @PutMapping("/{id}")
    public CourseResponseDTO updateCourse(
            @PathVariable Long id,
            @RequestBody CourseRequestDTO dto){

        return courseService.updateCourse(id,dto);
    }
}