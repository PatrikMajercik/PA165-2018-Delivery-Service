package cz.muni.fi.pa165.project.dto;

import cz.muni.fi.pa165.project.enums.DeliveryState;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDate;

/**
 * TODO: create javadoc *
 *
 * @author Patrik Majercik
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DeliveryEditDTO {
    @EqualsAndHashCode.Exclude
    private Long id;
    private DeliveryState deliveryState;
    private LocalDate ordered;
    private LocalDate delivered;
    private BigDecimal price;
    private Long courierId;
    private PersonDTO customer;
    private ArticleDTO article;

}
