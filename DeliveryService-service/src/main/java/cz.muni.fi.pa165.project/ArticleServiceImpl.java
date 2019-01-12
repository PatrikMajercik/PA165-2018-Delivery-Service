package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.project.dao.ArticleDao;
import cz.muni.fi.pa165.project.entity.Article;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Patrik Majercik
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Inject
    private ArticleDao articleDao;

    @Override
    public Long create(Article article) {
        return articleDao.create(article);
    }

    @Override
    public Article findById(Long id) {
        return articleDao.findById(id);
    }

    @Override
    public List<Article> findAll() {
        return articleDao.findAll();
    }

    @Override
    public void update(Article article) {
        articleDao.update(article);

    }

    @Override
    public void delete(Article article) {
        articleDao.delete(article);
    }
}
