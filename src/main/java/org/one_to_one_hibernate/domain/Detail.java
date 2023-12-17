package org.one_to_one_hibernate.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "city")
    private String city;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @OneToOne(mappedBy = "empDetail",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Employee employee;
}
