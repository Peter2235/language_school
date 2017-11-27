//package PA165.language_school_manager.service;
//
//import PA165.language_school_manager.Dao.CourseDao;
//import PA165.language_school_manager.Dao.LectureDao;
//import PA165.language_school_manager.Dao.LectureDaoImpl;
//import PA165.language_school_manager.Dao.LecturerDAO;
//import PA165.language_school_manager.Entities.Course;
//import PA165.language_school_manager.Entities.Lecture;
//import PA165.language_school_manager.Entities.Lecturer;
//import PA165.language_school_manager.Enums.Language;
//import PA165.language_school_manager.Enums.ProficiencyLevel;
//import PA165.language_school_manager.config.ServiceConfiguration;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.testng.annotations.BeforeMethod;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.Month;
//import java.util.*;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Matchers.any;
//import static org.mockito.Matchers.anyLong;
//import static org.mockito.Mockito.doAnswer;
//import static org.mockito.Mockito.doCallRealMethod;
//import static org.mockito.Mockito.when;
//
//@ContextConfiguration(classes = ServiceConfiguration.class)
//public class LectureServiceTest {
//
//    @Mock
//    private LectureDaoImpl lectureDao = new LectureDaoImpl();
//
//    @Mock
//    private LecturerDAO lecturerDao;
//
//    @Mock
//    private CourseDao courseDao;
//
//    @Autowired
//    @InjectMocks
//    private LectureService lectureService = new LectureServiceImpl();
//
//
//
//    @Before
//    public void beforeClass(){
//        MockitoAnnotations.initMocks(this);
//
//        when(lectureDao.findById(anyLong())).then(invoke -> {
//            Long id = invoke.getArgumentAt(0, Long.class);
//            if (id == null) {
//                throw new IllegalArgumentException("Id is null");
//            }
//            return lectures.get(id);
//        });
//
//        doCallRealMethod().when(lectureDao).create(lecture);
//
//    }
//
//
//    private Map<Long, Lecture> lectures = new HashMap<>();;
//    private Lecture lecture;
//
//    @BeforeMethod
//    public void creationOfLecture(){
//
//        lecture = new Lecture();
//        lecture.setTime(LocalDateTime.now().plusDays(5));
//        lecture.setTopic("nevermind");
//        Set<Language> languages = new HashSet<>();
//        languages.add(Language.ENGLISH);
//        Lecturer lecturer = new Lecturer("user", "first", "middle", "last", languages, true);
//        lecturerDao.create(lecturer);
//        lecture.setLecturer(lecturer);
//        Course course = new Course();
//        course.setLanguage(Language.ENGLISH);
//        course.setName("boring");
//        course.setProficiencyLevel(ProficiencyLevel.A1);
//        lecture.setCourse(course);
//    }
//
//    @Test
//    public void findLectureByIdTest(){
//        lectureDao.create(lecture);
//        Lecture lecture1 = lectureService.findLectureById(lecture.getId());
//        assertThat(lecture1).isNotNull();
//    }
//
//    @Test
//    public void findAllLecturesTest(){
//        lectureDao.create(lecture);
//        Lecture lecture2 = new Lecture();
//        lecture2.setCourse(lecture.getCourse());
//        lecture2.setLecturer(lecture.getLecturer());
//        lecture2.setTopic(lecture.getTopic());
//        lecture2.setTime(lecture.getTime().plusDays(4));
//        lectureDao.create(lecture2);
//        List<Lecture> lectures = lectureService.findAllLectures();
//        assertThat(lectures).isNotNull().containsExactlyInAnyOrder(lecture, lecture2);
//    }
//
//    @Test
//    public void findLectureByTopicTest(){
//        lectureDao.create(lecture);
//        Lecture lecture1 = lectureService.findLectureByTopic(lecture.getTopic());
//        assertThat(lecture1).isNotNull();
//    }
//
//    @Test
//    public void createLectureTest(){
//        lectureService.createLecture(lecture);
//        assertThat(lecture.getId()).isNotNull();
//    }
//
//    @Test
//    public void deleteLectureTest(){
//        lectureDao.create(lecture);
//        assertThat(lecture.getId()).isNotNull();
//        Long id = lecture.getId();
//        lectureService.deleteLecture(lecture.getId());
//        assertThat(lectureDao.findById(id)).isNull();
//    }
//
//    @Test
//    public void updateLectureTest(){
//        lectureDao.create(lecture);
//        assertThat(lecture.getId()).isNotNull();
//        LocalDateTime time = lecture.getTime().plusDays(1);
//        lecture.setTime(time);
//        lectureService.updateLecture(lecture);
//        assertThat(lectureDao.findById(lecture.getId()).getTime()).isEqualTo(time);
//    }
//
//}
