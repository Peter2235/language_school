/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.Dao;

import PA165.language_school_manager.Entities.Course;
import java.util.List;
import PA165.language_school_manager.Enums.Language;


/**
 *
 * @author Jan Safarik
 */
public interface CourseDao {

    public void create(Course course);

    public Course findById(Long id);

    public List<Course> findAll();

    public List<Course> findByLanguage(Language language);

    public Course findByName(String name);
    
    public void update(Course course);

    public void delete(Course course);

}
