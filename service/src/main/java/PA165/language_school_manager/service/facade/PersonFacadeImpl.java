/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.service.facade;

import PA165.language_school_manager.DTO.PersonDTO;
import PA165_language_school_manager.Facade.PersonFacade;
import java.util.Collection;
/**
 *
 * @author Matúš
 */
public class PersonFacadeImpl implements PersonFacade{

    @Override
    public PersonDTO findPersonById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PersonDTO findPersonByFullName(String fullName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<PersonDTO> getAllPersons() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
