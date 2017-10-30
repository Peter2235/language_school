package PA165.language_school_manager.Dao;

import PA165.language_school_manager.Entities.Person;

import java.util.List;

/**
 * @author Viktor Slaný
 */
public interface PersonDao {

    /**
     * Find person by Id.
     * @param id identification number.
     * @return Person with this identification number.
     */
    Person findById(Long id);

    /**
     * Create and add person to database.
     * @param person Person in language school.
     */
    void create(Person person);

    /**
     * Update person in database.
     * @param person Person in language school.
     */
    void update(Person person);

    /**
     * Delete person from database.
     * @param person Person in language school.
     */
    void delete(Person person);

    /**
     * Find all persons in database.
     * @return List of all persons in database.
     */
    List<Person> findAll();
}
