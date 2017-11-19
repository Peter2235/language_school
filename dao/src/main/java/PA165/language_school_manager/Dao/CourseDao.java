/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.Dao;

import PA165.language_school_manager.Entities.Course;
import PA165.language_school_manager.Enums.Language;

import java.util.List;


/**
 * @author Jan Safarik
 */
public interface CourseDao {

    /**
     * Method used to store course given as a parameter in the apps database
     *
     * @param course - course to be stored in database
     */
    public void create(Course course);

    /**
     * Method used to find course in database by given id
     *
     * @param id - id of the searched course
     * @return course with given id
     */
    public Course findById(Long id);

    /**
     * Method used to retrieve all courses from database
     *
     * @return List of courses stored in database
     */
    public List<Course> findAll();

    /**
     * Method used to get all courses with language given in the methods parameter
     *
     * @param language - language of the searched courses
     * @return List of courses with given language
     */
    public List<Course> findByLanguage(Language language);

    /**
     * Method used to get course with a given name (every course has a unique name)
     *
     * @param name - name of the searched course
     * @return course with the given name
     */
    public Course findByName(String name);

    /**
     * method used to update the course information in the database
     *
     * @param course - course which should be updated
     */
    public void update(Course course);

    /**
     * Method used to delete course from database
     *
     * @param course - course which should be deleted
     */
    public void delete(Course course);

}
