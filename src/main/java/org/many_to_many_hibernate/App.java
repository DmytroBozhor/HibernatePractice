package org.many_to_many_hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.many_to_many_hibernate.domain.Child;
import org.many_to_many_hibernate.domain.Section;
import org.one_to_many_hibernate_bi.domain.Department;
import org.one_to_many_hibernate_bi.domain.Employee;

public class App {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Child.class)
                .addAnnotatedClass(Section.class)
                .buildSessionFactory();

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Child child = Child.builder()
                    .name("Jack")
                    .age(14)
                    .build();

            Section section = Section.builder()
                    .name("Chess")
                    .build();

            child.getSections().add(section);
//            section.getChildren().add(child);

            session.persist(child);

            session.getTransaction().commit();
        }

        sessionFactory.close();
    }
}
