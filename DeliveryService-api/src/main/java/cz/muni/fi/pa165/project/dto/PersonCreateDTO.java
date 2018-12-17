package cz.muni.fi.pa165.project.dto;

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
public class PersonCreateDTO {

    @EqualsAndHashCode.Exclude
    private Long id;

    private String name;
    private String phoneNumber;
    private String email;
    private Long addressId;
}
