//
//package PA165.language_school_manager.facade;
//
//
//import PA165.language_school_manager.DTO.PersonCreateDTO;
//import PA165.language_school_manager.DTO.PersonDTO;
//import PA165.language_school_manager.config.ServiceConfiguration;
//import PA165_language_school_manager.Facade.PersonFacade;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
///**
// * @author Viktor Slany
// */
//
//@DirtiesContext
//@ContextConfiguration(classes = ServiceConfiguration.class)
//public class PersonFacadeImplTest extends AbstractTestNGSpringContextTests {
//
//    //@Autowired
//    private PersonFacade personFacade;
//
//    private PersonDTO person1;
//    private PersonDTO person2;
//
//
//    @BeforeMethod
//    public void setUp() {
//        PersonCreateDTO personCreate1 = new PersonCreateDTO();
//        PersonCreateDTO personCreate2 = new PersonCreateDTO();
//
//        personCreate1.setLastName("lebo");
//        personCreate1.setUserName("nam");
//
//        personCreate2.setLastName("hadze");
//        personCreate2.setUserName("errory");
//
//        person1 = personFacade.createPerson(personCreate1);
//        person2 = personFacade.createPerson(personCreate2);
//    }
//
//    @AfterMethod
//    public void tearDown() throws Exception {
//
//    }
//
//    @Test
//    public void findPersonById() {
//        assertThat(personFacade.findPersonById(person1.getId())).isEqualToComparingFieldByField(person1);
//    }
//
//    @Test
//    public void findPersonByUserName() {
//        assertThat(personFacade.findPersonByUserName(person1.getUserName())).isEqualToComparingFieldByField(person1);
//    }
//
//    @Test
//    public void findPersonsByLastName() {
//        assertThat(personFacade.findPersonsByLastName(person1.getLastName())).containsOnlyOnce(person1);
//    }
//
//    @Test
//    public void getAllPersons() {
//        assertThat(personFacade.getAllPersons()).hasSize(2).containsExactlyInAnyOrder(person1, person2);
//    }
//
//    @Test
//    public void createPerson() {
//        assertThat(personFacade.getAllPersons()).hasSize(2).contains(person1, person2);
//        PersonCreateDTO personCreate3 = new PersonCreateDTO();
//        personCreate3.setLastName("prosiiim");
//        personCreate3.setUserName("pomooc");
//        PersonDTO person3 = personFacade.createPerson(personCreate3);
//        assertThat(personFacade.getAllPersons()).hasSize(3).contains(person1, person2, person3);
//
//    }
//
//    @Test
//    public void updatePerson() {
//        person1.setUserName("not today");
//        personFacade.updatePerson(person1);
//        assertThat(personFacade.findPersonById(person1.getId())).isEqualToComparingFieldByField(person1);
//    }
//
//    @Test
//    public void deletePerson() {
//        assertThat(personFacade.getAllPersons()).hasSize(2).contains(person1, person2);
//        personFacade.deletePerson(person2);
//        assertThat(personFacade.getAllPersons()).hasSize(3).contains(person1);
//
//    }
//}