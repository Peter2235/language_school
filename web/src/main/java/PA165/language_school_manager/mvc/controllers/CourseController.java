package PA165.language_school_manager.mvc.controllers;

import PA165.language_school_manager.DTO.CourseCreateDTO;
import PA165.language_school_manager.DTO.CourseDTO;
import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.Enums.ProficiencyLevel;
import PA165.language_school_manager.Facade.CourseFacade;
import PA165.language_school_manager.Facade.LectureFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

/**
 * Controller for course
 *
 * @author Peter Tirala
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseFacade courseFacade;

    @Autowired
    private LectureFacade lectureFacade;

    /**
     * List all courses
     *
     * @param model
     * @return /course/list
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("courses", courseFacade.findAllCourses());
        return "/course/list";
    }

    /**
     * Detail of a course
     *
     * @param id Id of a course
     * @param model
     * @return /course/view
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewCoursee(@PathVariable long id, Model model){
        model.addAttribute("lectures", lectureFacade.findLectureByCourse(courseFacade.findCourseById(id)));
        model.addAttribute("course", courseFacade.findCourseById(id));
        return "/course/view";
    }

    @ModelAttribute("languages")
    public Language[] languages() {
        return Language.values();
    }

    @ModelAttribute("proficiencyLevels")
    public ProficiencyLevel[] proficiencyLevels() {
        return ProficiencyLevel.values();
    }

    /**
     * New form for course creating
     *
     * @param model
     * @return /course/new
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newCourse(Model model) {
        model.addAttribute("courseCreate", new CourseCreateDTO());
        return "/course/new";
    }

    /**
     * Create new course
     *
     * @return /course/new
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("courseCreate") CourseCreateDTO formBean, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            return "/course/new";
        }
        Long id = courseFacade.createCourse(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "Course" + id + " was created");
        return "redirect:" + uriBuilder.path("/course/view/{id}").buildAndExpand(id).encode().toUriString();
    }

    /**
     * Delete course
     *
     * @return /course/list
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        CourseDTO course = courseFacade.findCourseById(id);
        courseFacade.deleteCourse(course);
        redirectAttributes.addFlashAttribute("alert_success", "Course \"" + course.getName() + "\" was deleted.");

        return "redirect:" + uriBuilder.path("/course/list").toUriString();
    }

}
