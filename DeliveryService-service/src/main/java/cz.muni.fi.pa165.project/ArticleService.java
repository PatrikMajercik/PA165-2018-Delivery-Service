package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.project.entity.Article;

import java.util.List;

/**
 * TODO: create javadoc *
 *
 * @author Patrik Majercik
 */
public interface ArticleService {
    /**
     * Stores Article
     *
     * @param article
     *
     */
    void create(Article article);

    /**
     * Get Article with specified id.
     *
     * @param id
     * @return Article with id
     */
    Article findById(Long id);

    /**
     * Get all Article as List
     *
     * @return List of Article
     */
    List<Article> findAll();

    /**
     * Updates the Article.
     * @param article Article to be updated.
     */
    void update(Article article);

    /**
     * Remove Article specified as parameter
     *
     * @param article
     */
    void delete(Article article);
}
