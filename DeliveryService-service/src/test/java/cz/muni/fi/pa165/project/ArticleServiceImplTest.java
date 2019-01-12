package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.project.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.project.dao.ArticleDao;
import cz.muni.fi.pa165.project.entity.Article;
import org.hamcrest.Matcher;
import org.hibernate.service.spi.ServiceException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.hasItems;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * TODO: create javadoc *
 *
 * @author Patrik Majercik
 */
@ContextConfiguration(classes = {ServiceConfiguration.class})
public class ArticleServiceImplTest extends AbstractTransactionalTestNGSpringContextTests {
    @Mock
    ArticleDao articleDao;

    @InjectMocks
    @Autowired
    ArticleService articleService;

    Article article1;
    Article article2;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUp() throws Exception {
        article1 = new Article();
        article2 = new Article();

        article1.setName("Banana");
        article1.setWeight(new BigDecimal(1));

        article2.setName("Apple");
        article2.setWeight(new BigDecimal(2));
    }

    @Test
    public void testCreateArticle() {
        articleService.create(article1);
        verify(articleDao).create(article1);
    }

    @Test
    public void testRemoveArticle() {
        articleService.delete(article1);
        verify(articleDao).delete(article1);
    }

    @Test
    public void testFindById() {
        when(articleDao.findById(article1.getId())).thenReturn(article1);
        assertEquals(articleService.findById(article1.getId()), article1);
    }

    @Test
    public void testFindAll() {
        when(articleDao.findAll()).thenReturn(new ArrayList<>(asList(article2, article1)));
        assertTrue(articleService.findAll().contains(article1));
        assertTrue(articleService.findAll().contains(article2));
    }
    /*
    @Test
    public void testName() {
        when(articleDao.update(null)).thenThrow(new IllegalArgumentException());
        articleService.update(null);
    }*/

    @Test
    public void testUpdate() {
        articleService.update(article1);
        verify(articleDao).update(article1);
    }
}
