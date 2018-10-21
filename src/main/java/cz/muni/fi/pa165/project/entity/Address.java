package cz.muni.fi.pa165.project.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
    @Pattern(regexp = "^\\d{5}$")
    private String postalCode;

    @NotNull
    @Column(nullable = false)
    @Length(min = 3, max = 20)
    private String city;

    @NotNull
    @Column(nullable = false)
    @Length(min = 3, max = 20)
    private String street;

    @NotNull
    @Column(nullable = false)
    @Pattern(regexp = "^\\d{0,5}$")
    private String streetNumber;

    @OneToOne
    private Person person;
}
