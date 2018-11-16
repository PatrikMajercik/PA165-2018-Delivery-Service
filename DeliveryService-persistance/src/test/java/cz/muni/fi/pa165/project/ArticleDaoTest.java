package cz.muni.fi.pa165.project;


import cz.muni.fi.pa165.project.dao.ArticleDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ValidationException;

import cz.muni.fi.pa165.project.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static org.testng.Assert.*;

import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * Tests for ArticleDao
 *
 * @author Tomas Terem
 */
@ContextConfiguration(classes = cz.muni.fi.pa165.project.ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ArticleDaoTest extends AbstractTestNGSpringContextTests{
 
    @Autowired
    private ArticleDao articleDao;
    
    @PersistenceContext
    EntityManager em;

    @Test
    public void createArticle(){
        Article a = getSimpleArticle();
        articleDao.create(a);
        assertEquals(a, em.find(Article.class, a.getId()));
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullArticle(){
        Article a = null;
        articleDao.create(a);
    }

    @Test(expectedExceptions = ValidationException.class)
    public void createArticleWithId() {
        Article a = getSimpleArticle();
        articleDao.create(a); // id assigned
        articleDao.create(a); // exception thrown
    }
    
    @Test
    public void updateArticle(){
        Article a = getSimpleArticle();
        em.persist(a);
        a.setName("Snowboard");
        articleDao.update(a);
        assertEquals(a.getName(), em.find(Article.class, a.getId()).getName());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNullArticle(){
        articleDao.update(null);
    }

    @Test(expectedExceptions = ValidationException.class)
    public void updateArticleWithNullId() {
        Article a = getSimpleArticle();
        articleDao.update(a);
    }

    @Test
    public void deleteArticle(){
        Article a = getSimpleArticle();
        em.persist(a);
        articleDao.delete(a);
        assertNull(em.find(Article.class, a.getId()));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNullArticle(){
        articleDao.delete(null);
    }

    @Test(expectedExceptions = ValidationException.class)
    public void deleteArticleWithNullId() {
        Article a = getSimpleArticle();
        articleDao.delete(a);
    }

    @Test
    public void findById(){
        Article a = getSimpleArticle();
        em.persist(a);
        assertEquals(a, articleDao.findById(a.getId()));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByNullId(){
        Article a = articleDao.findById(null);
    }
    
    @Test
    public void findAll(){
        Article a1 = getSimpleArticle();
        Article a2 = getSimpleArticle();
        a2.setName("Snowboard");
        em.persist(a1);
        em.persist(a2);
        List<Article> list = articleDao.findAll();
        assertTrue(list.contains(a2));
        assertTrue(list.contains(a1));
    }

    private Article getSimpleArticle() {
        Article a = new Article();
        a.setName("Mountain bike");
        a.setWeight(new BigDecimal(15));
        return a;
    }
}
