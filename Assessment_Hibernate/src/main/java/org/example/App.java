package org.example;

import java.util.List;
import java.util.Scanner;

import org.example.Entity.Menu_item;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class App {

    private static final SessionFactory factory =
            new Configuration().configure().buildSessionFactory();


    public static void addMenu(Menu_item m) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(m);
            tx.commit();
        }
    }




    public static void viewAll() {
        try (Session session = factory.openSession()) {
            List<Menu_item> items = session.createQuery("from Menu_item", Menu_item.class).list();
            items.forEach(System.out::println);
        }
    }


    public static void updatePrice(int id, double newPrice) {
        try (Session session = factory.openSession()) {

            Transaction tx = session.beginTransaction();

            Menu_item item = session.get(Menu_item.class, id);
                item.setPrice(newPrice);

                session.merge(item);

            tx.commit();
        }
    }





    public static void deleteItem(int id) {
        try (Session session = factory.openSession()) {

            Transaction tx = session.beginTransaction();

            Menu_item item = session.get(Menu_item.class, id);

                session.remove(item);
            tx.commit();
        }
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Add Menu Item");
            System.out.println("2. View All Items");
            System.out.println("3. Update Price");
            System.out.println("4. Delete Item");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Price:    ");
                     double price = sc.nextDouble();
                     sc.nextLine();

                    System.out.print("Category: ");
                    String category = sc.nextLine();

                     System.out.print("Available true or false: ");
                    boolean available = sc.nextBoolean();

                    Menu_item m = new Menu_item(name, price, category, available);
                    addMenu(m);
                    break;



                case 2:

                    viewAll();
                    break;

                case 3:
                    System.out.print("Enter ID: ");
                    int uid = sc.nextInt();

                    System.out.print("New Price: ");
                    double newPrice = sc.nextDouble();

                    updatePrice(uid, newPrice);
                    break;

                case 4:


                    System.out.print("Enter ID to delete: ");
                    int did = sc.nextInt();
                    deleteItem(did);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid Choice  ");
            }

        } while (choice != 5);

        sc.close();
        factory.close();
    }
}
