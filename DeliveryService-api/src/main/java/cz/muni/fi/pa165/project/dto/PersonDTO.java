package cz.muni.fi.pa165.project.dto;

import cz.muni.fi.pa165.project.dto.AddressDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Person Data Transfer Object
 *
 * @author Tomas Terem
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PersonDTO {

    @EqualsAndHashCode.Exclude
    private Long id;

    private AddressDTO address;
    private String name;
    private String phoneNumber;
    private String email;
}
