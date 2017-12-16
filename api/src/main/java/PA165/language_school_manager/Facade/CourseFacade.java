package PA165.language_school_manager.Facade;

import PA165.language_school_manager.DTO.CourseCreateDTO;
import PA165.language_school_manager.DTO.CourseDTO;
import PA165.language_school_manager.Enums.Language;

import java.util.Collection;
import java.util.List;

/**
 * @author Viktor Slany
 */

public interface CourseFacade {
    /**
     * Method used to find course by given id
     *
     * @param id - id of the searched course
     * @return course with given id
     */
    CourseDTO findCourseById(Long id);

    /**
     * Method used to get course with a given name (every course has a unique name)
     *
     * @param name - name of the searched course
     * @return course with the given name
     */
    CourseDTO findCourseByName(String name);

    /**
     * Method used to get all courses with language given in the methods parameter
     *
     * @param language - language of the searched courses
     * @return List of courses with given language
     */
    List<CourseDTO> findCourseByLanguage(Language language);

    /**
     * Method used to retrieve all courses from database
     *
     * @return List of courses stored in database
     */
    List<CourseDTO> findAllCourses();

    /**
     * Method used to store course given as a parameter
     *
     * @param course - course to be stored in database
     * @return Long - Id of course
     */
    Long createCourse(CourseCreateDTO course);

    /**
     * method used to update the course information
     *
     * @param course - course which should be updated
     */
    void updateCourse(CourseDTO course);

    /**
     * Method used to delete course
     *
     * @param course - course which should be deleted
     */
    void deleteCourse(CourseDTO course);
}
