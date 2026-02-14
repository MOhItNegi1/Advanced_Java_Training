package org.example;


import java.util.Scanner;


import org.example.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class App {

    private static final SessionFactory factory =
            new Configuration().configure().buildSessionFactory();

    // CREATE
    public static void saveStudent(Student s) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(s);
            tx.commit();
        }
    }

    // READ BY ID
    public static Student getStudent(int id) {
        try (Session session = factory.openSession()) {
            return session.get(Student.class, id);
        }
    }

    // READ ALL
    public static void getAllStudents() {
        try (Session session = factory.openSession()) {
            List<Student> list =
                    session.createQuery("from Student", Student.class).list();
            list.forEach(System.out::println);
        }
    }

    // UPDATE
    public static void updateStudent(int id, int newAge) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Student s = session.get(Student.class, id);
            if (s != null) {
                s.setAge(newAge);
                session.merge(s);
            }
            tx.commit();
        }
    }



    // DELETE
    public static void deleteStudent(int id) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Student s = session.get(Student.class, id);
            if (s != null) session.remove(s);
            tx.commit();
        }
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. Add Student");
            System.out.println("2. View Student By ID");
            System.out.println("3. View All Students");
            System.out.println("4. Update Student Age");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Name: ");
                    sc.nextLine();
                    String name = sc.nextLine();

                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();

                    saveStudent(new Student(name, age));
                    System.out.println("Student Added!");
                    break;

                case 2:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    Student s = getStudent(id);
                    System.out.println(s != null ? s : "Student Not Found");
                    break;

                case 3:
                    getAllStudents();
                    break;

                case 4:
                    System.out.print("Enter ID: ");
                    int uid = sc.nextInt();

                    System.out.print("Enter New Age: ");
                    int newAge = sc.nextInt();

                    updateStudent(uid, newAge);
                    System.out.println("Updated!");
                    break;

                case 5:
                    System.out.print("Enter ID: ");
                    int did = sc.nextInt();

                    deleteStudent(did);
                    System.out.println("Deleted!");
                    break;

                case 6:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 6);




        sc.close();
        factory.close();
    }
}
