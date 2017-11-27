//package PA165.language_school_manager.facade;
//
//import PA165.language_school_manager.DTO.LectureDTO;
//import PA165.language_school_manager.DTO.LecturerCreateDTO;
//import PA165.language_school_manager.DTO.LecturerDTO;
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
//
//import java.time.LocalDateTime;
//import java.util.HashSet;
//import java.util.Set;
//
//@DirtiesContext
//@ContextConfiguration(classes = ServiceConfiguration.class)
//public class LectureFacadeImplTest extends AbstractTestNGSpringContextTests {
//
//    private LectureFacade lectureFacade;
//    private LecturerFacade lecturerFacade;
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
//        lecture.setLecturer(lecturer);
//        Course course = new Course();
//        course.setLanguage(Language.ENGLISH);
//        course.setName("boring");
//        course.setProficiencyLevel(ProficiencyLevel.A1);
//        lecture.setCourse(course);
//    }
//
//
//}
