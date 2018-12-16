package cz.muni.fi.pa165.project.facade;

import cz.muni.fi.pa165.project.dto.ArticleDTO;

import java.util.List;

/**
 * TODO: create javadoc *
 *
 * @author Patrik Majercik
 */
public interface ArticleFacade {
    /**
     * Stores ArticleDTO
     *
     * @param articleDTO
     *
     */
    Long create(ArticleDTO articleDTO);

    /**
     * Get ArticleDTO with specified id.
     *
     * @param id
     * @return ArticleDTO with id
     */
    ArticleDTO findById(Long id);

    /**
     * Get all ArticleDTO as List
     *
     * @return List of ArticleDTO
     */
    List<ArticleDTO> findAll();

    /**
     * Updates the ArticleDTO.
     * @param articleDTO ArticleDTO to be updated.
     */
    void update(ArticleDTO articleDTO);

    /**
     * Remove ArticleDTO specified as parameter
     *
     * @param id
     */
    void delete(Long id);
}
