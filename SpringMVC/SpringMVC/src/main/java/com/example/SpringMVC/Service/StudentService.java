package com.example.SpringMVC.Service;

import com.example.SpringMVC.Model.Student;
import com.example.SpringMVC.Repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentDao studentDAO;

    public void saveStudent(Student student){
        studentDAO.save(student);
    }
}

