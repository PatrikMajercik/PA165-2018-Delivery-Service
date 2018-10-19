package cz.muni.fi.pa165.project.entity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * TODO: create javadoc *
 *
 * @author Patrik Majercik
 */
@Getter
@Setter
@EqualsAndHashCode
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE)
    private long id;
    private String postalCode;
    private String city;
    private String street;
    private String streetNumber;
}
