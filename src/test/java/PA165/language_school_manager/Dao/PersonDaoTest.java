/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.Dao;

import PA165.language_school_manager.ApplicationContext;
import PA165.language_school_manager.Dao.PersonDao;
import PA165.language_school_manager.Entities.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Assert;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Matúš
 */
@ContextConfiguration(classes = ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PersonDaoTest extends AbstractTestNGSpringContextTests {
    
    @PersistenceContext
    public EntityManager em;
    
    @Autowired
    private PersonDao personDao;
    
    private Person p1;
    private Person p2;
    private Person p3;
  
    
    @BeforeMethod
    public void createPeople() {
        p1 = new Person();
        p2 = new Person();
        p3 = new Person();
        
        p1.setFirstName("Adam");
        p2.setFirstName("Boris");
        p3.setFirstName("Cyril");
        
        p1.setLastName("Adamovic");
        p2.setLastName("Borisovic");
        p3.setLastName("Cyrilovic");
                
        personDao.create(p1);
        personDao.create(p2);
        personDao.create(p3);
    }
    
    @Test
    public void createPerson(){
        assertThat(personDao.findById(p1.getId()))
                .isNotNull();
        assertThat(p1.getFirstName())
                .isEqualTo("Adam");
    }
    
    @Test
    public void findPersonById(){
        Person person = personDao.findById(p1.getId());
        assertThat(person.getFirstName())
                .isEqualTo("Adam");
        assertThat(person.getLastName())
                .isEqualTo("Adamovic");
    }
    
    @Test
    public void findAllPeople(){
        List<Person> people = personDao.findAll();
        assertThat(people)
                .hasSize(3);
    }
    
    @Test
    public void deletePerson(){
        assertThat(personDao.findById(p2.getId()))
                .isNotNull();
        personDao.delete(p2);
        assertThat(personDao.findById(p2.getId()))
                .isNull();
    }
    
    @Test
    public void updatePerson(){
        p1.setFirstName("Peter");
        personDao.update(p1);
        assertThat(p1.getFirstName())
                .isEqualTo("Peter");
    }   
}
