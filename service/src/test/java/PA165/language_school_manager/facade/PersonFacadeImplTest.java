package PA165.language_school_manager.facade;

import PA165.language_school_manager.DTO.PersonCreateDTO;
import PA165.language_school_manager.DTO.PersonDTO;
import PA165.language_school_manager.Entities.Person;
import PA165.language_school_manager.config.ServiceConfiguration;
import PA165.language_school_manager.service.BeanMappingService;
import PA165.language_school_manager.service.PersonService;
import PA165_language_school_manager.Facade.PersonFacade;
import org.hibernate.service.spi.ServiceException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Viktor Slany
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class PersonFacadeImplTest {

    @Autowired
    private PersonFacade personFacade;

    private PersonDTO personDTO;
    private PersonCreateDTO personCreateDTO;

    @Before
    public void setUp() throws ServiceException{



        personCreateDTO = new PersonCreateDTO();
        personCreateDTO.setUserName("BestUserName");
        personCreateDTO.setLastName("mrkvicka");


        personDTO = personFacade.createPerson(personCreateDTO);
    }
    @After
    public void tearDown(){
        for (PersonDTO personDTO: personFacade.getAllPersons()) {
            personFacade.deletePerson(personDTO);
        }
        assertThat(personFacade.getAllPersons()).isEmpty();
    }

    @Test
    public void findPersonById() {
        PersonDTO foundPersonDTO = personFacade.findPersonById(personDTO.getId());
        assertThat(foundPersonDTO).isEqualTo(personDTO);
    }

    @Test
    public void findPersonByUserName() {
        PersonDTO foundPersonDTO = personFacade.findPersonByUserName("BestUserName");
        assertThat(foundPersonDTO).isEqualTo(personDTO);
    }

    @Test
    public void findPersonsByLastName() {
        List<PersonDTO> foundPersonDTO = personFacade.findPersonsByLastName("mrkvicka");
        assertThat(foundPersonDTO).contains(personDTO);
    }

    @Test
    public void getAllPersons() {
        Collection<PersonDTO> foundPersonDTO = personFacade.getAllPersons();
        assertThat(foundPersonDTO).contains(personDTO);
    }

    @Test
    public void createPerson() {
       assertThat(personFacade.getAllPersons()).contains(personDTO);
    }

    @Test
    public void updatePerson() {
        personDTO.setUserName("kalerab");
        personFacade.updatePerson(personDTO);
        assertThat(personFacade.findPersonById(personDTO.getId())).isEqualTo(personDTO);
    }

    @Test
    public void deletePerson() {
        personFacade.deletePerson(personDTO);
        assertThat(personFacade.getAllPersons()).isEmpty();

    }
}