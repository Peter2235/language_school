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

    private Long counter = 10L;
    private Map<Long, Person> personMap = new HashMap<>();

    private Person person1;
    private Person person2;
    private Person testedPerson;

    @Before
    public void beforeClass() {
        MockitoAnnotations.initMocks(this);

        /*when(personDao.create(any(Person.class))).then(invoke -> {
            Person mockedPerson = invoke.getArgumentAt(0, Person.class);

            if (mockedPerson == null) {
                throw new IllegalArgumentException("Person is null");
            }
            if (mockedPerson.getId() != null) {
                throw new IllegalArgumentException("Person is already in DB");
            }
            if (mockedPerson.getUserName() == null) {
                throw new IllegalArgumentException("Person username cant be null");
            }
            if (mockedPerson.getLastName() == null) {
                throw new IllegalArgumentException("Person lastname cant be null");
            }
            if (chceckPersonUsernameDubplicity(mockedPerson.getUserName(), -1L)) {
                throw new IllegalArgumentException("Person username already exists");
            }
            Long id = counter;
            counter++;
            mockedPerson.setId(id);
            personMap.put(id, mockedPerson);
            return mockedPerson;
        });

        when(personDao.update(any(Person.class))).then(invoke -> {
            Person mockedPerson = invoke.getArgumentAt(0, Person.class);

            if (mockedPerson == null) {
                throw new IllegalArgumentException("Person is null");
            }
            if (mockedPerson.getId() != null) {
                throw new IllegalArgumentException("Person is already in DB");
            }
            if (mockedPerson.getUserName() == null) {
                throw new IllegalArgumentException("Person username cant be null");
            }
            if (mockedPerson.getLastName() == null) {
                throw new IllegalArgumentException("Person lastname cant be null");
            }
            if (chceckPersonUsernameDubplicity(mockedPerson.getUserName(), -1L)) {
                throw new IllegalArgumentException("Person username already exists");
            }
            personMap.replace(mockedPerson.getId(), mockedPerson);
            return mockedPerson;
        });

        when(personDao.delete(any(Person.class))).then(invoke -> {
            Person mockedPerson = invoke.getArgumentAt(0, Person.class);

            if (mockedPerson == null) {
                throw new IllegalArgumentException("Person is null");
            }
            if (mockedPerson.getId() == null) {
                throw new IllegalArgumentException("Person is not in DB");
            }
            personMap.remove(mockedPerson.getId(), mockedPerson);
            return mockedPerson;
        });

        when(personDao.findById(anyLong())).then(invoke -> {
            Long id = invoke.getArgumentAt(0, Long.class);
            if (id == null) {
                throw new IllegalArgumentException("Id is null");
            }
            return personMap.get(id);
        });

        when(personDao.findByUserName(anyString())).then(invoke -> {
            String username = invoke.getArgumentAt(0, String.class);
            if (username == null) {
                throw new IllegalArgumentException("Username is null");
            }
            return personMap.get(username);
        });

        when(personDao.findByLastName(anyString())).then(invoke -> {
            String lastname = invoke.getArgumentAt(0, String.class);
            if (lastname == null) {
                throw new IllegalArgumentException("Lastname is null");
            }
            return personMap.get(lastname);
        });*/

        when(personDao.findAll()).then(invoke -> Collections.unmodifiableList(new ArrayList<>(personMap.values())));

    }

    @Before
    public void beforeTest() {
        personMap.clear();

        person1 = new Person(1L);
        person1.setFirstName("marek");
        person1.setMiddleName("rudolf");
        person1.setLastName("vysoky");
        person1.setUserName("xXx_King_Pl_xXx");

        person2 = new Person(2L);
        person2.setFirstName("Java");
        person2.setMiddleName("je");
        person2.setLastName("proste");
        person2.setUserName("NAJLEPSIA");

        personMap.put(1L, person1);
        personMap.put(2L, person2);

        testedPerson = new Person();
        testedPerson.setUserName("DanoDrevoFanboy");
        testedPerson.setFirstName("Gábor");
        testedPerson.setMiddleName("37Centimetrov");
        testedPerson.setLastName("Székesfehervárbalatonszavadisóstó");
    }

    @Test(expected = LanguageSchoolException.class)
    public void createPerson(){
        int sizeBefore = personMap.values().size();

        personService.createPerson(testedPerson);
        assertThat(personMap.values()).hasSize(sizeBefore + 1).contains(testedPerson);

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

    @Test(expected = LanguageSchoolException.class)
    public void createPersonWithDuplicatedUserName() {
        testedPerson.setUserName(person1.getUserName());
        personService.createPerson(testedPerson);
    }

    @Test(expected = LanguageSchoolException.class)
    public void createPersonWithNotNullId() {
        testedPerson = new Person(counter * 3L);
        testedPerson.setUserName("DanoDrevoFanboy");
        testedPerson.setLastName("Székesfehervárbalatonszavadisóstó");
        personService.createPerson(testedPerson);
    }

    @Test
    public void updatePerson() {
        person1.setUserName("updated person1");
        personService.updatePerson(person1);

        Person updatedPerson = personMap.get(person1.getId());
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

    @Test(expected = LanguageSchoolException.class)
    public void updatePersonWithDuplicatedUserName() {
        person1.setUserName("NAJLEPSIA");
        personService.updatePerson(person1);
    }

    @Test
    public void deletePerson() {
        int sizeBefore = personMap.values().size();
        personService.deletePerson(person1);
        assertThat(personMap.values()).hasSize(sizeBefore - 1).doesNotContain(person1);
    }

    @Test(expected = LanguageSchoolException.class)
    public void deleteNullPerson() {
        personService.deletePerson(null);
    }

    @Test(expected = LanguageSchoolException.class)
    public void deletePersonNotInDatabase() {
        int sizeBefore = personMap.values().size();
        personService.deletePerson(testedPerson);
        assertThat(personMap.values()).hasSize(sizeBefore).doesNotContain(testedPerson);
    }


    @Test
    public void findAllPersons() {
        assertThat(personService.findAll()).containsExactlyInAnyOrder(person1, person2);
    }

    @Test(expected = LanguageSchoolException.class)
    public void findPersonById() {
        Person adam = personService.findPersonById(person1.getId());
        assertThat(adam).isEqualToComparingFieldByField(person1);
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
        assertThat(adam).containsOnlyOnce(person1);
    }

    @Test
    public void findPersonByLastNameNotInDB() {
        List<Person> adam = personService.findPersonsByLastName("stefan");
        assertThat(adam).isEmpty();
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
    public void findPersonByUserNameNotInDB() {
        personService.findPersonByUserName("stefan");
    }

    @Test(expected = LanguageSchoolException.class)
    public void findPersonByUserNameNull() {
        personService.findPersonsByLastName(null);
    }

    private boolean chceckPersonUsernameDubplicity(String name, Long id) {
        for (Person person : personMap.values()) {
            if (person.getUserName().equals(name) && person.getId() != id) {
                return true;
            }
        }
        return false;
    }
}
