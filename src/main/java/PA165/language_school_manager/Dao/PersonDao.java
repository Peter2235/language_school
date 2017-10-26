package PA165.language_school_manager.Dao;

import PA165.language_school_manager.Entities.Person;

import java.util.List;

/**
 *
 * @author Viktor Slaný
 */
public interface PersonDao {

    Person findById(Long id);
    void create(Person person);
    void delete(Person person);
    List<Person> findAll();
}
