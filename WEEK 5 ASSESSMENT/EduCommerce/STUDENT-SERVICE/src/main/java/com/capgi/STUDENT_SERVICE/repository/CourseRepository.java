package com.capgi.STUDENT_SERVICE.repository;

import com.capgi.STUDENT_SERVICE.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
