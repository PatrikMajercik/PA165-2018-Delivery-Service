/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.Article;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Interface for Article DAO
 *
 * @author Jakub Gavlas
 */
public interface ArticleDao {

    /**
     * Creates new article
     *
     * @param article to create
     * @throws IllegalArgumentException if article is null
     * @throws ValidationException      if article id is not null
     */
    void create(@NotNull Article article) throws IllegalArgumentException, ValidationException;

    /**
     * Updates article
     *
     * @param article to update
     * @return updated article
     * @throws IllegalArgumentException if article is null
     * @throws ValidationException      if article id is null
     */
    Article update(@NotNull Article article) throws IllegalArgumentException, ValidationException;

    /**
     * Deletes article
     *
     * @param article to delete
     * @throws IllegalArgumentException if article is null
     * @throws ValidationException      if article id is null
     */
    void delete(@NotNull Article article) throws IllegalArgumentException, ValidationException;

    /**
     * Finds article by Id
     *
     * @param id of the article
     * @return article with id "id"(if exists)
     * @throws IllegalArgumentException if id is null
     */
    Article findById(@NotNull Long id) throws IllegalArgumentException;

    /**
     * Finds all articles
     *
     * @return List of all articles
     */
    List<Article> findAll();
}
