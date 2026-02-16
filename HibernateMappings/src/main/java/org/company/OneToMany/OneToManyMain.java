package org.company.OneToMany;

import org.company.EntityOneToMany.Customer;
import org.company.EntityOneToMany.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OneToManyMain {

    public static void main(String[] args) {

        // Hibernate Configuration
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Customer.class);
        cfg.addAnnotatedClass(Order.class);

        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Customer Name: ");
        String cname = sc.nextLine();

        Customer customer = new Customer(cname);

        System.out.print("How many orders? ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        List<Order> orderList = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            System.out.print("Enter Order Date " + i + ": ");
            String date = sc.nextLine();

            Order order = new Order(date);

            // set relationship BOTH SIDES
            order.setCustomer(customer);
            orderList.add(order);
        }

        // set orders to customer
        customer.setOrders(orderList);

        // SAVE
        session.persist(customer);   // cascade will save orders

        tx.commit();
        session.close();
        factory.close();

        System.out.println("Data Saved Successfully!");
    }
}
