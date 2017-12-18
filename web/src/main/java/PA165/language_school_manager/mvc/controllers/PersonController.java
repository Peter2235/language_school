package PA165.language_school_manager.mvc.controllers;

import PA165.language_school_manager.Facade.PersonFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
