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
     * Find all persons
     * @return List of all persons.
     */
    List<Person> findAll();

    /**
     * Find person with ID
     * @param personId ID f person
     * @return Person by Id
     */
    Person findPersonById(Long personId);

    /**
     * Find person with Username
     * @param userName username of person
     * @return person with username
     */
    Person findPersonByUserName(String userName);

    /**
     * Find all persons with fullname
     * @param fullName fullname of persons
     * @return all persons with fullname
     */
    List<Person> findPersonsByLastName(String fullName);

    /**
     * Create person
     * @param person person to create
     */
    void createPerson(Person person);

    /**
     * Update person
     * @param person to update
     */
    void updatePerson(Person person);

    /**
     * Delete person
     * @param person to delete
     */
    void deletePerson(Person person);

    /**
     * Find if person is admin
     * @param person if he is admin
     * @return boolean if person is admin
     */
    boolean isAdmin(Person person);

    /**
     * Authenticate person
     * @param findPersonById person with id
     * @param password password of person
     * @return validate password of person
     */
    boolean authenticate(Person findPersonById, String password);
    
    /**
     * Register person.
     * @param person person to register
     * @param unencryptedPassword unencrypted password to person
     */
    void registerPerson(Person person, String unencryptedPassword);
}
