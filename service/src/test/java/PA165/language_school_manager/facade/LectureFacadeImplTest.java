package PA165.language_school_manager.facade;

import PA165.language_school_manager.DTO.*;
import PA165.language_school_manager.Entities.Course;
import PA165.language_school_manager.Entities.Lecture;
import PA165.language_school_manager.Entities.Lecturer;
import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.Enums.ProficiencyLevel;
import PA165.language_school_manager.config.ServiceConfiguration;
import PA165.language_school_manager.service.BeanMappingService;
import PA165.language_school_manager.service.LectureService;
import PA165.language_school_manager.Facade.LectureFacade;
import PA165.language_school_manager.service.CourseService;
import PA165.language_school_manager.service.LecturerService;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import org.mockito.Matchers;
import org.mockito.Mockito;

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

    private Course course;
    private CourseDTO courseDTO;
    
    private Lecturer lecturer;
    private LecturerDTO lecturerDTO;
    
    private LectureCreateDTO lectureCreateDTO;
    private LectureDTO lectureDTO;
    private Lecture lecture;
    private List<Lecture> lectures = new ArrayList<>();

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        
        lecturer = new Lecturer();
        lecturer.setId(1L);
        lecturer.setLanguage(Language.ENGLISH);
        lecturer.setUserName("theChosenOne");
        lecturer.setLastName("huh");
        lecturer.setNativeSpeaker(true);
        
        lecturerDTO = new LecturerDTO();
        lecturerDTO.setLanguage(Language.ENGLISH);
        lecturerDTO.setId(1L);
        lecturerDTO.setUserName("theChosenOne");
        lecturerDTO.setLastName("huh");
        lecturerDTO.setNativeSpeaker(true);
        
        course = new Course();
        course.setId(1L);
        course.setName("java");
        course.setLanguage(Language.ENGLISH);
        course.setProficiencyLevel(ProficiencyLevel.C1);
        
        courseDTO = new CourseDTO();
        courseDTO.setId(1L);
        courseDTO.setName("java");
        courseDTO.setLanguage(Language.ENGLISH);
        courseDTO.setProficiencyLevel(ProficiencyLevel.C1);
        
        LocalDateTime time = LocalDateTime.now().plusDays(1);
        
        lecture = new Lecture();
        lecture.setId(1L);
        lecture.setTime(time);
        lecture.setCourse(course);
        lecture.setLecturer(lecturer);
        lecture.setTopic("someTopic");
        lectures.add(lecture);
        
        lectureDTO = new LectureDTO();
        lectureDTO.setId(1L);
        lectureDTO.setTime(time);
        lectureDTO.setCourse(courseDTO);
        lectureDTO.setLecturer(lecturerDTO);
        lectureDTO.setTopic("someTopic");
        
        lectureCreateDTO = new LectureCreateDTO();
        lectureCreateDTO.setTime(time);
        lectureCreateDTO.setCourse(courseDTO);
        lectureCreateDTO.setLecturer(lecturerDTO);
        lectureCreateDTO.setTopic("someTopic");
        
        when(lectureService.findLectureById(lecture.getId())).thenReturn(lecture);
        when(lectureService.findAllLectures()).thenReturn(lectures);
        when(lectureService.findLectureByTopic("someTopic")).thenReturn(lecture);
        when(lectureService.findLectureByCourse(course)).thenReturn(lectures);
        
    }

    @Test
    public void findLectureByIdTest(){
        LectureDTO lecture1 = lectureFacade.findLectureById(lecture.getId());
        assertThat(lecture1).isEqualToComparingFieldByField(this.lectureDTO);
    }

    @Test
    public void findAllLecturesTest(){
        List<LectureDTO> lecturesToFind = lectureFacade.findAllLectures();
        assertThat(lecturesToFind).containsOnly(lectureDTO);
    }

    @Test
    public void findLectureByTopic(){
        LectureDTO lecture1 = lectureFacade.findLectureByTopic("someTopic");
        assertThat(lecture1).isEqualToComparingFieldByField(this.lectureDTO);
    }

    @Test
    public void deleteLectureTest(){
        lectureFacade.deleteLecture(lectureDTO);
        verify(lectureService).deleteLecture(lecture.getId());
    }

    @Test
    public void createLecture(){
        lectureFacade.createLecture(lectureCreateDTO);
        verify(lectureService).createLecture(lecture);
    }

    @Test
    public void updateLecture(){
        lectureFacade.updateLecture(lectureDTO);
        verify(lectureService).updateLecture(lecture);
    }
    
    @Test
    public void findLectureByCourse(){
        List<LectureDTO> lecture1 = lectureFacade.findLectureByCourse(courseDTO);
        assertThat(lecture1).containsOnly(lectureDTO);
    }

}
