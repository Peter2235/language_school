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
import PA165.language_school_manager.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
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

    private Lecturer lecturer;
    private List<Lecturer> lectures = new ArrayList<>();

    @Before
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);

        TestUtils.mockLecturerDao(lecturerDao, lectures);


        lecturer = TestUtils.createLecturer("Here", "Comes", "The", "Surprise", Language.ENGLISH, true);
        lecturerService.createLecturer(lecturer);
    }

    /*@Test
    public void assignLectureTest(){
        Lecture lecture = new Lecture();
        lecturerService.assignNewLecture(testLecturer.getId(), lecture);
        assertThat(lecturerDao.findById(testLecturer.getId()).getLectures())
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
    }*/

    @Test
    public void createLecturerTest(){
        Lecturer lecturer2 = new Lecturer();
        lecturerService.createLecturer(lecturer2);

        assertThat(lecturerService.findLecturerById(lecturer2.getId())).isEqualTo(lecturer2);
    }

  /*  @Test
    public void deleteLecturerTest(){
        lecturerService.deleteLecturer(testLecturer.getId());

        assertThat(lecturerDao.findAll())
                .isEmpty();
    }*/

}
