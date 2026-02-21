package org.company.ManyToMany;

import org.company.EntityManyToMany.Actor;
import org.company.EntityManyToMany.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManyToManyMain {

    public static void main(String[] args) {

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Actor.class);
        cfg.addAnnotatedClass(Movie.class);

        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Scanner sc = new Scanner(System.in);

        System.out.print("How many movies? ");
        int movieCount = sc.nextInt();
        sc.nextLine();

        List<Movie> movieList = new ArrayList<>();

        for (int i = 1; i <= movieCount; i++) {
            System.out.print("Enter Movie Title " + i + ": ");
            String title = sc.nextLine();
            movieList.add(new Movie(title));
        }

        System.out.print("How many actors? ");
        int actorCount = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= actorCount; i++) {

            System.out.print("Enter Actor Name: ");
            String actorName = sc.nextLine();
            Actor actor = new Actor(actorName);

            System.out.println("Select movies for " + actorName);
            for (int j = 0; j < movieList.size(); j++) {
                System.out.println((j + 1) + ". " + movieList.get(j).getTitle());
            }

            System.out.print("How many movies for this actor? ");
            int choiceCount = sc.nextInt();
            sc.nextLine();

            for (int k = 1; k <= choiceCount; k++) {
                System.out.print("Enter movie number: ");
                int index = sc.nextInt();
                sc.nextLine();

                Movie selectedMovie = movieList.get(index - 1);
                actor.addMovie(selectedMovie);
            }

            session.persist(actor);
        }

        tx.commit();
        session.close();
        factory.close();

        System.out.println("Data Saved Successfully!");
    }
}
