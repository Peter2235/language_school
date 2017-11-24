/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165_language_school_manager.Facade;

import PA165.language_school_manager.DTO.PersonDTO;
import java.util.Collection;

/**
 *
 * @author MatĂşĹˇ
 */
public interface PersonFacade {
    PersonDTO findPersonById(Long id);
    PersonDTO findPersonByFullName(String fullName);
    
    /**
     * Get all people
     * 
     */
    Collection<PersonDTO> getAllPersons();
}