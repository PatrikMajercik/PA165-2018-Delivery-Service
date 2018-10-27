package cz.muni.fi.pa165.project;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import cz.muni.fi.pa165.project.dao.ArticleDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;


import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author Jakub Gavlas
 */
@ContextConfiguration(classes = cz.muni.fi.pa165.project.ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@org.springframework.transaction.annotation.Transactional
public class ArticleDaoTest extends AbstractTestNGSpringContextTests{
 
    @Autowired
    private ArticleDao articleDao;
    
    @PersistenceContext
    EntityManager em;
    
    @Test
    public void createArticle(){
        
    }
    
    @Test
    public void createNullArticle(){
        
    }
    
    @Test
    public void updateArticle(){
        
    }
    
    @Test
    public void updateNullArticle(){
        
    }
    
    @Test
    public void removeArticle(){
        
    }
    
    @Test
    public void removeNullArticle(){
        
    }
    
    @Test
    public void getArticle(){
        
    }
    
    @Test
    public void getAllArticle(){
        
    }
}
