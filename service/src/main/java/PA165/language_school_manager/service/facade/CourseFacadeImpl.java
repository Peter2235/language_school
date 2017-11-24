package PA165.language_school_manager.service.facade;

import PA165.language_school_manager.DTO.CourseDTO;
import PA165.language_school_manager.DTO.LectureDTO;
import PA165.language_school_manager.Entities.Course;
import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.Enums.ProficiencyLevel;
import PA165.language_school_manager.service.BeanMappingService;
import PA165.language_school_manager.service.CourseService;
import PA165_language_school_manager.Facade.CourseFacade;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * @author Viktor Slany
 */

public class CourseFacadeImpl implements CourseFacade {

    @Autowired
    private CourseService courseService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public CourseDTO findCourseById(Long id) {
        Course course = courseService.findById(id);
        return (course == null) ? null : beanMappingService.mapTo(course, CourseDTO.class);
    }

    @Override
    public CourseDTO findCourseByName(String name) {
        Course course = courseService.findByName(name);
        return (course == null) ? null : beanMappingService.mapTo(course, CourseDTO.class);
    }

    @Override
    public List<CourseDTO> findCourseByLanguage(Language language) {
        List<Course> courses = courseService.findByLanguage(language);
        return (courses == null) ? null : beanMappingService.mapTo(courses, CourseDTO.class);
    }

    @Override
    public Collection<CourseDTO> findAllCourses() {
        return beanMappingService.mapTo(courseService.findAll(), CourseDTO.class);
    }
}
