package PA165.language_school_manager.service;

import PA165.language_school_manager.Dao.CourseDao;
import PA165.language_school_manager.Entities.Course;
import PA165.language_school_manager.Enums.Language;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Viktor Slany
 */

public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Override
    public Course findById(Long id) {
        return courseDao.findById(id);
    }

    @Override
    public List<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    public List<Course> findByLanguage(Language language) {
        return courseDao.findByLanguage(language);
    }

    @Override
    public Course findByName(String name) {
        return courseDao.findByName(name);
    }
}
