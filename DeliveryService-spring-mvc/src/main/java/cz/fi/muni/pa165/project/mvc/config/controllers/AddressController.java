package cz.fi.muni.pa165.project.mvc.config.controllers;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cz.muni.fi.pa165.project.dto.AddressDTO;
import cz.muni.fi.pa165.project.facade.AddressFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Tomas Terem
 */
@Controller
@RequestMapping("/address")
public class AddressController extends HomeController{

    private final AddressFacade addressFacade;

    @Autowired
    public AddressController(AddressFacade addressFacade){
        if(addressFacade == null){
            throw new IllegalArgumentException("Address facade cannot be null");
        }
        this.addressFacade = addressFacade;
    }

    /**
     * List of address
     * @param model data to display
     * @return      diplayed JSP page with category list
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("addresses", addressFacade.findAll());
        model.addAttribute("bestCity", addressFacade.findCityWithMostAddresses());
        return "address/list";
    }

    /**
     * Prepares an empty form.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAddress(Model model) {
        model.addAttribute("addressCreate", new AddressDTO());
        return "address/new";
    }

    @RequestMapping("/create")
    public String create(@ModelAttribute("addressCreate") AddressDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "address/new";
        }
        try {
            addressFacade.create(formBean);
        } catch (PersistenceException ex) {
            return "redirect:/address/new";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("alert_danger", "Address could not be created. You probably entered invalid data. Try again.");
            return "redirect:/address/new";
        }
        // report success
        redirectAttributes.addFlashAttribute("alert_success", "Address was created");
        return "redirect:" + uriBuilder.path("/address/list").toUriString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(
            @PathVariable Long id,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpServletRequest req,
            HttpServletResponse res) {

        AddressDTO addressDTO = addressFacade.findById(id);
        if (addressDTO == null) {
            redirectAttributes.addFlashAttribute("alert_danger", "Address #" + id + " does not exist");
            return "redirect:/address/list";
        }

        try {
            addressFacade.delete(addressFacade.findById(id));
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("alert_danger", "Cannot delete address assigned to a person. Edit/Remove the person and try again.");
            return "redirect:/address/list";
        }
        redirectAttributes.addFlashAttribute("alert_success", "Address #" + id + " was successfully removed");
        return "redirect:/address/list";

    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(
            @PathVariable Long id,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpServletRequest req,
            HttpServletResponse res) {

        AddressDTO addressDTO = addressFacade.findById(id);

        model.addAttribute("address", addressDTO);

        return "address/edit";
    }

    @RequestMapping(value = "/save/{id}", method = RequestMethod.POST)
    public String update(
            @PathVariable Long id,
            @ModelAttribute("address") AddressDTO addressDTO,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpServletRequest req,
            HttpServletResponse res) {


        try {
            addressFacade.update(addressDTO);
        } catch (PersistenceException ex) {
            return "redirect:/address/edit/" + id;
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("alert_danger", "Address #" + id + " could not be updated. Check if you entered correct data and try again.");
            return "redirect:/address/edit/" + id;
        }

        redirectAttributes.addFlashAttribute("alert_success", "Address #" + id + " successfuly updated.");

        return "redirect:/address/list";

    }
}
