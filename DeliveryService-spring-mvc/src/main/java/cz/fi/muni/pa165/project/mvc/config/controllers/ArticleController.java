package cz.fi.muni.pa165.project.mvc.config.controllers;

import cz.muni.fi.pa165.project.dto.ArticleDTO;
import cz.muni.fi.pa165.project.facade.ArticleFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SpringMVC Controller for administering articles.
 *
 * @author Jakub Gavlas
 */
@Controller
@RequestMapping("/article")
public class ArticleController extends HomeController{

    private final static Logger log = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleFacade articleFacade;


    /**
     * Shows a list of articles with the ability to add, delete or edit.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("articles", articleFacade.findAll());
        return "article/list";
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newArticle(Model model) {
        model.addAttribute("articleCreate", new ArticleDTO());
      
        return "article/new";
    }
    
    @RequestMapping("/create")
    public String createArticle(@ModelAttribute("articleCreate") ArticleDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "article/new";
        }
        try {
            articleFacade.create(formBean);
        } catch (PersistenceException ex) {
            return "redirect:/article/new";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("alert_danger", "Article could not be created. You probably entered invalid data. Try again.");
            return "redirect:/article/new";
        }
        // report success
        redirectAttributes.addFlashAttribute("alert_success", "Article was created");
        return "redirect:" + uriBuilder.path("/article/list").toUriString();
    }
    
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editArticle(
            @PathVariable Long id,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpServletRequest req,
            HttpServletResponse res) {
       
        ArticleDTO articleDTO = articleFacade.findById(id);

        model.addAttribute("article", articleDTO);

        return "article/edit";
    }
    
    @RequestMapping(value = "/save/{id}", method = RequestMethod.POST)
    public String updateArticle(
            @PathVariable Long id,
            @ModelAttribute("article") ArticleDTO articleDTO,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpServletRequest req,
            HttpServletResponse res) {
       
        try {
            articleFacade.update(articleDTO);
        } catch (PersistenceException ex) {            
            return "redirect:/article/edit/" + id;
        } catch (Exception ex) {       
            redirectAttributes.addFlashAttribute("alert_danger", "Article #" + id + " could not be updated (internal error)"+ ex.getMessage());
            return "redirect:/article/edit/" + id;
        }

        redirectAttributes.addFlashAttribute("alert_success", "Article #" + id + " successfuly updated.");

        return "redirect:/article/list";

    }
    
    
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(
            @PathVariable Long id,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpServletRequest req,
            HttpServletResponse res) {

         ArticleDTO articleDTO = articleFacade.findById(id);
        if (articleDTO == null) {
            redirectAttributes.addFlashAttribute("alert_danger", "Article #" + id + " does not exist");
            return "redirect:/article/list";
        }

         try {
            articleFacade.delete(articleFacade.findById(id));
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("alert_danger", "Cannot delete article assigned to a delivery. Edit/Remove the delivery and try again.");
            return "redirect:/article/list";
        }
        redirectAttributes.addFlashAttribute("alert_success", "Article #" + id + " was successfully removed");
        return "redirect:/article/list";

     }
    

}