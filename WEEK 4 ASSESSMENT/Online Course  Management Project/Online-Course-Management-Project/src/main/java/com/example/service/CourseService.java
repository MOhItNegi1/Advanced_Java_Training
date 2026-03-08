package com.example.service;

import com.example.dto.CourseRequestDTO;
import com.example.dto.CourseResponseDTO;
import com.example.entity.Course;
import com.example.entity.User;
import com.example.repository.CourseRepository;
import com.example.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @CacheEvict(value = "courses", allEntries = true)
    public CourseResponseDTO createCourse(Long instructorId, CourseRequestDTO dto) {

        User instructor = userRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        Course course = new Course();
        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setPrice(dto.getPrice());
        course.setDuration(dto.getDuration());
        course.setLevel(dto.getLevel());
        course.setInstructor(instructor);

        Course saved = courseRepository.save(course);

        CourseResponseDTO response = new CourseResponseDTO();
        response.setId(saved.getId());
        response.setTitle(saved.getTitle());
        response.setDescription(saved.getDescription());
        response.setPrice(saved.getPrice());
        response.setDuration(saved.getDuration());
        response.setLevel(saved.getLevel());
        response.setInstructorName(instructor.getFullName());

        return response;
    }

    @Cacheable("courses")
    public Page<Course> getAllCourses(int page, int size, String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return courseRepository.findAll(pageable);
    }

    public Course getCourseById(Long id) {

        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @CacheEvict(value = "courses", allEntries = true)
    public void deleteCourse(Long id) {

        courseRepository.deleteById(id);

    }

    @CacheEvict(value = "courses", allEntries = true)
    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO dto){

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setPrice(dto.getPrice());
        course.setDuration(dto.getDuration());
        course.setLevel(dto.getLevel());

        Course saved = courseRepository.save(course);

        CourseResponseDTO response = new CourseResponseDTO();
        response.setId(saved.getId());
        response.setTitle(saved.getTitle());
        response.setDescription(saved.getDescription());
        response.setPrice(saved.getPrice());
        response.setDuration(saved.getDuration());
        response.setLevel(saved.getLevel());

        return response;
    }
}