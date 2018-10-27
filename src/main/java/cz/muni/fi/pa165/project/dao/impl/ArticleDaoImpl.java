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
import javax.validation.constraints.NotNull;
import cz.muni.fi.pa165.project.dao.ArticleDao;

/**
 *
 * @author Jakub Gavlas
 */
public class ArticleDaoImpl implements ArticleDao{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void create(@NotNull Article article) {
        em.persist(article);
    }

    @Override
    public Article update(@NotNull Article article) {
        return em.merge(article);
    }

    @Override
    public void delete(@NotNull Article article) {
        em.remove(article);
    }

    @Override
    public Article findById(@NotNull long id) {
        return em.find(Article.class, id);
    }

    @Override
    public List<Article> findAll() {
        return Collections.unmodifiableList(em.createQuery("SELECT a FROM Article a", Article.class).getResultList());
    }
    
}
