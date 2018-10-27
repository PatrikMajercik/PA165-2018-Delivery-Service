package cz.muni.fi.pa165.project.entity;


import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * TODO: create javadoc *
 *
 * @author Patrik Majercik
 */

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Delivery {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(nullable = false)
    @Enumerated
    private DeliveryState deliveryState;
    
    @NotNull
    @Column(nullable = false)
    private LocalDateTime ordered;
    
    @NotNull
    @Column(nullable = false)
    private LocalDateTime delivered;
    
    @NotNull
    @Column(nullable = false)
    private BigDecimal price;
    
    @NotNull
    @Column(nullable = false)
    @ManyToOne
    private Person courier;
    
    @NotNull
    @Column(nullable = false)
    @ManyToOne
    private Person customer;
    
    @NotNull
    @Column(nullable = false)
    @OneToOne
    private List<Article> articles;
}
