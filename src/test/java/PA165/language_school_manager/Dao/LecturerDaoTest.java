/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.Dao;

import PA165.language_school_manager.ApplicationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.testng.annotations.Test;
import PA165.language_school_manager.Entities.Lecturer;
import PA165.language_school_manager.Enums.Language;
import static org.assertj.core.api.Assertions.*;
import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.Dao.LecturerDAO;
import java.util.HashSet;
import java.util.List;
import javax.inject.Inject;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;


/**
 *
 * @author Jan Safarik
 */
@ContextConfiguration(classes = ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class LecturerDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private LecturerDAO lecturerDao;
    
    @PersistenceContext
    private EntityManager em;
    
    private Lecturer l1;
    private Lecturer l2;

    @BeforeMethod
    public void createLecturers(){
        
        l1 = new Lecturer("Christopher", "Biggie Smalls", "Wallace", new HashSet<>(), false);
        l1.addLanguage(Language.ITALIAN);
        l2 = new Lecturer("Lamont", "Big L", "Coleman", new HashSet<>(), true);
        l2.addLanguage(Language.GERMAN);
        l2.addLanguage(Language.SPANISH);
        lecturerDao.create(l1);
        lecturerDao.create(l2);
        
    }
    
    @Test
    public void createLecturer() {

        Lecturer lecturer = new Lecturer("Albert", "Prodigy", "Johnson", new HashSet<>(), true);
        lecturer.addLanguage(Language.ENGLISH);
        lecturerDao.create(lecturer);
        
        assertThat(lecturer.getId()).isNotNull();

        Lecturer isHeTheSame = em.find(Lecturer.class, lecturer.getId());

        assertThat(isHeTheSame).isEqualToComparingFieldByField(lecturer);

    }

    @Test
    public void deleteLecturer() {

        assertThat(em.find(Lecturer.class, l1.getId())).isNotNull();
        lecturerDao.delete(l1);
        assertThat(em.find(Lecturer.class, l1.getId())).isNull();
        lecturerDao.delete(l2);
        assertThat(em.createQuery("select l from Lecturer l").getResultList()).isEmpty();
        
    }
    
    @Test
    public void updateLecturer(){
        
        l1.setFirstName("Tupac");
        l1.setMiddleName("Amaru");
        l1.setLastName("Shakur");
        l1.addLanguage(Language.ENGLISH);
        lecturerDao.update(l1);
        assertThat(em.find(Lecturer.class, l1.getId()).getFirstName()).isEqualTo("Tupac");
        assertThat(em.find(Lecturer.class, l1.getId()).getMiddleName()).isEqualTo("Amaru");
        assertThat(em.find(Lecturer.class, l1.getId()).getLastName()).isEqualTo("Shakur");
        assertThat(em.find(Lecturer.class, l1.getId()).getLanguages().size()).isEqualTo(2);
        
    }
    
    @Test
    public void findLecturerById(){
        
        assertThat(lecturerDao.findById(l1.getId())).isEqualTo(em.find(Lecturer.class, l1.getId()));
        Lecturer lecturer = lecturerDao.findById(l2.getId());
        assertThat(lecturer.getFirstName()).isEqualTo("Lamont");
        assertThat(lecturer.getMiddleName()).isEqualTo("Big L");
        assertThat(lecturer.getLastName()).isEqualTo("Coleman");
        assertThat(lecturer.getLanguages().size()).isEqualTo(2);
        
    }
    
    @Test
    public void findAllLecturers(){
        
        List<Lecturer> lecturers = lecturerDao.findAll();
        assertThat(lecturers.size()).isEqualTo(2);
        
        assertThat(lecturers.contains(l1)).isTrue();
        assertThat(lecturers.contains(l2)).isTrue();
        
    }
    
    @Test
    public void findLecturersByLanguage(){
        
        Lecturer l3 = new Lecturer("Sean", null, "Price", new HashSet<>(), true);
        l3.addLanguage(Language.SPANISH);
        lecturerDao.create(l3);
        
        List<Lecturer> lecturers = lecturerDao.findByLanguage(Language.SPANISH);
        assertThat(lecturers.size()).isEqualTo(2);
        assertThat(lecturers.contains(l3)).isTrue();
        assertThat(lecturers.contains(l2)).isTrue();
        
    }
    
}
