package cz.muni.fi.pa165.project.entity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Article entity
 *
 * @author Patrik Majercik
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Article {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE)
    private Long id;
    
    @NotNull
    @Column(nullable = false)
    private String name;
    
    @NotNull
    @Column(nullable = false)
    private BigDecimal weight;
}
