package PA165.language_school_manager.service;

import PA165.language_school_manager.Entities.Course;
import PA165.language_school_manager.Entities.Lecture;
import PA165.language_school_manager.Enums.Language;

import java.util.List;

/**
 * @author Viktor Slany
 */

public interface CourseService {

    /**
     * Method used to find course by given id.
     *
     * @param id - id of the searched course
     * @return course with given id
     */
    Course findById(Long id);

    /**
     * Method used to retrieve all courses.
     *
     * @return List of courses stored in database
     */
    List<Course> findAll();

    /**
     * Method used to get all courses with language given in the methods parameter.
     *
     * @param language - language of the searched courses
     * @return List of courses with given language
     */
    List<Course> findByLanguage(Language language);

    /**
     * Method used to get course with a given name (every course has a unique name).
     *
     * @param name - name of the searched course
     * @return course with the given name
     */
    Course findByName(String name);

    /**
     * Method used to store course given as a parameter.
     *
     * @param course - course to be stored in database
     */
    void createCourse(Course course);

    /**
     * method used to update the course information.
     *
     * @param course - course which should be updated
     */
    void updateCourse(Course course);

    /**
     * Method used to delete course.
     *
     * @param course - course which should be deleted
     */
    void deleteCourse(Course course);

    /**
     * Assign lecture to course
     *
     * @param course course
     * @param lecture lecture
     */
    void assignNewLecture(Course course, Lecture lecture);
}
