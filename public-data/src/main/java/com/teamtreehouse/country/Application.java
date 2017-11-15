package com.teamtreehouse.country;

import com.teamtreehouse.country.model.Country;
import com.teamtreehouse.country.model.Country.CountryBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Application {

    // Hold a reusable reference to a sessionFactory
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        // Create a StandardServiceRegistry
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static void main(String[] args) {

        Country country = new CountryBuilder("sam")
                .withInternetUsers(9.8f)
                .withAdultLiteracyRat(7.7f)
                .build();

        String id = addCountry(country);

        System.out.println("\nBefore Update...\n");
        fetchAllLanguages().forEach(System.out::println);

        Country c = findCountryById(id);
        c.setName("Josh");
        c.setInternetUsers(5.51f);
        c.setAdultLiteracyRat(6.6f);

        System.out.println("\nUpdating...\n");
        updateCountry(c);
        System.out.println("\nUpdate Complete\n");

        System.out.println("\nAfter Update\n");
        fetchAllLanguages().forEach(System.out::println);

        System.out.println("\nDeleting Country...\n");
        deleteCountry(c);
        System.out.println("\nDelete Complete\n");

        System.out.println("\nAfter Delete\n");
        fetchAllLanguages().forEach(System.out::println);
    }

    private static String addCountry(Country country) {
        // Open a session
        Session session = sessionFactory.openSession();

        // Begin a transaction
        session.beginTransaction();

        // Save country object
        String c = (String) session.save(country);

        // Commit the transaction
        session.getTransaction().commit();

        // Close session
        session.close();

        return c ;
    }

    private static List<Country> fetchAllLanguages() {

        // Open a Session
        Session session = sessionFactory.openSession();

        // Create a CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create a CriteriaQuery
        CriteriaQuery<Country> criteria = builder.createQuery(Country.class);

        // Specify criteria root
        criteria.from(Country.class);

        // Execute query
        List<Country> countries = session.createQuery(criteria).getResultList();

        // Close the session
        session.close();

        return countries;
    }

    private static void updateCountry(Country country) {

        // Open a session
        Session session = sessionFactory.openSession();

        // Begin a transaction
        session.beginTransaction();

        // Use the session to update the country object
        session.update(country);

        // Commit the transaction
        session.getTransaction().commit();

        // Close the session
        session.close();
    }

    private static void deleteCountry(Country country) {

        // Open a session
        Session session = sessionFactory.openSession();

        // Begin a transaction
        session.beginTransaction();

        // Use the session to delete the country object
        session.delete(country);

        // Commit the transaction
        session.getTransaction().commit();

        // Close the session
        session.close();
    }

    private static Country findCountryById(String id) {

        // Open a session
        Session session = sessionFactory.openSession();

        // Retrieve the persistent object (or null if not found)
        Country country = session.get(Country.class, id);

        // Close the session
        session.close();

        return country;
    }
}
