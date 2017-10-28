/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.Dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.testng.annotations.Test;
import PA165.language_school_manager.Entities.Lecturer;
import PA165.language_school_manager.Enums.Language;
import static org.assertj.core.api.Assertions.*;
import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.Dao.LecturerDAO;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;


/**
 *
 * @author Jan Safarik
 */
public class LecturerDaoTest {

    @Autowired
    private LecturerDAO lecturerDao;
    
    @PersistenceContext
    private EntityManager em;
    
    Lecturer l1;
    Lecturer l2;

    @BeforeMethod
    public void createLecturers(){
        
        l1 = new Lecturer("Adam", null, "Johnson", new HashSet<>(), false);
        l1.addLanguage(Language.ITALIAN);
        l2 = new Lecturer("Eve", null, "Smith", new HashSet<>(), true);
        l2.addLanguage(Language.GERMAN);
        l2.addLanguage(Language.SPANISH);
        lecturerDao.create(l1);
        lecturerDao.create(l2);
        
    }
    
    @Test
    public void createLecturer() {

        Lecturer lecturer = new Lecturer("John", null, "Doe", new HashSet<>(), true);
        lecturer.addLanguage(Language.ENGLISH);
        lecturerDao.create(lecturer);
        
        assertThat(lecturer.getId()).isNotNull();

        Lecturer isHeTheSame = em.find(Lecturer.class, lecturer.getId());

        assertThat(isHeTheSame).isEqualToComparingFieldByField(lecturer);
        
//        assertThat(isHeTheSame.getId()).isEqualTo(lecturer.getId());
//        assertThat(isHeTheSame.getFirstName()).isEqualTo(lecturer.getId());
//        assertThat(isHeTheSame.getLastName()).isEqualTo(lecturer.getId());
//        assertThat(isHeTheSame.getLanguages()).isEqualTo(lecturer.getLanguages());

    }

    @Test
    public void deleteLecuterer() {

        //todo

    }
}
