package PA165.language_school_manager.facade;

import PA165.language_school_manager.DTO.PersonCreateDTO;
import PA165.language_school_manager.DTO.PersonDTO;
import PA165.language_school_manager.config.ServiceConfiguration;
import PA165.language_school_manager.service.BeanMappingService;
import PA165.language_school_manager.service.PersonService;
import PA165_language_school_manager.Facade.PersonFacade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Viktor Slany
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class PersonFacadeImplTest {

    @InjectMocks
    private PersonFacade personFacade = new PersonFacadeImpl();

    @Inject
    @Spy
    private BeanMappingService mapper;

    @Mock
    private PersonService personService;

    private PersonDTO person1;
    private PersonDTO person2;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        PersonCreateDTO personCreate1 = new PersonCreateDTO();
        PersonCreateDTO personCreate2 = new PersonCreateDTO();

        personCreate1.setLastName("lebo");
        personCreate1.setUserName("nam");

        personCreate2.setLastName("hadze");
        personCreate2.setUserName("errory");

        person1 = personFacade.createPerson(personCreate1);
        person2 = personFacade.createPerson(personCreate2);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void findPersonById() {
        assertThat(personFacade.findPersonById(person1.getId())).isEqualToComparingFieldByField(person1);
    }

    @Test
    public void findPersonByUserName() {
        assertThat(personFacade.findPersonByUserName(person1.getUserName())).isEqualToComparingFieldByField(person1);
    }

    @Test
    public void findPersonsByLastName() {
        assertThat(personFacade.findPersonsByLastName(person1.getLastName())).containsOnlyOnce(person1);
    }

    @Test
    public void getAllPersons() {
        assertThat(personFacade.getAllPersons()).hasSize(2).containsExactlyInAnyOrder(person1, person2);
    }

    @Test
    public void createPerson() {
        assertThat(personFacade.getAllPersons()).hasSize(2).contains(person1, person2);
        PersonCreateDTO personCreate3 = new PersonCreateDTO();
        personCreate3.setLastName("prosiiim");
        personCreate3.setUserName("pomooc");
        PersonDTO person3 = personFacade.createPerson(personCreate3);
        assertThat(personFacade.getAllPersons()).hasSize(3).contains(person1, person2, person3);

    }

    @Test
    public void updatePerson() {
        person1.setUserName("not today");
        personFacade.updatePerson(person1);
        assertThat(personFacade.findPersonById(person1.getId())).isEqualToComparingFieldByField(person1);
    }

    @Test
    public void deletePerson() {
        assertThat(personFacade.getAllPersons()).hasSize(2).contains(person1, person2);
        personFacade.deletePerson(person2);
        assertThat(personFacade.getAllPersons()).hasSize(3).contains(person1);

    }
}