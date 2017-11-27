/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.service;

import PA165.language_school_manager.Dao.LecturerDAO;
import PA165.language_school_manager.Entities.Lecture;
import PA165.language_school_manager.Entities.Lecturer;
import PA165.language_school_manager.Enums.Language;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.service.spi.ServiceException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import static org.assertj.core.api.Assertions.assertThat;
/**
 *
 * @author Matúš Sedlák
 */
//@ContextConfiguration(classes=ServiceConfiguration.class)
public class LecturerServiceTest extends AbstractTransactionalTestNGSpringContextTests{
    
    @Mock
    private LecturerDAO lecturerDao;
    
    @Autowired
    @InjectMocks
    private LecturerService lecturerService;
    
    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }
    
    private Lecturer testLecturer;
    
    @BeforeMethod
    public void prepareTestLecturer(){
        testLecturer = new Lecturer();
        Set<Language> languages = new HashSet<>();
        languages.add(Language.ENGLISH);
        languages.add(Language.GERMAN);
        
        testLecturer.setFirstName("Adam");
        testLecturer.setLanguages(languages);
        testLecturer.setLastName("Adamovic");
        testLecturer.setNativeSpeaker(true);
        testLecturer.setUserName("adam123");
    }
    
    @Test
    public void assignLectureTest(){
        Lecture lecture = new Lecture();
        lecturerService.assignNewLecture(testLecturer.getId(), lecture);
        assertThat(lecturerDao.findById(testLecturer.getId()).getLectures())
                .contains(lecture)
                .isNotEmpty();
    }
    
    @Test
    public void addLanguageTest(){
        Language language = language.ENGLISH;
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
        
        assertThat(lecturerDao.findById(lecturer.getId()))
                .isNotNull();
        assertThat(lecturerDao.findById(lecturer.getId()).getLastName())
                .isEqualTo("Bebek");
    }
    
    @Test
    public void deleteLecturerTest(){
        lecturerService.deleteLecturer(testLecturer.getId());
        
        assertThat(lecturerDao.findAll())
                .isEmpty();
    } 
    
}
