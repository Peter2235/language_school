/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.mvc.controllers;

import PA165.language_school_manager.DTO.LectureCreateDTO;
import javax.validation.Valid;
import PA165.language_school_manager.Facade.LectureFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import java.lang.StringBuilder;

/**
 *
 * @author Matúš
 */
@Controller
@RequestMapping("/lectures")
public class LectureController {

    final static Logger log = LoggerFactory.getLogger(LectureController.class);

    @Autowired
    private LectureFacade lectureFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("lectures", lectureFacade.findAllLectures());
        return "/lectures/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newLecture(Model model) {
        log.debug("new()");
        model.addAttribute("lectureCreate", new LectureCreateDTO());
        return "lecture/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("categoryCreate") LectureCreateDTO formBean, BindingResult bindingResult,
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
            return "lecture/new";
        }
        Long id = lectureFacade.createLecture(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "Lecture" + id + " was created");
        return "redirect: " + uriBuilder.path("lecture/list").toString();
    }
}
