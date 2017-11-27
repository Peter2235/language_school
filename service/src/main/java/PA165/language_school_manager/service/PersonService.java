/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.service;

import PA165.language_school_manager.Entities.Person;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Matúš
 */
@Service
public interface PersonService {
	/**
	 * Get all persons
         * @return 
	 */
	List<Person> findAll();

        /**
         * 
         * @param personId
         * @return 
         */
	Person findPersonById(Long personId);

        /**
         * 
         * @param userName
         * @return 
         */
        Person findPersonByUserName(String userName);
        
        /**
         * 
         * @param fullName
         * @return 
         */
	List<Person> findPersonsByLastName(String fullName);
        
        /**
         * 
         * @param person 
         */
        void createPerson(Person person);
        
        /**
         * 
         * @param person 
         */
        void updatePerson(Person person);
        
        /**
         * 
         * @param id 
         */
        void deletePerson(Person person);
}
