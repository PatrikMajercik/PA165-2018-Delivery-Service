package cz.fi.muni.pa165.project;

import cz.muni.fi.pa165.project.ArticleService;
import cz.muni.fi.pa165.project.entity.Article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


/**
 * @author Tomas Terem
 */
@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    @Autowired
    private ArticleService articleService;

    @Override
    public void loadData() {
        Article article1 = article("Book 1: Break with a Banshee", new BigDecimal(1));
        Article article2 = article("Book 2: Gadding with Ghouls", new BigDecimal(1));
        Article article3 = article("Book 3: Holidays with Hags", new BigDecimal(1));
        Article article4 = article("Book 4: Travels with Trolls", new BigDecimal(1));
        Article article5 = article("Book 5: Voyages with Vampires", new BigDecimal(2));
        Article article6 = article("Book 6: Wanderings with Werewolves", new BigDecimal(2));
        Article article7 = article("Book 7: Year with the Yeti", new BigDecimal(2));
    }

    private Article article(String name, BigDecimal weight) {
        Article article = new Article();
        article.setName(name);
        article.setWeight(weight);
        articleService.create(article);
        return article;
    }
}
