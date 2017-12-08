/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.facade;

import PA165.language_school_manager.DTO.PersonCreateDTO;
import PA165.language_school_manager.service.BeanMappingService;
import PA165.language_school_manager.DTO.PersonDTO;
import PA165.language_school_manager.Entities.Person;
import PA165.language_school_manager.service.PersonService;
import PA165_language_school_manager.Facade.PersonFacade;
import java.util.Collection;
import java.util.List;

import org.dozer.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Matúš
 */

@Service
@Transactional
public class PersonFacadeImpl implements PersonFacade{
    
    @Inject
    private PersonService personService;

    @Inject
    private BeanMappingService beanMappingService;
    
    @Override
    public PersonDTO findPersonById(Long id) {
        Person person = personService.findPersonById(id);
        return (person == null) ? null : beanMappingService.mapTo(person, PersonDTO.class);
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
    public Collection<PersonDTO> getAllPersons() {
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
        personService.updatePerson(mapPerson);
    }

    @Override
    public void deletePerson(PersonDTO person) {
        Person mapPerson = beanMappingService.mapTo(person, Person.class);
        personService.deletePerson(mapPerson);
    }
}
