/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.Facade;

import PA165.language_school_manager.DTO.PersonAuthenticateDTO;
import PA165.language_school_manager.DTO.PersonCreateDTO;
import PA165.language_school_manager.DTO.PersonDTO;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author Matúš Sedlák
 */
public interface PersonFacade {
    PersonDTO findPersonById(Long id);
    PersonDTO findPersonByUserName(String userName);
    List<PersonDTO> findPersonsByLastName(String lastName);
    
    /**
     * Get all people
     * 
     */
    List<PersonDTO> getAllPersons();
    
    /**
     * Method used to store person given as a parameter
     * @param person
     */
    PersonDTO createPerson(PersonCreateDTO person);
    
    /**
     * 
     * @param person - person which should be updated
     */
    void updatePerson(PersonDTO person);
    
    /**
     * Method used to delete person
     * @param person 
     */
    void deletePerson(PersonDTO person);
    
    /**
     * Method used to check whether Person is admin or not
     * @param person
     * @return 
     */
    boolean isAdmin(PersonDTO person);
    
    /**
     * 
     * @param person
     * @return 
     */
    boolean authenticate(PersonAuthenticateDTO person);
}
