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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("lectures", lectureFacade.findAllLectures());
        model.addAttribute("localDateTimeFormat", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        model.addAttribute("lecturesTime", new TimeRangeDTO());
        return "/lecture/list";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewLecture(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder) {
        log.debug("view({})", id);
        //model.addAttribute("persons", lectureFacade.findLectureById(id).getPersons());
        model.addAttribute("lecture", lectureFacade.findLectureById(id));
        model.addAttribute("localDateTimeFormat", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        log.warn(lectureFacade.findLectureById(id).getPersons().toString());
        return "lecture/view";
    }

    @RequestMapping(value = "/assign/{id}", method = RequestMethod.POST)
    public String assignToLecture(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, HttpServletRequest request,
            RedirectAttributes redirectAttributes) {
        LectureDTO lecture = lectureFacade.findLectureById(id);
        PersonDTO loggedPerson = (PersonDTO) request.getSession().getAttribute("person");
        if (loggedPerson == null) {
            redirectAttributes.addFlashAttribute("alert_warning", "failed");
            return "redirect:" + request.getHeader("Referer");
        }
        if (!loggedPerson.getLectures().contains(lecture)) {
            loggedPerson.addLecture(lecture);
            personFacade.updatePerson(loggedPerson);
        }

        redirectAttributes.addFlashAttribute("alert_success", "Lecture \"" + lecture.getTopic() + "\" assigned.");
        return "redirect:" + uriBuilder.path("/lecture/view/{id}").buildAndExpand(id).encode().toUriString();
    }

    @RequestMapping(value = "/unassign/{id}", method = RequestMethod.POST)
    public String unassignFromLecture(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, HttpServletRequest request,
            RedirectAttributes redirectAttributes) {
        LectureDTO lecture = lectureFacade.findLectureById(id);
        PersonDTO loggedPerson = (PersonDTO) request.getSession().getAttribute("person");
        if (loggedPerson == null) {
            redirectAttributes.addFlashAttribute("alert_warning", "failed");
            return "redirect:" + request.getHeader("Referer");
        }
        if (loggedPerson.getLectures().contains(lecture)) {
            loggedPerson.dropLecture(lecture);
            personFacade.updatePerson(loggedPerson);
        }

        redirectAttributes.addFlashAttribute("alert_success", "Lecture \"" + lecture.getTopic() + "\" assigned.");
        return "redirect:" + uriBuilder.path("/lecture/view/{id}").buildAndExpand(id).encode().toUriString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        LectureDTO lecture = lectureFacade.findLectureById(id);
        lectureFacade.deleteLecture(lecture);
        log.debug("delete({})", id);
        redirectAttributes.addFlashAttribute("alert_success", "Lecture \"" + lecture.getTopic() + "\" was deleted.");

        return "redirect:" + uriBuilder.path("/lecture/list").toUriString();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newLecture(Model model) {
        log.debug("new()");
        model.addAttribute("lectureCreate", new LectureCreateDTO());
        return "lecture/new";
    }

    @ModelAttribute("persons")
    public List<PersonDTO> persons() {
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

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("lectureCreate") LectureCreateDTO formBean, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
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
        log.debug("create(productCreate={})", formBean);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(formBean.getTimeString().replace("T", " "), formatter);
        formBean.setTime(dateTime);
        
        Long id = lectureFacade.createLecture(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "Lecture" + id + " was created");
        String redirect = "redirect:" + uriBuilder.path("/lecture/view/{id}").buildAndExpand(id).encode().toUriString();
        return redirect;
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editLecture(@PathVariable long id, Model model, HttpServletRequest request, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        LectureDTO lecture = lectureFacade.findLectureById(id);
        String str = lecture.getTime().toString();
        str = str.substring(0, str.length() - 7);
        lecture.setTimeString(str);
        log.warn(lecture.getTimeString());
        model.addAttribute("lectureEdit", lecture);
        return "/lecture/edit";
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String updateLecture(@PathVariable long id,
                                 @Valid @ModelAttribute("lectureEdit") LectureDTO formBean,
                                 BindingResult bindingResult,
                                 Model model,
                                 UriComponentsBuilder uriBuilder,
                                 RedirectAttributes redirectAttributes,
                                 HttpServletRequest request) {

        log.debug("update(lectureCreate={})", formBean);
        if (bindingResult.hasErrors()) {
            return "lecture/edit/"+id;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(formBean.getTimeString().replace("T", " "), formatter);
        formBean.setTime(dateTime);
        lectureFacade.updateLecture(formBean);
        return "redirect:" + uriBuilder.path("/lecture/list").toUriString();
    }
    
    @RequestMapping(value = "/timerange", method = RequestMethod.POST)
    public String timeRangeFilter(@Valid @ModelAttribute("lecturesTime") TimeRangeDTO formBean, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
    
        if (formBean.getStartTimeString() == null || formBean.getEndTimeString() == null || formBean.getStartTimeString() == "" || formBean.getEndTimeString() == "") {
            return "lecture/list";
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(formBean.getStartTimeString().replace("T", " "), formatter);
        formBean.setStartTime(dateTime);
        LocalDateTime dateTime2 = LocalDateTime.parse(formBean.getEndTimeString().replace("T", " "), formatter);
        formBean.setEndTime(dateTime2);
        
        List<LectureDTO> lectures = lectureFacade.findByTime(formBean.getStartTime(), formBean.getEndTime());
        model.addAttribute("lectures", lectures);
        model.addAttribute("localDateTimeFormat", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        model.addAttribute("lecturesTime", new TimeRangeDTO());
        return "/lecture/list";
        
    }
    
}
