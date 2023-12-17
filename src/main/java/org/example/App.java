package org.example;

import org.example.domain.Detail;
import org.example.domain.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class App {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory();

        // We have to close the session to avoid connection leaks
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Employee employee = session.get(Employee.class, 20);
            System.out.println(employee);

            session.getTransaction().commit();
        }

        sessionFactory.close();
    }
}
