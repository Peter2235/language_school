/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.mvc.controllers;

import PA165.language_school_manager.DTO.LecturerCreateDTO;
import PA165.language_school_manager.Facade.LecturerFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Jan Safarik
 */

@Controller
@RequestMapping("/lecturers")
public class LecturerController {
    
    private final static Logger log = LoggerFactory.getLogger(LecturerController.class);
    
    @Autowired
    private LecturerFacade lecturerFacade;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model){
        log.debug("list all lecturers");
        model.addAttribute("lecturers", lecturerFacade.findAllLecturers());
        return "lecturers/list";
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newLecturer(Model model){
        log.debug("Preparing new form for Lecturer");
        model.addAttribute("lecturerCreate", new LecturerCreateDTO());
        return "lecturers/new";
    }
    
}
