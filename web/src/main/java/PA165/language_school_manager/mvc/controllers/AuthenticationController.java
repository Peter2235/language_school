/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.mvc.controllers;

import PA165.language_school_manager.DTO.PersonAuthenticateDTO;
import PA165.language_school_manager.DTO.PersonDTO;
import PA165.language_school_manager.Facade.PersonFacade;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.hibernate.validator.internal.metadata.raw.ExecutableElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Matúš
 */
@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    private final static Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private PersonFacade personFacade;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String loginUser(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("person") != null) {
            return "redirect:" + request.getHeader("Referer");
        }
        log.debug("[AUTH] login");
        model.addAttribute("personLogin", new PersonAuthenticateDTO());
        return "/auth/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid @ModelAttribute("personLogin") PersonAuthenticateDTO formBean, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.debug("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.debug("FieldError: {}", fe);
            }
            model.addAttribute("personLogin", new PersonAuthenticateDTO());
            return "/auth/login";
        }

        PersonDTO matchingP = personFacade.findPersonByUserName(formBean.getUserName());
        if (matchingP == null) {
            log.warn("no person with userName {}", formBean.getUserName());
            redirectAttributes.addFlashAttribute("alert_warning", "No person with userName: " + formBean.getUserName());
            return "redirect:" + uriBuilder.path("/auth").build().toUriString();
        }

        if (!personFacade.authenticate(formBean)) {
            log.warn("wrong credentials: person={} password={}", formBean.getUserName(), formBean.getPassword());
            redirectAttributes.addFlashAttribute("alert_warning", "Login " + formBean.getUserName() + " failed ");
            return "redirect:" + uriBuilder.path("/auth").build().toUriString();
        }

        if (!personFacade.isAdmin(matchingP)) {
            request.getSession().setAttribute("person", matchingP);
            redirectAttributes.addFlashAttribute("alert_success", "Log " + formBean.getUserName() + " successful");
            return "home";
        } else {
            request.getSession().setAttribute("admin", matchingP);
            redirectAttributes.addFlashAttribute("alert_success", "Log " + formBean.getUserName() + " successful");
            return "home";
        }

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutUser(Model model, HttpServletRequest request) {
        log.debug("[AUTH] Logout");
        if (request.getSession().getAttribute("person") == null) {
            if (request.getSession().getAttribute("admin") == null) {
                return "home";
            } else {
                request.getSession().removeAttribute("admin");
            }
        }
        request.getSession().removeAttribute("person");
        return "home";
    }
}
