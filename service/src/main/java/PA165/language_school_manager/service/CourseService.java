package PA165.language_school_manager.service;

import PA165.language_school_manager.Entities.Course;
import PA165.language_school_manager.Enums.Language;

import java.util.List;

/**
 * @author Viktor Slany
 */

public interface CourseService {

    Course findById(Long id);

    List<Course> findAll();

    List<Course> findByLanguage(Language language);

    Course findByName(String name);
}
