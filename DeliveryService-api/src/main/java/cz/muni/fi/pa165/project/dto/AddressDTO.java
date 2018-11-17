package cz.muni.fi.pa165.project.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Address Data Transfer Object
 *
 * @author Tomas Terem
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AddressDTO {

    @EqualsAndHashCode.Exclude
    private Long id;

    private String postalCode;
    private String city;
    private String street;
    private String streetNumber;

}
