package PA165.language_school_manager.service;

import PA165.language_school_manager.Dao.CourseDao;
import PA165.language_school_manager.Entities.Course;
import PA165.language_school_manager.Entities.Lecture;
import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.LanguageSchoolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Viktor Slany
 */

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Override
    public Course findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id can not be null");
        }
        try {
            return courseDao.findById(id);
        } catch (Throwable e) {
            throw new LanguageSchoolException("Course create failed");
        }
    }

    @Override
    public List<Course> findAll() {
        try {
            return courseDao.findAll();
        } catch (Throwable e) {
            throw new LanguageSchoolException("Course create failed");
        }
    }

    @Override
    public List<Course> findByLanguage(Language language) {
        if (language == null) {
            throw new IllegalArgumentException("Language can not be null");
        }
        try {
            return courseDao.findByLanguage(language);
        } catch (Throwable e) {
            throw new LanguageSchoolException("Course create failed");
        }
    }

    @Override
    public Course findByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name can not be empty");
        }
        try {
            return courseDao.findByName(name);
        } catch (Throwable e) {
            throw new LanguageSchoolException("Course create failed");
        }
    }

    @Override
    public void createCourse(Course course) throws DataAccessException {
        if (course == null) {
            throw new IllegalArgumentException("Course can not be null");
        }
        try {
            courseDao.create(course);
        } catch (Throwable e) {
            throw new LanguageSchoolException("Course create failed");
        }

    }

    @Override
    public void updateCourse(Course course) throws DataAccessException {
        if (course == null) {
            throw new IllegalArgumentException("Course can not be null");
        }
        try {
            courseDao.update(course);
        } catch (Throwable e) {
            throw new LanguageSchoolException("Course update failed");
        }

    }

    @Override
    public void deleteCourse(Course course) throws DataAccessException {
        if (course == null) {
            throw new IllegalArgumentException("Course can not be null");
        }
        try {
            courseDao.delete(course);
        } catch (Throwable e) {
            throw new LanguageSchoolException("Course delete failed");
        }
    }

    @Override
    public void assignNewLecture(Course course, Lecture lecture) {
        if (course == null) {
            throw new IllegalArgumentException("Course can not be null");
        }
        if (lecture == null) {
            throw new IllegalArgumentException("Course can not be null");
        }
        course.addLecture(lecture);
        courseDao.update(course);
    }
}
