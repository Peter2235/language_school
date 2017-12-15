package PA165.language_school_manager.mvc.controllers;

import PA165.language_school_manager.Facade.CourseFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for course
 *
 * @author Peter Tirala
 */
@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseFacade courseFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("courses", courseFacade.findAllCourses());
        return "/course/list";
    }
}
