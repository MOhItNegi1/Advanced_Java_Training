package com.example.SpringMVC.Repository;


import com.example.SpringMVC.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
//public class StudentDao {
//    public void saveStudent(Student student){
//        System.out.println("Saved to db: "+student.getName());
//    }
//}

@Repository
public interface StudentDao extends JpaRepository<Student,Long> {

}

