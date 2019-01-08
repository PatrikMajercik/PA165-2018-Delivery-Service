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
import org.hibernate.validator.constraints.Length;

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
    @EqualsAndHashCode.Exclude
    private Long id;
    
    @NotNull
    @Column(nullable = false)
    @Length(min = 3, max = 20)
    private String name;
    
    @NotNull
    @Column(nullable = false)
    private BigDecimal weight;
}
