package PA165.language_school_manager.Dao;

import PA165.language_school_manager.Entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author Viktor Slaný
 */
public class PersonDaoImpl implements PersonDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Person findById(Long id) {
        return entityManager.find(Person.class,id);
    }

    @Override
    public void create(Person person) {
        entityManager.persist(person);
    }

    @Override
    public void delete(Person person) {
        entityManager.remove(person);
    }

    @Override
    public List<Person> findAll() {
        return entityManager.createQuery("select person from Person person" , Person.class).getResultList();
    }
}
