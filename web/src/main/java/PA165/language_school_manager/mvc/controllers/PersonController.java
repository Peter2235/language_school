package PA165.language_school_manager.mvc.controllers;

import PA165.language_school_manager.DTO.PersonCreateDTO;
import PA165.language_school_manager.DTO.PersonDTO;
import PA165.language_school_manager.Facade.PersonFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

/**
 * @author Viktor Slany
 */

@Controller
@RequestMapping("/person")
public class PersonController {
    private final static Logger log = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonFacade personFacade;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        log.debug("list all students");
        model.addAttribute("persons", personFacade.getAllPersons());
        return "person/list";
    }

    /**
     * Detail of a person
     *
     * @param id Id of a person
     * @param model
     * @return /person/view
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewPerson(@PathVariable long id, Model model){
        model.addAttribute("person", personFacade.findPersonById(id));
        return "/person/view";
    }


/*    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newPerson(Model model){
        log.debug("Preparing new form for Person");
        model.addAttribute("personCreate", new PersonCreateDTO());
        return "person/new";
    }*/
/*    *//**
     * Create new person
     *
     * @return /person/list
     *//*
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("personCreate") PersonCreateDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(formBean={})", formBean);
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "person/new";
        }
        PersonDTO person = personFacade.createPerson(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "Person" + person.getId() + " was created");
        return "redirect:" + uriBuilder.path("/person/list").toUriString();
    }

    *//**
     * Delete peston
     *
     * @return /person/list
     *//*
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        PersonDTO personDTO = personFacade.findPersonById(id);
        personFacade.deletePerson(personDTO);
        redirectAttributes.addFlashAttribute("alert_success", "Person \"" + personDTO.getUserName() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/person/list").toUriString();
    }*/
}
