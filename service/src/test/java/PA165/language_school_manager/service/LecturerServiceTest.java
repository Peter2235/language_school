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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void assignLectureTest(){
        Lecture lecture = new Lecture();
        lecturerService.assignNewLecture(lecturer.getId(), lecture);
        assertThat(lecturerService.findLecturerById(lecturer.getId()).getLectures())
                .contains(lecture)
                .isNotEmpty();
    }

    @Test
    public void createLecturerTest(){
        Lecturer lecturer2 = new Lecturer("feri", "a", "b", "Drevo", true);
        lecturerService.createLecturer(lecturer2);

        assertThat(lecturerService.findLecturerById(lecturer2.getId())).isEqualTo(lecturer2);
    }

    @Test
    public void findByIdTest() {
        assertThat(lecturerService.findLecturerById(lecturer.getId())).isEqualTo(lecturer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByIdNullTest() {
        lecturerService.findLecturerById(null);
    }

    @Test
    public void findAllTest() {
        List<Lecturer> lectures = lecturerService.findAllLecturers();
        assertThat(lectures.size()).isEqualTo(1);
        assertThat(lectures).contains(lecturer);
    }

    @Test
    public void findByLanguageTest() {
        assertThat(lecturerService.findLecturersByLanguage(Language.ENGLISH)).contains(lecturer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByLanguageNullTest() {
        lecturerService.findLecturersByLanguage(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByNameNullTest() {
        lecturerService.findLecturerByFirstName(null);
    }


    @Test
    public void updateCourseTest() {
        lecturer.setFirstName("Test");
        lecturerService.updateLecturer(lecturer);

        Lecturer byId = lecturerService.findLecturerById(lecturer.getId());
        assertThat(byId.getFirstName()).isEqualTo("Test");
    }

    @Test
    public void deleteCourseTest() {
        lecturerService.deleteLecturer(lecturer.getId());

        assertThat(lecturerService.findAllLecturers()).isEmpty();
    }

}
