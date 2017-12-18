/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.facade;

import PA165.language_school_manager.DTO.LectureDTO;
import PA165.language_school_manager.DTO.PersonAuthenticateDTO;
import PA165.language_school_manager.DTO.PersonCreateDTO;
import PA165.language_school_manager.service.BeanMappingService;
import PA165.language_school_manager.DTO.PersonDTO;
import PA165.language_school_manager.Entities.Lecture;
import PA165.language_school_manager.Entities.Person;
import PA165.language_school_manager.service.PersonService;
import PA165.language_school_manager.Facade.PersonFacade;
import static PA165.language_school_manager.facade.LectureFacadeImpl.log;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Matúš
 */
@Service
@Transactional
public class PersonFacadeImpl implements PersonFacade {
    
    final static org.slf4j.Logger log = LoggerFactory.getLogger(LectureFacadeImpl.class);

    @Autowired
    private PersonService personService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public PersonDTO findPersonById(Long id) {
        Person person = personService.findPersonById(id);
        log.warn(person.getLectures().toString());
        PersonDTO personDTO = beanMappingService.mapTo(person, PersonDTO.class);
        log.warn(personDTO.getLectures().toString());
        for (Lecture lecture: person.getLectures()) {
            log.warn(lecture.getId().toString());
            LectureDTO lectureDTO = beanMappingService.mapTo(lecture, LectureDTO.class);
            log.warn(lectureDTO.getId().toString());
            personDTO.addLecture(lectureDTO);
        }
        log.warn(personDTO.getLectures().toString());
        return (person == null) ? null : personDTO;
    }

    @Override
    public PersonDTO findPersonByUserName(String userName) {
        Person person = personService.findPersonByUserName(userName);
        return (person == null) ? null : beanMappingService.mapTo(person, PersonDTO.class);
    }

    @Override
    public List<PersonDTO> findPersonsByLastName(String lastName) {
        List<Person> persons = personService.findPersonsByLastName(lastName);
        return beanMappingService.mapTo(persons, PersonDTO.class);
    }

    @Override
    public List<PersonDTO> getAllPersons() {
        return beanMappingService.mapTo(personService.findAll(), PersonDTO.class);
    }

    @Override
    @Transactional
    public PersonDTO createPerson(PersonCreateDTO person) {
        Person mapPerson = beanMappingService.mapTo(person, Person.class);
        personService.createPerson(mapPerson);
        return beanMappingService.mapTo(mapPerson, PersonDTO.class);
    }

    @Override
    public void updatePerson(PersonDTO person) {
        Person mapPerson = beanMappingService.mapTo(person, Person.class);
        for (LectureDTO lecture : person.getLectures()){
            Lecture lecture2 = beanMappingService.mapTo(lecture, Lecture.class);
            mapPerson.addLecture(lecture2);
        }
        personService.updatePerson(mapPerson);
    }

    @Override
    public void deletePerson(PersonDTO person) {
        Person mapPerson = beanMappingService.mapTo(person, Person.class);
        personService.deletePerson(mapPerson);
    }

    @Override
    public boolean isAdmin(PersonDTO person) {
        return personService.isAdmin(beanMappingService.mapTo(person, Person.class));
    }

    @Override
    public boolean authenticate(PersonAuthenticateDTO person) {
        return personService.authenticate(
                personService.findPersonByUserName(person.getUserName()), person.getPassword());
    }

}
