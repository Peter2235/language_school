/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager;

import PA165.language_school_manager.Dao.LecturerDAO;
import PA165.language_school_manager.Entities.Lecturer;
import PA165.language_school_manager.Enums.Language;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author Jan Safarik
 */
@ContextConfiguration(classes = ApplicationContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class LecturerDaoTest {

    @Autowired
    private LecturerDAO lecturerDao;

    private Lecturer l1;
    private Lecturer l2;

    @Before
    public void createLecturers() {
        l1 = new Lecturer("lecturer1", "Christopher", "Biggie Smalls", "Wallace", Language.ITALIAN, false);
        l2 = new Lecturer("lecturer2", "Lamont", "Big L", "Coleman", Language.GERMAN, true);
        lecturerDao.create(l1);
        lecturerDao.create(l2);
    }

    @Test
    public void createLecturer() {
        Lecturer lecturer = new Lecturer("lecturer", "Albert", "Prodigy", "Johnson", Language.ENGLISH, true);
        lecturerDao.create(lecturer);

        assertThat(lecturer.getId()).isNotNull();

        Lecturer isHeTheSame = lecturerDao.findById(lecturer.getId());

        assertThat(isHeTheSame).isEqualToComparingFieldByField(lecturer);
    }

    @Test
    public void deleteLecturer() {
        assertThat(lecturerDao.findById(l1.getId())).isNotNull();
        lecturerDao.delete(l1);
        assertThat(lecturerDao.findById(l1.getId())).isNull();
        lecturerDao.delete(l2);
        assertThat(lecturerDao.findAll()).isEmpty();
    }

    @Test
    public void updateLecturer() {
        l1.setFirstName("Tupac");
        l1.setMiddleName("Amaru");
        l1.setLastName("Shakur");
        l1.setLanguage(Language.ENGLISH);
        lecturerDao.update(l1);

        assertThat(lecturerDao.findById(l1.getId()).getFirstName()).isEqualTo("Tupac");
        assertThat(lecturerDao.findById(l1.getId()).getMiddleName()).isEqualTo("Amaru");
        assertThat(lecturerDao.findById(l1.getId()).getLastName()).isEqualTo("Shakur");
        assertThat(lecturerDao.findById(l1.getId()).getLanguage()).isEqualTo(Language.ENGLISH);

    }

    @Test
    public void findLecturerById() {
        assertThat(lecturerDao.findById(l1.getId())).isEqualTo(lecturerDao.findById(l1.getId()));
        Lecturer lecturer = lecturerDao.findById(l2.getId());

        assertThat(lecturer.getFirstName()).isEqualTo("Lamont");
        assertThat(lecturer.getMiddleName()).isEqualTo("Big L");
        assertThat(lecturer.getLastName()).isEqualTo("Coleman");
        assertThat(lecturer.getLanguage()).isEqualTo(Language.GERMAN);
    }

    @Test
    public void findAllLecturers() {
        List<Lecturer> lecturers = lecturerDao.findAll();

        assertThat(lecturers.size()).isEqualTo(2);
        assertThat(lecturers.contains(l1)).isTrue();
        assertThat(lecturers.contains(l2)).isTrue();
    }

    @Test
    public void findLecturersByLanguage() {
        Lecturer l3 = new Lecturer("lectuer", "Sean", null, "Price", Language.GERMAN, true);
        lecturerDao.create(l3);

        List<Lecturer> lecturers = lecturerDao.findByLanguage(Language.GERMAN);
        assertThat(lecturers.size()).isEqualTo(2);
        assertThat(lecturers.contains(l3)).isTrue();
        assertThat(lecturers.contains(l2)).isTrue();
    }

    @Test
    public void findLecturerByLanguageNull() {
        List<Lecturer> lecturers = lecturerDao.findByLanguage(null);
        assertThat(lecturers).isEmpty();
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByIdNull() {
        lecturerDao.findById(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createLecturerNull() {
        lecturerDao.create(null);
    }
}
