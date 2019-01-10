
package cz.fi.muni.pa165.project.mvc.controllers;

import cz.fi.muni.pa165.project.mvc.config.MySpringMvcConfig;
import cz.muni.fi.pa165.project.dto.*;

import cz.muni.fi.pa165.project.entity.Article;
import cz.muni.fi.pa165.project.entity.DeliveryState;
import cz.muni.fi.pa165.project.facade.ArticleFacade;
import cz.muni.fi.pa165.project.facade.DeliveryFacade;
import cz.muni.fi.pa165.project.facade.DeliveryFacadeImpl;
import cz.muni.fi.pa165.project.facade.PersonFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


/**
 * @author Patrik Majerčík
 */
@Controller
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryFacade deliveryFacade;

    @Autowired
    private PersonFacade personFacade;

    @Autowired
    private ArticleFacade articleFacade;

    @ModelAttribute("persons")
    public List<PersonDTO> persons() {
        log.debug("persons()");
        return personFacade.findAll();
    }

    @ModelAttribute("articles")
    public List<ArticleDTO> articles() {
        log.debug("articles()");
        return articleFacade.findAll();
    }
//    @ModelAttribute("state")
//    public DeliveryState[] states() {
//        log.debug("states()");
//        return DeliveryState.values();
//    }

    private final static Logger log = LoggerFactory.getLogger(DeliveryController.class);

    /**
     * @param model data to display
     * @return diplayed JSP page with category list
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        log.info(deliveryFacade.findAll().toString());
        log.info(articleFacade.findAll().toString());
        model.addAttribute("deliveries", deliveryFacade.findAll());
        return "delivery/list";
    }

    /**
     * Prepares an empty form.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newDelivery(Model model) {
        log.debug("new()");
        model.addAttribute("deliveryCreate", new DeliveryCreateDTO());
        return "delivery/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("deliveryCreate") DeliveryCreateDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        // in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "delivery/new";
        }

        deliveryFacade.create(formBean);

        redirectAttributes.addFlashAttribute("alert_success", "Delivery " + /*id +*/ " was created");
        return "redirect:" + uriBuilder.path("/delivery/list").toUriString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder,
                         RedirectAttributes redirectAttributes) {
        DeliveryDTO delivery = deliveryFacade.findById(id);
        deliveryFacade.delete(delivery);
        redirectAttributes.addFlashAttribute("alert_success", "Delivery \"" + delivery.getId() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/delivery/list").toUriString();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editPerson(
            @PathVariable Long id,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpServletRequest req,
            HttpServletResponse res) {

        DeliveryDTO deliveryDTO = deliveryFacade.findById(id);
        DeliveryEditDTO deliveryEditDTO = new DeliveryEditDTO();

        deliveryEditDTO.setId(deliveryDTO.getId());
        deliveryEditDTO.setArticle(deliveryDTO.getArticles().get(0));
        deliveryEditDTO.setCourierId(deliveryDTO.getCourier().getId());
        deliveryEditDTO.setCustomer(deliveryDTO.getCustomer());
        deliveryEditDTO.setDelivered(deliveryDTO.getDelivered());
        deliveryEditDTO.setOrdered(deliveryDTO.getOrdered());
        deliveryEditDTO.setDeliveryState(deliveryDTO.getDeliveryState());
        deliveryEditDTO.setPrice(deliveryDTO.getPrice());

        model.addAttribute("deliveryEdit", deliveryEditDTO);
        model.addAttribute("states", cz.muni.fi.pa165.project.enums.DeliveryState.values());
        return "delivery/edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String updatePerson(
            @PathVariable Long id,
            @ModelAttribute("deliveryEdit") DeliveryEditDTO deliveryEditDTO,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpServletRequest req,
            HttpServletResponse res) {

        DeliveryDTO deliveryDTO = deliveryFacade.findById(deliveryEditDTO.getId());
        deliveryDTO.setCourier(personFacade.findById(deliveryEditDTO.getCourierId()));
        deliveryDTO.setPrice(deliveryEditDTO.getPrice());
        deliveryDTO.setDeliveryState(deliveryEditDTO.getDeliveryState());
        try {
            deliveryFacade.update(deliveryDTO);
        } catch (Exception ex) {
            return "redirect:/delivery/edit/" + id;
        }

        redirectAttributes.addFlashAttribute("alert_success", "Person #" + id + " successfuly updated.");

        return "redirect:/delivery/list";

    }
}