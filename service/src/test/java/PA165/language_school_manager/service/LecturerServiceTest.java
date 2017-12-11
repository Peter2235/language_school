/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.service;

import PA165.language_school_manager.Dao.LecturerDAO;
import PA165.language_school_manager.Entities.Course;
import PA165.language_school_manager.Entities.Lecture;
import PA165.language_school_manager.Entities.Lecturer;
import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.Enums.ProficiencyLevel;
import PA165.language_school_manager.config.ServiceConfiguration;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.util.Sets;
/**
 *
 * @author Matúš Sedlák
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class LecturerServiceTest {

    @Mock
    private LecturerDAO lecturerDao;

    @InjectMocks
    private LecturerService lecturerService = new LecturerServiceImpl();

    private Lecturer testLecturer;
    private List<Lecturer> lecturers = new ArrayList<>();
    private Set<Language> languages = new HashSet<>(Arrays.asList(Language.ENGLISH, Language.SPANISH));
    
    @Before
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
        TestUtils.mockLecturerDao(lecturerDao, lecturers);
        testLecturer = TestUtils
                .createLecturer("adam123", "Adam", "", "Adamovic", languages, false);
        lecturerService.createLecturer(testLecturer);
    }

    @Test
    public void findLecturerByIdTest(){
        assertThat(lecturerService.findLecturerById(testLecturer.getId()))
                .isNotNull()
                .isEqualTo(testLecturer);
    }

    @Test
    public void findLecturerByFirstNameTest(){
        List<Lecturer> foundLecturers = lecturerService.findLecturerByFirstName(testLecturer.getFirstName());
        assertThat(foundLecturers.size())
                .isEqualTo(1);
        assertThat(foundLecturers)
                .isNotNull()
                .isNotEmpty();
    }
    
    @Test
    public void findLecturerByLastNameTest(){
        List<Lecturer> foundLecturers = lecturerService.findLecturerByLastName(testLecturer.getLastName());
        assertThat(foundLecturers.size())
                .isEqualTo(1);
        assertThat(foundLecturers)
                .isNotNull()
                .isNotEmpty();
    }
    
    @Test 
     public void findLecturerByLectureTest(){
        Lecture lecture = TestUtils.createLecture(
                "topic", 
                testLecturer, new Course(1l));
        
        lecturerService.assignNewLecture(testLecturer.getId(), lecture);
        
        Lecturer foundLecturer = lecturerService.findLecturerByLecture(lecture);
        assertThat(foundLecturer)
                .isNull();
    }
  
    /*@Test
    public void findLecturersByLanguageTest(){
        
    }*/
    
    @Test
    public void assignLectureTest(){
        Lecture lecture = new Lecture();
        lecturerService.assignNewLecture(testLecturer.getId(), lecture);
        assertThat(lecturerService.findLecturerById(testLecturer.getId()).getLectures())
                .contains(lecture)
                .isNotEmpty();
    }

    @Test
    public void addLanguageTest(){
        Language language = Language.ENGLISH;
        lecturerService.addLanguage(testLecturer.getId(), language);

        assertThat(lecturerDao.findById(testLecturer.getId()).getLanguages())
                .contains(language)
                .isNotEmpty();
    }

    @Test
    public void createLecturerTest(){
        Lecturer lecturer = new Lecturer();
        lecturer.setLastName("Bebek");
        lecturer.setUserName("bebek123");
        lecturerService.createLecturer(lecturer);

        assertThat(lecturerService.findLecturerById(lecturer.getId()))
                .isNotNull();
        assertThat(lecturerService.findLecturerById(lecturer.getId()).getLastName())
                .isEqualTo("Bebek");
    }

    @Test
    public void deleteLecturerTest(){
        lecturerService.deleteLecturer(testLecturer.getId());

        assertThat(lecturerService.findLecturerByLastName(testLecturer.getLastName()))
                .isEmpty();
    }

}
