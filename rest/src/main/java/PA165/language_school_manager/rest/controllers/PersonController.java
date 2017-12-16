package PA165.language_school_manager.rest.controllers;

import PA165.language_school_manager.DTO.PersonCreateDTO;
import PA165.language_school_manager.DTO.PersonDTO;
import PA165.language_school_manager.rest.exceptions.ResourceNotFoundException;
import PA165.language_school_manager.Facade.PersonFacade;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Inject
    private PersonFacade personFacade;

    /**
     * Get Person by identifier id
     *
     * @param id identifier for a person
     * @return PersonDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final PersonDTO getPerson(@PathVariable("id") long id) throws Exception {
        PersonDTO dto = personFacade.findPersonById(id);
        if (dto!= null) {
            return dto;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Get Person by username
     *
     * @param username username
     * @return PersonDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/username/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final PersonDTO getPersonByUsername(@PathVariable("username") String username) throws Exception {
        PersonDTO dto = personFacade.findPersonByUserName(username);
        if (dto!= null) {
            return dto;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Get Person by last name
     *
     * @param lastName lastname of a person
     * @return PersonDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/lastname/{lastname}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<PersonDTO> getPersonByLastName(@PathVariable("lastname") String lastName) throws Exception {
        List<PersonDTO> dto = personFacade.findPersonsByLastName(lastName);
        if (dto!= null) {
            return dto;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Get list of Pesron
     *
     *  @return List of PersonDto
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<PersonDTO> getAll() {
        return new ArrayList<>(personFacade.getAllPersons());
    }

    /**
     * Create a new person
     *
     * @param person PersonCreateDto with required fields for creation
     * @return the created product PersonDto
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final PersonDTO createPerson(@RequestBody PersonCreateDTO person) {
            return personFacade.createPerson(person);
    }

    /**
     * Update a person
     *
     * @param person PersonDto with required fields for creation
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final void updatePerson(@RequestBody PersonDTO person) {
            personFacade.updatePerson(person);
    }

    /**
     * Delete one person
     *
     * @param id identifier for person
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deletePerson(@PathVariable("id") long id) throws Exception {
        try {
            PersonDTO person = personFacade.findPersonById(id);
            personFacade.deletePerson(person);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }
}
