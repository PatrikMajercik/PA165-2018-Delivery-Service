package cz.fi.muni.pa165.project.mvc.config.controllers;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cz.muni.fi.pa165.project.dto.PersonEditDTO;
import cz.muni.fi.pa165.project.dto.PersonDTO;
import cz.muni.fi.pa165.project.facade.AddressFacade;
import cz.muni.fi.pa165.project.facade.PersonFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Tomas Terem
 */
@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonFacade personFacade;

    private final AddressFacade addressFacade;

    @Autowired
    public PersonController(PersonFacade personFacade, AddressFacade addressFacade) {
        if(personFacade == null) {
            throw new IllegalArgumentException("Person facade cannot be null.");
        }
        if(addressFacade == null) {
            throw new IllegalArgumentException("Address facade cannot be null.");
        }
        this.personFacade = personFacade;
        this.addressFacade = addressFacade;
    }

    /**
     * List of persons
     * @param model data to display
     * @return      diplayed JSP page with category list
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("persons", personFacade.findAll());
        return "person/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAddress(Model model) {
        model.addAttribute("personCreate", new PersonEditDTO());
        model.addAttribute("addresses", addressFacade.findAll());
        return "person/new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("personCreate") PersonEditDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "person/new";
        }
        try {
            personFacade.create(formBean);
        } catch (PersistenceException ex) {
            return "redirect:/person/new";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("alert_danger", "Person could not be created. You probably entered invalid data. Try again.");
            return "redirect:/person/new";
        }

        // report success
        redirectAttributes.addFlashAttribute("alert_success", "Person was created");
        return "redirect:" + uriBuilder.path("/person/list").toUriString();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editPerson(
            @PathVariable Long id,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpServletRequest req,
            HttpServletResponse res) {

        PersonDTO personDTO = personFacade.findById(id);
        PersonEditDTO personEditDTO = new PersonEditDTO();
        personEditDTO.setAddress(personDTO.getAddress());
        personEditDTO.setEmail(personDTO.getEmail());
        personEditDTO.setName(personDTO.getName());
        personEditDTO.setPhoneNumber(personDTO.getPhoneNumber());
        personEditDTO.setId(personDTO.getId());
        personEditDTO.setAddressId(personDTO.getAddress().getId());

        model.addAttribute("person", personEditDTO);
        model.addAttribute("addresses", addressFacade.findAll());

        return "person/edit";
    }

    @RequestMapping(value = "/save/{id}", method = RequestMethod.POST)
    public String updatePerson(
            @PathVariable Long id,
            @ModelAttribute("person") PersonEditDTO personDTO,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpServletRequest req,
            HttpServletResponse res) {

        personDTO.setAddress(addressFacade.findById(personDTO.getAddressId()));
        try {
            personFacade.update(personDTO);
        } catch (PersistenceException ex) {
            return "redirect:/person/edit/" + id;
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("alert_danger", "Person #" + id + " could not be updated. Check if you entered correct data and try again.");
            return "redirect:/person/edit/" + id;
        }

        redirectAttributes.addFlashAttribute("alert_success", "Person #" + id + " successfuly updated.");

        return "redirect:/person/list";

    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder,
                         RedirectAttributes redirectAttributes) {
        PersonDTO person = personFacade.findById(id);
        try {
            personFacade.delete(person);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("alert_danger", "Cannot delete person assigned to a delivery. Edit/Remove the delivery and try again.");
            return "redirect:/person/list";
        }
        redirectAttributes.addFlashAttribute("alert_success", "Person \"" + person.getName() + " was deleted.");
        return "redirect:" + uriBuilder.path("/person/list").toUriString();
    }
}
