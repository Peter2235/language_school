package PA165.language_school_manager.service;

import PA165.language_school_manager.Dao.PersonDao;
import PA165.language_school_manager.Entities.Person;
import PA165.language_school_manager.LanguageSchoolException;
import PA165.language_school_manager.config.ServiceConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

/**
 * @author Viktor Slany
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class PersonServiceTest {

    @Mock
    private PersonDao personDao;

    @InjectMocks
    private PersonService personService = new PersonServiceImpl();

    private Person person1;
    private List<Person> people = new ArrayList<>();

    @Before
    public void beforeClass() {
        MockitoAnnotations.initMocks(this);
        TestUtils.mockPersonDao(personDao, people);
        person1 = TestUtils.createPerson("PokemonMaster", "Ferko", "nemam", "Horvath");
        personService.createPerson(person1);
    }

    @Test
    public void createPerson() {
        personService.createPerson(person1);
        assertThat(personService.findPersonById(person1.getId())).isEqualTo(person1);
    }

    @Test(expected = LanguageSchoolException.class)
    public void createNullPerson() {
        personService.createPerson(null);
    }

    @Test(expected = LanguageSchoolException.class)
    public void createPersonWithNullLastName() {
        personService.createPerson(new Person());
    }

    @Test(expected = LanguageSchoolException.class)
    public void createPersonWithNullUserName() {
        personService.createPerson(new Person());
    }

    @Test
    public void updatePerson() {
        personService.createPerson(person1);
        person1.setUserName("updated person1");
        personService.updatePerson(person1);

        Person updatedPerson = personService.findPersonById(person1.getId());
        assertThat(updatedPerson.getUserName().equals("updated person1"));
        assertThat(updatedPerson).isEqualToComparingFieldByField(person1);
    }

    @Test(expected = LanguageSchoolException.class)
    public void updateNullPerson() {
        personService.updatePerson(null);
    }

    @Test(expected = LanguageSchoolException.class)
    public void updatePersonWithNullLastName() {
        person1.setLastName(null);
        personService.updatePerson(person1);
    }

    @Test(expected = LanguageSchoolException.class)
    public void updatePersonWithNullUserName() {
        person1.setUserName(null);
        personService.updatePerson(person1);
    }

    @Test
    public void deletePerson() {
        personService.deletePerson(person1);
        assertThat(personService.findAll()).isEmpty();
    }

    @Test(expected = LanguageSchoolException.class)
    public void deleteNullPerson() {
        personService.deletePerson(null);
    }

    @Test(expected = LanguageSchoolException.class)
    public void deletePersonNotInDatabase() {
        int sizeBefore = personService.findAll().size();
        personService.deletePerson(new Person());
        assertThat(personService.findAll()).hasSize(sizeBefore);
    }

    @Test
    public void findAllPersons() {
        assertThat(personService.findAll()).containsOnly(person1);
    }

    @Test
    public void findPersonById() {
        assertThat(personService.findPersonById(person1.getId())).isEqualTo(person1);
    }

    @Test(expected = LanguageSchoolException.class)
    public void findPersonByNullId() {
        personService.findPersonById(null);
    }

    @Test
    public void findPersonbyIdNotInDB() {
        assertThat(personService.findPersonById(1999L)).isNull();
    }

    @Test
    public void findPersonByLastName() {
        List<Person> adam = personService.findPersonsByLastName(person1.getLastName());
        assertThat(adam).containsOnly(person1);
    }

    @Test(expected = LanguageSchoolException.class)
    public void findPersonByLastNameNull() {
        personService.findPersonsByLastName(null);
    }

    @Test
    public void findPersonByUserName() {
        Person adam = personService.findPersonByUserName(person1.getUserName());
        assertThat(adam).isEqualToComparingFieldByField(person1);
    }

    @Test(expected = LanguageSchoolException.class)
    public void findPersonByUserNameNull() {
        personService.findPersonsByLastName(null);
    }
}
