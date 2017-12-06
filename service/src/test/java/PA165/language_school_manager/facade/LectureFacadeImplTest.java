package PA165.language_school_manager.facade;

import PA165.language_school_manager.DTO.*;
import PA165.language_school_manager.Dao.LectureDao;
import PA165.language_school_manager.Entities.Lecture;
import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.Enums.ProficiencyLevel;
import PA165.language_school_manager.config.ServiceConfiguration;
import PA165_language_school_manager.Facade.CourseFacade;
import PA165_language_school_manager.Facade.LectureFacade;
import PA165_language_school_manager.Facade.LecturerFacade;
import PA165_language_school_manager.Facade.PersonFacade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = ServiceConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class LectureFacadeImplTest {

    @Autowired
    private LectureFacade lectureFacade;

    @Autowired
    private LecturerFacade lecturerFacade;

    @Autowired
    private PersonFacade personFacade;

    @Autowired
    private CourseFacade courseFacade;

    private LectureCreateDTO lectureCreateDTO;
    private LectureDTO lectureDTO;
    private LectureDTO lectureDTO2;

    @Before
    public void setup(){
        LecturerCreateDTO lecturerCreateDTO = createLecturerCreateDTO("Barack", "Hussein", "Obama", "Barry", true);
        lecturerFacade.createLecturer(lecturerCreateDTO);
        LecturerDTO lecturerDTO = lecturerFacade.findLecturerByFirstName("Barack").get(0);

        PersonCreateDTO personCreateDTO = createPersonCreateDTO("Donald", "Trump", "WhiteKanye");
        PersonCreateDTO personCreateDTO2 = createPersonCreateDTO("Melania", "Trump", "WhiteKanyesWife");
        personFacade.createPerson(personCreateDTO);
        personFacade.createPerson(personCreateDTO2);
        PersonDTO personDTO = personFacade.findPersonByUserName("WhiteKanye");
        PersonDTO personDTO2 = personFacade.findPersonByUserName("WhiteKanyesWife");
        Set<PersonDTO> students = new HashSet<>();
        students.add(personDTO);
        students.add(personDTO2);

        CourseCreateDTO courseCreateDTO = createCourseCreateDTO(Language.ENGLISH, "American English", ProficiencyLevel.C1);
        Long courseID = courseFacade.createCourse(courseCreateDTO);
        CourseDTO courseDTO = courseFacade.findCourseById(courseID);

        LectureCreateDTO lectureCreateDTO = createLectureCreateDTO(LocalDateTime.now().plusDays(2), "White house");
        lectureCreateDTO.setLecturer(lecturerDTO);
        lectureCreateDTO.setStudents(students);
        lectureCreateDTO.setCourse(courseDTO);
        lectureFacade.createLecture(lectureCreateDTO);
        System.out.println(courseDTO.getLectures());
    }

    @After
    public void tearDown(){
        for (PersonDTO person : personFacade.findPersonsByLastName("Trump")){
            personFacade.deletePerson(person);
        }
        for (LectureDTO lecture : lectureFacade.findAllLectures()){
            lectureFacade.deleteLecture(lecture);
        }
        for (LecturerDTO lecturer : lecturerFacade.findLecturerByLastName("Obama")){
            lecturerFacade.deleteLecturer(lecturer.getId());
        }
        for (CourseDTO course : courseFacade.findAllCourses()){
            courseFacade.deleteCourse(course);
        }

        lectureCreateDTO = null;
        lectureDTO = null;
        lectureDTO2 = null;

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
