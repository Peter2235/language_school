package PA165.language_school_manager.service;

import PA165.language_school_manager.Dao.CourseDao;
import PA165.language_school_manager.Entities.Course;
import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.Enums.ProficiencyLevel;
import PA165.language_school_manager.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Peter Tirala
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CourseServiceTest {

    @Mock
    private CourseDao courseDao;

    @InjectMocks
    private CourseService courseService = new CourseServiceImpl();

    private Course course;
    private List<Course> courses = new ArrayList<>();

    @Before
    public void setUp() throws ServiceException {
        MockitoAnnotations.initMocks(this);

        TestUtils.mockCourseDao(courseDao, courses);

        course = TestUtils.createCourse(ProficiencyLevel.A1, "Name", Language.ENGLISH);
        courseService.createCourse(course);
    }

    @Test
    public void findByIdTest() {
        assertThat(courseService.findById(course.getId())).isEqualTo(course);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByIdNullTest() {
        courseService.findById(null);
    }

    @Test
    public void findAllTest() {
        List<Course> courses = courseService.findAll();
        assertThat(courses.size()).isEqualTo(1);
        assertThat(courses).contains(course);
    }

    @Test
    public void findByLanguageTest() {
        assertThat(courseService.findByLanguage(Language.ENGLISH)).contains(course);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByLanguageNullTest() {
        courseService.findByLanguage(null);
    }

    @Test
    public void findByNameTest() {
        assertThat(courseService.findByName("Name")).isEqualTo(course);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByNameNullTest() {
        courseService.findByName(null);
    }

    /*@Test
    public void createCourseTest(){
        Course course = new Course(1L);
        courseService.createCourse(course);

        assertThat(courseService.findById(course.getId())).isEqualTo(course);
    }*/

    @Test
    public void updateCourseTest() {
        course.setName("Test");
        courseService.updateCourse(course);

        Course byId = courseService.findById(course.getId());
        assertThat(byId.getName()).isEqualTo("Test");
    }

    @Test
    public void deleteCourseTest() {
        courseService.deleteCourse(course);

        assertThat(courseService.findAll()).isEmpty();
    }

}
