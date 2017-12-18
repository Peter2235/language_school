/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.mvc.controllers;

import PA165.language_school_manager.DTO.*;
import PA165.language_school_manager.Facade.CourseFacade;
import PA165.language_school_manager.Facade.LectureFacade;
import PA165.language_school_manager.Facade.LecturerFacade;
import PA165.language_school_manager.Facade.PersonFacade;
import PA165.language_school_manager.mvc.forms.LectureCreateDTOValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Controller for lecture
 *
 * @author Matúš
 */
@Controller
@RequestMapping("/lecture")
public class LectureController {

    final static Logger log = LoggerFactory.getLogger(LectureController.class);

    @Autowired
    private LectureFacade lectureFacade;

    @Autowired
    private CourseFacade courseFacade;

    @Autowired
    private LecturerFacade lecturerFacade;
    
    @Autowired
    private PersonFacade personFacade;

    /**
     * List all lectures
     *
     * @return /lecture/list
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("lectures", lectureFacade.findAllLectures());
        return "/lecture/list";
    }

    /**
     * Detail of a lecture
     *
     * @param id id of lecture
     * @return /lecture/view
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewLecture(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder) {
        log.debug("view({})", id);
        //model.addAttribute("persons", lectureFacade.findLectureById(id).getPersons());
        model.addAttribute("lecture", lectureFacade.findLectureById(id));
        model.addAttribute("localDateTimeFormat", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        log.warn(lectureFacade.findLectureById(id).getPersons().toString());
        return "lecture/view";
    }

    /**
     * Delete lecture by id
     *
     * @param id id of lecture
     * @return lecture/list
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        LectureDTO lecture = lectureFacade.findLectureById(id);
        lectureFacade.deleteLecture(lecture);
        log.debug("delete({})", id);
        redirectAttributes.addFlashAttribute("alert_success", "Lecture \"" + lecture.getTopic() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/lecture/list").toUriString();
    }

    /**
     * Form for new lecture
     *
     * @return lecture/new
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newLecture(Model model) {
        log.debug("new()");
        model.addAttribute("lectureCreate", new LectureCreateDTO());
        return "lecture/new";
    }
    
    @ModelAttribute("persons")
    public List<PersonDTO> persons(){
        log.debug("persons()");
        return personFacade.getAllPersons();
    }
    
    @ModelAttribute("courses")
    public List<CourseDTO> courses() {
        log.debug("courses()");
        return courseFacade.findAllCourses();
    }

    @ModelAttribute("lecturers")
    public List<LecturerDTO> lecturers() {
        log.debug("lecturers()");
        return lecturerFacade.findAllLecturers();
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof LectureCreateDTO) {
            binder.addValidators(new LectureCreateDTOValidator());
        }
    }

    /**
     * Create new lecturer
     *
     * @return lecture/new
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("lectureCreate") LectureCreateDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(productCreate={})", formBean);
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
        String redirect = "redirect:" + uriBuilder.path("/lecture/view/{id}").buildAndExpand(id).encode().toUriString();
        return redirect;
    }
}
