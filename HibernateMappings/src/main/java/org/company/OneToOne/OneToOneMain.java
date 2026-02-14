package org.company.OneToOne;

import org.company.EntityOneToOne.Person;
import org.company.EntityOneToOne.Passport;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class OneToOneMain {

    public static void main(String[] args) {

        // Hibernate Configuration
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Person.class);
        cfg.addAnnotatedClass(Passport.class);

        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Scanner sc = new Scanner(System.in);

        // Input Person
        System.out.println("Enter Person Name:");
        String name = sc.nextLine();

        // Input Passport
        System.out.println("Enter Passport Number:");
        int passNum = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.println("Enter Country:");
        String country = sc.nextLine();

        // Create Objects
        Passport passport = new Passport(passNum, country);
        Person person = new Person(name, passport);

        // Save Person (Passport auto-saved due to Cascade.ALL)
        session.persist(person);

        // Commit Transaction
        tx.commit();

        // Close
        session.close();
        factory.close();

        System.out.println("Data Saved Successfully!");
    }
}
