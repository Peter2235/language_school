package PA165.language_school_manager.service;

import PA165.language_school_manager.Dao.CourseDao;
import PA165.language_school_manager.Entities.Course;
import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Peter Tirala
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class CourseServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Mock
    private CourseDao courseDao;

    @Autowired
    @InjectMocks
    private CourseService courseService;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findByIdTest() {
        Course course = new Course();
        courseDao.create(course);

        assertThat(courseService.findById(course.getId()))
                .isEqualTo(course);
    }

    @Test
    public void findAllTest() {
        Course course = new Course();
        courseDao.create(course);

        assertThat(courseService.findAll())
                .contains(course);
    }

    @Test
    public void findByLanguageTest() {
        Course course = new Course();
        course.setLanguage(Language.ENGLISH);
        courseDao.create(course);

        assertThat(courseService.findByLanguage(Language.ENGLISH))
                .contains(course);
    }

    @Test
    public void findByNameTest() {
        Course course = new Course();
        course.setName("Test");
        courseDao.create(course);

        assertThat(courseService.findByName("Test"))
                .isEqualTo(course);
    }

    @Test
    public void createCourseTest() {
        Course course = new Course();
        courseService.createCourse(course);

        assertThat(courseDao.findById(course.getId()))
                .isEqualTo(course);
    }

    @Test
    public void updateCourseTest() {
        Course course = new Course();
        courseDao.create(course);

        course.setName("Test");
        courseService.updateCourse(course);

        Course byId = courseDao.findById(course.getId());
        assertThat(byId.getName()).isEqualTo("Test");
    }

    @Test
    public void deleteCourseTest() {
        Course course = new Course();
        courseDao.create(course);

        courseService.deleteCourse(course);

        assertThat(courseDao.findAll())
                .isEmpty();
    }

}
