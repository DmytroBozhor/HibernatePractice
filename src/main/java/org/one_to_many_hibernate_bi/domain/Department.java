package org.one_to_many_hibernate_bi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "min_salary")
    private Integer minSalary;

    @Column(name = "max_salary")
    private Integer maxSalary;

    //To avoid getting the foreign key exception, we must cascade all actions.
    //For instance, deleting department -> delete all employees, connected to the department
    //If we want to avoid deleting all employees we'd better create the "on default set null" constraint in the db.
    //We would also not want to load all the employees right away. So it is better to set fetch = FetchType.LAZY.
    @OneToMany(targetEntity = Employee.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "department")
    @ToString.Exclude
    @Builder.Default
    private List<Employee> employeeList = new ArrayList<>();
}
