package org.example;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.example.Entity.*;

import java.util.*;

public class main {

    private static SessionFactory factory;


    public static void createData(Scanner sc) {

        try (Session session = factory.openSession()) {

            Transaction tx = session.beginTransaction();


            System.out.print("Enter Department Name: ");
            sc.nextLine();
            String deptName = sc.nextLine();

            Department dept = new Department();
            dept.setName(deptName);




            System.out.print("Enter number of students: ");
            int n = sc.nextInt();

            List<Student> students = new ArrayList<>();

            for (int i = 0; i < n; i++) {

                sc.nextLine();
                System.out.print("Enter Student Name: ");
                String name = sc.nextLine();

                Student s = new Student();
                s.setName(name);

                // Many-to-One
                s.setDepartment(dept);
                dept.getStudents().add(s);


                // ID Card
                System.out.print("Enter ID Card Number: ");
                String cardNum = sc.nextLine();

                IDCard card = new IDCard();
                card.setCardNumber(cardNum);

                s.setIdCard(card);

                students.add(s);
            }





            System.out.print("Enter number of courses: ");
            int c = sc.nextInt();

            List<Course> courses = new ArrayList<>();

            for (int i = 0; i < c; i++) {
                sc.nextLine();
                System.out.print("Enter Course Name: ");
                String cname = sc.nextLine();

                Course course = new Course();
                course.setCourseName(cname);

                courses.add(course);
            }



            for (Student s : students) {
                System.out.println("Assign courses to " + s.getName());

                System.out.print("How many courses? ");
                int count = sc.nextInt();

                List<Course> selected = new ArrayList<>();

                for (int i = 0; i < count; i++) {
                    System.out.print("Enter course index (0 to " + (courses.size()-1) + "): ");
                    int idx = sc.nextInt();

                    selected.add(courses.get(idx));
                }

                s.setCourses(selected);
            }


            session.persist(dept);

            tx.commit();
            System.out.println("Data Saved Successfully!");
        }
    }



    public static void viewStudents() {

        try (Session session = factory.openSession()) {

            List<Student> list =
                    session.createQuery("from Student", Student.class).list();

            for (Student s : list) {
                System.out.println("\nID: " + s.getId());
                System.out.println("Name: " + s.getName());

                if (s.getDepartment() != null)
                    System.out.println("Department: " + s.getDepartment().getName());

                if (s.getIdCard() != null)
                    System.out.println("Card: " + s.getIdCard().getCardNumber());

                if (s.getCourses() != null) {
                    System.out.print("Courses: ");
                    for (Course c : s.getCourses()) {
                        System.out.print(c.getCourseName() + " ");
                    }
                }
                System.out.println("\n----------------------");
            }
        }
    }



    public static void deleteStudent(Scanner sc) {

        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();

        try (Session session = factory.openSession()) {

            Transaction tx = session.beginTransaction();

            Student s = session.get(Student.class, id);

            if (s != null) {
                session.remove(s);
                System.out.println("Deleted Successfully!");
            } else {
                System.out.println("Student Not Found!");
            }

            tx.commit();
        }
    }


    public static void main(String[] args) {

        factory = new Configuration().configure()
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(IDCard.class)
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Create Data");
            System.out.println("2. View Students");
            System.out.println("3. Delete Student");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    createData(sc);
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    deleteStudent(sc);
                    break;

                case 4:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 4);

        sc.close();
        factory.close();
    }
}