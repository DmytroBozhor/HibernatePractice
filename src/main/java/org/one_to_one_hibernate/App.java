package org.one_to_one_hibernate;

import org.one_to_one_hibernate.domain.Detail;
import org.one_to_one_hibernate.domain.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
