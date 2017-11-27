//package PA165.language_school_manager.facade;
//
//import PA165.language_school_manager.DTO.CourseCreateDTO;
//import PA165.language_school_manager.Dao.CourseDao;
//import PA165.language_school_manager.Entities.Course;
//import PA165.language_school_manager.Enums.Language;
//import PA165_language_school_manager.Facade.CourseFacade;
//import org.hibernate.service.spi.ServiceException;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
///**
// * @author Peter Tirala
// */
//public class CourseFacadeTest {
//
//    @Autowired
//    @InjectMocks
//    private CourseFacade courseFacade;
//
//    @Mock
//    private CourseDao courseDao;
//
//    @BeforeClass
//    public void setup() throws ServiceException {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void findByIdTest() {
//        Course course = new Course();
//        courseDao.create(course);
//
//        assertThat(courseFacade.findCourseById(course.getId()))
//                .isNotNull();
//    }
//
//    @Test
//    public void findAllTest() {
//        Course course = new Course();
//        courseDao.create(course);
//
//        assertThat(courseFacade.findAllCourses())
//                .isNotEmpty();
//    }
//
//    @Test
//    public void findByLanguageTest() {
//        Course course = new Course();
//        course.setLanguage(Language.ENGLISH);
//        courseDao.create(course);
//
//        assertThat(courseFacade.findCourseByLanguage(Language.ENGLISH))
//                .isNotNull();
//    }
//
//    @Test
//    public void findByNameTest() {
//        Course course = new Course();
//        course.setName("Test");
//        courseDao.create(course);
//
//        assertThat(courseFacade.findCourseByName("Test"))
//                .isNotNull();
//    }
//
//    @Test
//    public void createCourseTest() {
//        CourseCreateDTO course = new CourseCreateDTO();
//        courseFacade.createCourse(course);
//
//        assertThat(courseDao.findAll())
//                .isNotEmpty();
//    }
//}
