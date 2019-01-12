package cz.muni.fi.pa165.project.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Delivery entity
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
    @EqualsAndHashCode.Exclude
    private Long id;

    @NotNull
    @Column(nullable = false)
    @Enumerated
    private DeliveryState deliveryState;
    
    @Nullable
    private LocalDate ordered;
    

    @Nullable
    private LocalDate delivered;
    
    @NotNull
    @Column(nullable = false)
    private BigDecimal price;
    
    @ManyToOne
    private Person courier;
    
    @ManyToOne
    private Person customer;
    
    //@OneToMany(cascade = CascadeType.ALL)
    @ManyToOne
    //@JoinColumn(name="fk_id")
    private Article article;
}
