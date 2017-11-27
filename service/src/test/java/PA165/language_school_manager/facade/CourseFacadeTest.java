package java.PA165.language_school_manager.facade;

import PA165.language_school_manager.DTO.CourseCreateDTO;
import PA165.language_school_manager.Dao.CourseDao;
import PA165.language_school_manager.Entities.Course;
import PA165.language_school_manager.Enums.Language;
import PA165_language_school_manager.Facade.CourseFacade;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Peter Tirala
 */
public class CourseFacadeTest {

    @Autowired
    private CourseFacade courseFacade;

    @Mock
    private CourseDao courseDao;

    @Test
    public void findByIdTest() {
        Course course = new Course();
        courseDao.create(course);

        assertThat(courseFacade.findCourseById(course.getId()))
                .isNotNull();
    }

    @Test
    public void findAllTest() {
        Course course = new Course();
        courseDao.create(course);

        assertThat(courseFacade.findAllCourses())
                .isNotEmpty();
    }

    @Test
    public void findByLanguageTest() {
        Course course = new Course();
        course.setLanguage(Language.ENGLISH);
        courseDao.create(course);

        assertThat(courseFacade.findCourseByLanguage(Language.ENGLISH))
                .isNotNull();
    }

    @Test
    public void findByNameTest() {
        Course course = new Course();
        course.setName("Test");
        courseDao.create(course);

        assertThat(courseFacade.findCourseByName("Test"))
                .isNotNull();
    }

    @Test
    public void createCourseTest() {
        CourseCreateDTO course = new CourseCreateDTO();
        courseFacade.createCourse(course);

        assertThat(courseDao.findAll())
                .isNotEmpty();
    }
}
