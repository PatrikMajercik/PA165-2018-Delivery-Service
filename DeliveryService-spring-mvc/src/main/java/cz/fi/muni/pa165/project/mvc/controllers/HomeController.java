package cz.fi.muni.pa165.project.mvc.controllers;

import cz.muni.fi.pa165.project.dto.PersonDTO;
import cz.muni.fi.pa165.project.facade.PersonFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    @Inject
    private PersonFacade userFacade;

    /**
     * Get currently logged usser in the application
     * @return logged users DTO
     */
    @ModelAttribute("loggedUser")
    protected PersonDTO getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        String name = authentication.getName();

        if (name == null || name.equals("anonymousUser")) {
            return null;
        }

        PersonDTO user = userFacade.findPersonByEmail(name);
        return user;
    }

    /**
     * This method determines whether an user is admin or not
     * @param request request to process
     * @return true if user is admin, false otherwise
     */
    @ModelAttribute("isUserAdmin")
    protected boolean isUserAdmin(HttpServletRequest request) {
        return request.isUserInRole("ROLE_ADMIN");
    }

    /**
     * Method to process validation errors
     */
    protected void addValidationErrors(BindingResult bindingResult, Model model) {
        for (FieldError fe : bindingResult.getFieldErrors()) {
            model.addAttribute(fe.getField() + "_error", true);
        }
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        if (getLoggedUser() != null) {
            model.addAttribute("statistics", userFacade.toString());
        }
        return "home";
    }
}