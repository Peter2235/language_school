package PA165_language_school_manager.Facade;

import PA165.language_school_manager.DTO.CourseDTO;
import PA165.language_school_manager.DTO.LectureDTO;
import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.Enums.ProficiencyLevel;

import java.util.Collection;
import java.util.List;

/**
 * @author Viktor Slany
 */

public interface CourseFacade {
    CourseDTO findCourseById(Long id);

    CourseDTO findCourseByName(String name);

    List<CourseDTO> findCourseByLanguage(Language language);

    Collection<CourseDTO> findAllCourses();
}
