package cz.muni.fi.pa165.project.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * Person entity
 *
 * @author Patrik Majercik, Tomas Terem
 */
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "people")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String phoneNumber;

    @NotNull
    @Column(nullable = false)
    private String email;
}
