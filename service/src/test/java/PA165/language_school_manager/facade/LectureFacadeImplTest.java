package PA165.language_school_manager.facade;

import PA165.language_school_manager.DTO.*;
import PA165.language_school_manager.Dao.LectureDao;
import PA165.language_school_manager.Entities.Lecture;
import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.Enums.ProficiencyLevel;
import PA165.language_school_manager.config.ServiceConfiguration;
import PA165.language_school_manager.service.BeanMappingService;
import PA165.language_school_manager.service.LectureService;
import PA165.language_school_manager.service.PersonService;
import PA165_language_school_manager.Facade.CourseFacade;
import PA165_language_school_manager.Facade.LectureFacade;
import PA165_language_school_manager.Facade.LecturerFacade;
import PA165_language_school_manager.Facade.PersonFacade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = ServiceConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class LectureFacadeImplTest {

    @InjectMocks
    private LectureFacade lectureFacade = new LectureFacadeImpl();

    @Mock
    private LectureService lectureService;

    @Inject
    @Spy
    private BeanMappingService mapper;

    private LectureCreateDTO lectureCreateDTO;
    private LectureDTO lectureDTO;
    private LectureDTO lectureDTO2;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        lectureCreateDTO = createLectureCreateDTO(LocalDateTime.now().plusDays(2), "White house");
        lectureFacade.createLecture(lectureCreateDTO);
    }

    private LectureCreateDTO createLectureCreateDTO(LocalDateTime time, String topic){
        LectureCreateDTO lecture = new LectureCreateDTO();
        lecture.setTime(time);
        lecture.setTopic(topic);
        return lecture;
    }

    private CourseCreateDTO createCourseCreateDTO(Language language, String name, ProficiencyLevel proficiencyLevel){
        CourseCreateDTO course = new CourseCreateDTO();
        course.setLanguage(language);
        course.setName(name);
        course.setProficiencyLevel(proficiencyLevel);
        return course;
    }

    private LecturerCreateDTO createLecturerCreateDTO(String firstName, String middleName, String lastName, String userName, boolean nativeSpeaker){
        LecturerCreateDTO lecturer = new LecturerCreateDTO();
        lecturer.setFirstName(firstName);
        lecturer.setMiddleName(middleName);
        lecturer.setLastName(lastName);
        lecturer.setUserName(userName);
        lecturer.setNativeSpeaker(nativeSpeaker);
        return lecturer;
    }

    private PersonCreateDTO createPersonCreateDTO(String firstName, String lastName, String userName){
        PersonCreateDTO personCreateDTO = new PersonCreateDTO();
        personCreateDTO.setFirstName(firstName);
        personCreateDTO.setLastName(lastName);
        personCreateDTO.setUserName(userName);
        return personCreateDTO;
    }

    @Test
    public void findLectureByIdTest(){
    }

    @Test
    public void findAllLecturesTest(){
    }

    @Test
    public void findLectureByTopic(){
    }

    @Test
    public void deleteLectureTest(){
    }

    @Test
    public void createLecture(){
    }

    @Test
    public void updateLecture(){
    }

}
