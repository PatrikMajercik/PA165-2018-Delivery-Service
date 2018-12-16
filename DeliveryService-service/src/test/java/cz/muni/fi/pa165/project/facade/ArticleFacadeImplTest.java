package cz.muni.fi.pa165.project.facade;

import cz.muni.fi.pa165.project.ArticleService;
import cz.muni.fi.pa165.project.BeanMappingService;
import cz.muni.fi.pa165.project.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.project.dao.ArticleDao;
import cz.muni.fi.pa165.project.dto.ArticleDTO;
import cz.muni.fi.pa165.project.entity.Article;
import org.mockito.Spy;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.hasItems;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * TODO: create javadoc *
 *
 * @author Patrik Majercik
 */
@ContextConfiguration(classes = {ServiceConfiguration.class})
public class ArticleFacadeImplTest extends AbstractTestNGSpringContextTests {
    @Mock
    private ArticleService articleService;

    @Spy
    @Inject
    private BeanMappingService beanMappingService;

    @InjectMocks
    private ArticleFacade articleFacade = new ArticleFacadeImpl();

    private ArticleDTO articleDTO1;
    private ArticleDTO articleDTO2;
    private Article article1;
    private Article article2;

    @BeforeMethod
    public void setup() {

        article1 = new Article();
        article2 = new Article();

        article1.setName("Banana");
        article1.setWeight(new BigDecimal(1));

        article2.setName("Apple");
        article2.setWeight(new BigDecimal(2));
        this.articleDTO1 = beanMappingService.mapTo(article1, ArticleDTO.class);
        this.articleDTO2 = beanMappingService.mapTo(article2, ArticleDTO.class);
        MockitoAnnotations.initMocks(this);
        return;
    }


    @Test
    public void testCreateArticle() {
        articleFacade.create(articleDTO1);
        verify(articleService).create(article1);
    }

    @Test
    public void testRemoveArticle() {
        articleFacade.delete(articleDTO1.getId());
        verify(articleService).delete(article1.getId());
    }

    @Test
    public void testFindById() {
        when(articleService.findById(article1.getId())).thenReturn(article1);
        assertEquals(articleFacade.findById(articleDTO1.getId()), articleDTO1);
    }

    @Test
    public void testFindAll() {
        when(articleService.findAll()).thenReturn(new ArrayList<>(asList(article1, article2)));
        assertTrue(articleFacade.findAll().contains(articleDTO1));
        assertTrue(articleFacade.findAll().contains(articleDTO2));
    }

    @Test
    public void testUpdate() {
        articleFacade.update(articleDTO1);
        verify(articleService).update(article1);
    }
}
