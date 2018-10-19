package cz.muni.fi.pa165.project.entity;


import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * TODO: create javadoc *
 *
 * @author Patrik Majercik
 */

@Getter
@Setter
@EqualsAndHashCode
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE)
    private long id;

    private DeliveryState deliveryState;
    private Date ordered;
    private Date delivered;
    private BigDecimal price;
    private Person courier;
    private Person customer;
    private List<Article> articles;
}
