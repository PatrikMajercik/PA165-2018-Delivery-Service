/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.Article;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Interface for Article DAO
 * @author Jakub Gavlas
 */
public interface ArticleDao {
    
    /**
     * Creates new article
     * @param article to create
     */
    void create(@NotNull Article article);

    /**
     * Updates article
     * @param article to update
     * @return updated article
     */
    Article update(@NotNull Article article);

    /**
     * Deletes article
     * @param article to delete
     */
    void delete(@NotNull Article article);

    /**
     * Finds article by Id
     * @param id of the article
     * @return article with id "id"(if exists)
     */
    Article findById(@NotNull long id);

    /**
     * Finds all articles
     * @return List of all articles
     */
    List<Article> findAll();
}
