package org.one_to_many_hibernate.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "salary")
    private Integer salary;

    //I don't want for example on deleting the employee to delete the department, too.
    //This is why I don't do any cascade actions.
    //And since an employee can only have one department, we can allow hibernate to fetch the department at once.
    @ManyToOne(targetEntity = Department.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    @ToString.Exclude
    private Department department;
}
