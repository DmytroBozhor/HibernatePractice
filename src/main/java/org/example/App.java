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

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

//            List<Employee> employeeList = session.createQuery("from Employee").getResultList();
            List<Employee> employeeList = session.createNativeQuery("select * from employee").getResultList();
            System.out.println(employeeList);

            if (!employeeList.isEmpty()) {
                session.remove(employeeList.get(0));
            }

            session.getTransaction().commit();
        }

        sessionFactory.close();
    }
}
