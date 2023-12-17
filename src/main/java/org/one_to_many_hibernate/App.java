package org.one_to_many_hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.one_to_many_hibernate.domain.Department;
import org.one_to_many_hibernate.domain.Employee;

public class App {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class)
                .buildSessionFactory();

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Employee employee = Employee.builder()
                    .name("Marine")
                    .secondName("Hokins")
                    .salary(1450)
                    .build();

            Department department = Department.builder()
                    .departmentName("IT")
                    .minSalary(1000)
                    .maxSalary(2000)
                    .build();

            department.getEmployeeList().add(employee);
            employee.setDepartment(department);

            session.persist(department);

            session.getTransaction().commit();
        }

        sessionFactory.close();
    }
}
