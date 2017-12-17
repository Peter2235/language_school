/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.mvc.controllers;

import PA165.language_school_manager.DTO.CourseDTO;
import PA165.language_school_manager.DTO.LectureDTO;
import PA165.language_school_manager.DTO.LecturerCreateDTO;
import PA165.language_school_manager.DTO.LecturerDTO;
import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.Facade.LectureFacade;
import PA165.language_school_manager.Facade.LecturerFacade;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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

/**
 *
 * @author Jan Safarik
 */
@Controller
@RequestMapping("/lecturer")
public class LecturerController {

    private final static Logger log = LoggerFactory.getLogger(LecturerController.class);

    @Autowired
    private LecturerFacade lecturerFacade;

    @Autowired
    private LectureFacade lectureFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        log.debug("list all lecturers");
        model.addAttribute("lecturers", lecturerFacade.findAllLecturers());
        return "lecturer/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newLecturer(Model model) {
        log.debug("Preparing new form for Lecturer");
        model.addAttribute("lecturerCreate", new LecturerCreateDTO());
        return "lecturer/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("lecturerCreate") LecturerCreateDTO formBean, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(lecturerCreate={})", formBean);
        if (bindingResult.hasErrors()) {
            return "lecturer/new";
        }
        Boolean zkouska = formBean.getNativeSpeaker();
        log.warn(zkouska.toString());
        Long id = lecturerFacade.createLecturer(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "Lecturer " + id + " was created");
        return "redirect:" + uriBuilder.path("/lecturer/view/{id}").buildAndExpand(id).encode().toUriString();
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewLecturer(@PathVariable long id, Model model) {
        LecturerDTO lecturer = lecturerFacade.findLecturerById(id);
        model.addAttribute("lecturer", lecturer);
        model.addAttribute("lectures", lectureFacade.findLecturesByLecturer(lecturer));
        return "/lecturer/view";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        if (!(lectureFacade.findLecturesByLecturer(lecturerFacade.findLecturerById(id)).isEmpty())) {
            return "redirect:" + uriBuilder.path("/lecturer/list").toUriString();
        }
        lecturerFacade.deleteLecturer(id);
        redirectAttributes.addFlashAttribute("alert_success", "Lecturer with id \"" + id + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/lecturer/list").toUriString();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editLecturer(@PathVariable long id, Model model, HttpServletRequest request, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        LecturerDTO lecturer = lecturerFacade.findLecturerById(id);
        model.addAttribute("lecturerEdit", lecturer);
        return "/lecturer/edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String updateLecturer(@PathVariable long id,
            @Valid @ModelAttribute("hunterEdit") LecturerDTO formBean,
            BindingResult bindingResult,
            Model model,
            UriComponentsBuilder uriBuilder,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request) {
        
        log.debug("update(lecturerCreate={})", formBean);
        if (bindingResult.hasErrors()) {
            return "lecturer/edit/"+id;
        }
        lecturerFacade.updateLecturer(formBean);
        return "redirect:" + uriBuilder.path("/lecturer/list").toUriString();
        
    }

    @ModelAttribute("languages")
    public Language[] languages() {
        log.debug("languages()");
        return Language.values();
    }

    @ModelAttribute("booleans")
    public Boolean[] booleans() {
        log.debug("booleans()");
        Boolean[] bla = {Boolean.FALSE, Boolean.TRUE};
        return bla;
    }

}
