package PA165.language_school_manager.Dao;

import PA165.language_school_manager.Entities.Person;

import java.util.List;

/**
 * @author Viktor Slaný
 */
public interface PersonDao {

    /**
     * Find person by Id.
     *
     * @param id identification number.
     * @return Person with this identification number.
     */
    Person findById(Long id);

    /**
     * Find person by unique username.
     * @param userName unique username.
     * @return Person with this username.
     */
    Person findByUserName(String userName);
    
    /**
     * Find people by last name.
     * @param lastName 
     * @return List of persons with given last name.
     */
    List<Person> findByLastName(String lastName);
    
    /**
     * Create and add person to database.
     *
     * @param person Person in language school.
     */
    Person create(Person person);

    /**
     * Update person in database.
     *
     * @param person Person in language school.
     */
    Person update(Person person);

    /**
     * Delete person from database.
     *
     * @param person Person in language school.
     */
    Person delete(Person person);

    /**
     * Find all persons in database.
     *
     * @return List of all persons in database.
     */
    List<Person> findAll();
}
