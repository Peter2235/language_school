//package PA165.language_school_manager.facade;
//
//import PA165.language_school_manager.DTO.CourseDTO;
//import PA165.language_school_manager.DTO.LectureDTO;
//import PA165.language_school_manager.DTO.LecturerCreateDTO;
//import PA165.language_school_manager.DTO.LecturerDTO;
//import PA165.language_school_manager.Dao.LectureDao;
//import PA165.language_school_manager.Entities.Course;
//import PA165.language_school_manager.Entities.Lecture;
//import PA165.language_school_manager.Entities.Lecturer;
//import PA165.language_school_manager.Enums.Language;
//import PA165.language_school_manager.Enums.ProficiencyLevel;
//import PA165.language_school_manager.config.ServiceConfiguration;
//import PA165_language_school_manager.Facade.LectureFacade;
//import PA165_language_school_manager.Facade.LecturerFacade;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import java.time.LocalDateTime;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DirtiesContext
//@ContextConfiguration(classes = ServiceConfiguration.class)
//public class LectureFacadeImplTest extends AbstractTestNGSpringContextTests {
//
//    private LectureFacade lectureFacade;
//    private LecturerFacade lecturerFacade;
//    private LectureDao lectureDao;
//
//    private LectureDTO lectureDTO;
//    private LectureDTO lectureDTO2;
//
//    @BeforeMethod
//    public void setup(){
//        lectureDTO.setTime(LocalDateTime.now().plusDays(5));
//        lectureDTO.setTopic("nevermind");
//        Set<Language> languages = new HashSet<>();
//        languages.add(Language.ENGLISH);
//        LecturerCreateDTO lecturerCreateDto = new LecturerCreateDTO();
//        lecturerCreateDto.setFirstName("Adam");
//        lecturerCreateDto.setLastName("Adamovic");
//        lecturerCreateDto.setUserName("adam123");
//
//        lectureDTO.setLecturer(lecturerCreateDto);
//        CourseDTO courseDTO = new CourseDTO();
//        courseDTO.setLanguage(Language.ENGLISH);
//        courseDTO.setName("boring");
//        courseDTO.setProficiencyLevel(ProficiencyLevel.A1);
//        lectureDTO.setCourse(courseDTO);
//    }
//
//    @Test
//    public void findLectureByIdTest(){
//        Lecture lecture = new Lecture();
//        lectureDao.create(lecture);
//        LectureDTO lecture1 = lectureFacade.findLectureById(lecture.getId());
//        assertThat(lecture1.getId()).isNotNull();
//    }
//
//    @Test
//    public void findAllLecturesTest(){
//        Lecture lecture = new Lecture();
//        lectureDao.create(lecture);
//        Lecture lecture2 = new Lecture();
//        lectureDao.create(lecture2);
//        List<LectureDTO> lecture1 = lectureFacade.findAllLectures();
//        assertThat(lecture1).isNotEmpty().hasSize(2);
//    }
//
//    @Test
//    public void findLectureByTopic(){
//        Lecture lecture = new Lecture();
//        lecture.setTopic("huh");
//        lectureDao.create(lecture);
//        LectureDTO lecture1 = lectureFacade.findLectureByTopic("huh");
//        assertThat(lecture1.getId()).isNotNull();
//    }
//
//    @Test
//    public void deleteLectureTest(){
//        Lecture lecture = new Lecture();
//        lecture.setTopic("huh");
//        lectureDao.create(lecture);
//        lectureFacade.deleteLecture(lecture.getId());
//        assertThat(lectureDao.findById(lecture.getId())).isNull();
//    }
//
//    @Test
//    public void createLecture(){
//        lectureFacade.createLecture(lectureDTO);
//        assertThat(lectureDao.findById(lectureDTO.getId())).isNotNull();
//    }
//
//}
