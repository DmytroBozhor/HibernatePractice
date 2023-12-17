package org.one_to_one_hibernate.domain;

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

    @Column(name = "department")
    private String department;

    @Column(name = "salary")
    private Integer salary;

    //    If we use cascade, then hibernate will automatically
    //    generate the same queries for other related entities, too.
    @OneToOne(targetEntity = Detail.class,
            cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "details_id")
    private Detail empDetail;
}
