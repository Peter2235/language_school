package PA165.language_school_manager.facade;

import PA165.language_school_manager.DTO.CourseCreateDTO;
import PA165.language_school_manager.DTO.CourseDTO;
import PA165.language_school_manager.Entities.Course;
import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.Enums.ProficiencyLevel;
import PA165.language_school_manager.config.ServiceConfiguration;
import PA165.language_school_manager.service.BeanMappingService;
import PA165.language_school_manager.service.CourseService;
import PA165_language_school_manager.Facade.CourseFacade;
import org.hibernate.service.spi.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Peter Tirala
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CourseFacadeTest {

    @InjectMocks
    private CourseFacade courseFacade = new CourseFacadeImpl();

    @Inject
    @Spy
    private BeanMappingService mapper;

    @Mock
    private CourseService courseService;

    private CourseCreateDTO courseCreateDTO;
    private CourseDTO courseDTO;
    private Course course;
    private List<Course> courseList = new ArrayList<>();

    @Before
    public void setUp() throws ServiceException {
        MockitoAnnotations.initMocks(this);

        course = new Course();
        course.setId(1L);
        course.setName("Name");
        course.setLanguage(Language.ENGLISH);
        course.setProficiencyLevel(ProficiencyLevel.A1);

        courseDTO = new CourseDTO();
        courseDTO.setName("Name");
        courseDTO.setId(1L);
        courseDTO.setLanguage(Language.ENGLISH);
        courseDTO.setProficiencyLevel(ProficiencyLevel.A1);

        courseCreateDTO = new CourseCreateDTO();
        courseCreateDTO.setName("Name");
        courseCreateDTO.setLanguage(Language.ENGLISH);
        courseCreateDTO.setProficiencyLevel(ProficiencyLevel.A1);

        courseList.add(course);

        when(courseService.findById(1L)).thenReturn(course);
        when(courseService.findAll()).thenReturn(courseList);
        when(courseService.findByLanguage(Language.ENGLISH)).thenReturn(courseList);
        when(courseService.findByName("Name")).thenReturn(course);
    }

    @Test
    public void createCourseTest() {
        courseFacade.createCourse(courseCreateDTO);
        verify(courseService).createCourse(course);
    }

    @Test
    public void updateCourseTest() {
        courseFacade.updateCourse(courseDTO);
        verify(courseService).updateCourse(course);
    }

    @Test
    public void findByIdTest() {
        CourseDTO courseById = courseFacade.findCourseById(1L);
        assertThat(courseById).isEqualTo(courseDTO);
    }

    @Test
    public void findAllTest() {
        Collection<CourseDTO> allCourses = courseFacade.findAllCourses();
        assertThat(allCourses).contains(courseDTO);
    }

    @Test
    public void findByLanguageTest() {
        List<CourseDTO> courseByLanguage = courseFacade.findCourseByLanguage(Language.ENGLISH);
        assertThat(courseByLanguage).contains(courseDTO);
    }

    @Test
    public void findByNameTest() {
        CourseDTO dto = courseFacade.findCourseByName("Name");
        assertThat(dto).isEqualTo(courseDTO);
    }

    @Test
    public void deleteCourseTest() {
        courseFacade.deleteCourse(courseDTO);
        verify(courseService).deleteCourse(course);
    }
}
