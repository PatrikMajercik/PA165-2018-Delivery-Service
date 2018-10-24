package cz.muni.fi.pa165.project.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 * TODO: create javadoc *
 *
 * @author Patrik Majercik, Tomas Terem
 */
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "people")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE)
    private Long id;

    @OneToOne(mappedBy = "person")
    private Address address;

    @NotNull
    @Column(nullable = false)
    @Length(min = 3, max = 30)
    private String name;

    @NotNull
    @Column(nullable = false)
    @Pattern(regexp = "^\\+\\d{12}$")
    private String phoneNumber;

    @NotNull
    @Column(nullable = false)
    @Email
    private String email;
}
