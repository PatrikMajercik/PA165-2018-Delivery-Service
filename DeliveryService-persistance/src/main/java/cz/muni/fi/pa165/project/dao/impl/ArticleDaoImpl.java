/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.project.dao.impl;

import cz.muni.fi.pa165.project.entity.Article;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;

import cz.muni.fi.pa165.project.dao.ArticleDao;
import org.springframework.stereotype.Repository;

/**
 * @author Jakub Gavlas
 */
@Repository
public class ArticleDaoImpl implements ArticleDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long create(@NotNull Article article) throws IllegalArgumentException, ValidationException {
        if (article == null) {
            throw new IllegalArgumentException("article is null");
        }
        if (article.getId() != null) {
            throw new ValidationException("article id cannot be set before creation");
        }
        em.persist(article);
        return article.getId();
    }

    @Override
    public void update(@NotNull Article article) throws IllegalArgumentException, ValidationException {

        if (article == null) {
            throw new IllegalArgumentException("article is null");
        }
        if (article.getId() == null) {
            throw new ValidationException("article id is null");
        }
        
        em.merge(article);
    }

    @Override
    public void delete(@NotNull Article article) throws IllegalArgumentException, ValidationException {
        if (article == null) {
            throw new IllegalArgumentException("article is null");
        }
        if (article.getId() == null) {
            throw new ValidationException("article id is null");
        }

        em.remove(findById(article.getId()));
    }

    @Override
    public Article findById(@NotNull Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        return em.find(Article.class, id);
    }

    @Override
    public List<Article> findAll() {
        return Collections.unmodifiableList(em.createQuery("SELECT a FROM Article a", Article.class).getResultList());
    }

}
