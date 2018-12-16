package cz.muni.fi.pa165.project.facade;

import cz.muni.fi.pa165.project.ArticleService;
import cz.muni.fi.pa165.project.BeanMappingService;
import cz.muni.fi.pa165.project.dto.ArticleDTO;
import cz.muni.fi.pa165.project.entity.Article;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Patrik Majercik
 */
@Service
@Transactional
public class ArticleFacadeImpl implements ArticleFacade {

    @Inject
    private ArticleService articleService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public Long create(ArticleDTO articleDTO) {
        Article mappedArticle = beanMappingService.mapTo(articleDTO, Article.class);
        return articleService.create(mappedArticle);
    }

    @Override
    public ArticleDTO findById(Long id) {
        return beanMappingService.mapTo(articleService.findById(id), ArticleDTO.class);
    }

    @Override
    public List<ArticleDTO> findAll() {
        return beanMappingService.mapTo(articleService.findAll(), ArticleDTO.class);
    }

    @Override
    public void update(ArticleDTO articleDTO) {
        Article mappedArticle = beanMappingService.mapTo(articleDTO, Article.class);
        articleService.update(mappedArticle);
    }

    @Override
    public void delete(Long id) {
        articleService.delete(id);
    }
}
