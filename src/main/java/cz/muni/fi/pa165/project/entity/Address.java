package cz.muni.fi.pa165.project.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * TODO: create javadoc *
 *
 * @author Patrik Majercik, Tomas Terem
 */
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String postalCode;

    @NotNull
    @Column(nullable = false)
    private String city;

    @NotNull
    @Column(nullable = false)
    private String street;

    @NotNull
    @Column(nullable = false)
    private String streetNumber;

    @OneToOne
    private Person person;
}
