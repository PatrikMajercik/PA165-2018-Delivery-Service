package cz.muni.fi.pa165.project.dto;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author Patrik Majercik
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ArticleDTO {
    @EqualsAndHashCode.Exclude
    private Long id;
    private String name;
    private BigDecimal weight;
}
