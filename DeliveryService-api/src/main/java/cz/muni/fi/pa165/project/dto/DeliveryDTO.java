package cz.muni.fi.pa165.project.dto;

import cz.muni.fi.pa165.project.enums.DeliveryState;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Delivery DTO
 *
 * @author Jakub Gavlas
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DeliveryDTO {
        
    @EqualsAndHashCode.Exclude
    private Long id;
    private DeliveryState deliveryState;
    private LocalDateTime ordered;
    private LocalDateTime delivered;
    private BigDecimal price;
    private PersonDTO courier;
    private PersonDTO customer;
    //private List<ArticleDTO> articles;    
}
