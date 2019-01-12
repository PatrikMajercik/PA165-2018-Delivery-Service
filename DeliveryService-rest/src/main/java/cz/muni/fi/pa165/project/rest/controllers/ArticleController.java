package cz.muni.fi.pa165.project.rest.controllers;

import cz.muni.fi.pa165.project.dto.ArticleDTO;
import cz.muni.fi.pa165.project.facade.ArticleFacade;
import cz.muni.fi.pa165.project.rest.exceptions.ResourceAlreadyExistingException;
import cz.muni.fi.pa165.project.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tomas Terem
 */
@RestController
@RequestMapping("/articles")
public class ArticleController {

    final static Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleFacade articleFacade;

    /**
     * Get list of Article
     * curl -i -X GET http://localhost:8080/pa165/rest/articles
     *
     * @return list of ArticleDTO
     */
    @Transactional
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ArticleDTO> findAll(){
        logger.debug("rest findAll()");
        return articleFacade.findAll();
    }

    /**
     *
     * Get Article by identifier id
     * curl -i -X GET http://localhost:8080/pa165/rest/articles/1
     *
     * @param id identifier for a article
     * @return ArticleDTO
     * @throws ResourceNotFoundException
     */
    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ArticleDTO getArticle(@PathVariable("id") long id) throws Exception {
        logger.debug("rest getArticle({})", id);
        ArticleDTO articleDTO = articleFacade.findById(id);
        if (articleDTO != null) {
            return articleDTO;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Delete specified article by id
     * curl -i -X DELETE http://localhost:8080/pa165/rest/articles/1
     *
     * @param id identifier for item
     * @throws ResourceNotFoundException
     */
    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteArticle(@PathVariable("id") long id) throws Exception {
        logger.debug("rest deleteArticle({})", id);
        try {
            articleFacade.delete(articleFacade.findById(id));
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Create a new item by POST method
     * curl -X POST -i -H "Content-Type: application/json" --data '{"name":"Book 8","weight":"1"}' http://localhost:8080/pa165/rest/articles/create
     *
     * @param article ArticleDTO with required fields for creation
     * @return the created article ArticleDTO
     * @throws ResourceAlreadyExistingException
     */
    @Transactional
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ArticleDTO createArticle(@RequestBody ArticleDTO article) throws ResourceAlreadyExistingException {

        logger.debug("rest createArticle()");
        try {
            Long id = articleFacade.create(article);
            return articleFacade.findById(id);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException();
        }
    }
}
