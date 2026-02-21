package org.example.Entity;

import jakarta.persistence.*;
import java.util.*;

@Entity(name = "courses_name")
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String courseName;

    @ManyToMany(mappedBy = "courses")
    private List<Student> student = new ArrayList<>();

    public Course(int id, String courseName, List<Student> student) {
        this.id = id;
        this.courseName = courseName;
        this.student = student;
    }

    public Course(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }
}
