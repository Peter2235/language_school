package PA165.language_school_manager.Dao;

import PA165.language_school_manager.Entities.Lecturer;
import PA165.language_school_manager.Entities.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.persistence.NoResultException;

/**
 * @author Viktor Slaný
 */
@Repository
public class PersonDaoImpl implements PersonDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Person findById(Long id) {
        return entityManager.find(Person.class, id);
    }

    @Override
    public Person findByUserName(String userName){
        try {
            return entityManager.createQuery("select p from Person p where p.userName = :userName", Person.class)
                    .setParameter("userName", userName).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    @Override
    public List<Person> findByLastName(String lastName) {
        try {
            return entityManager.createQuery("select p from Person p where p.lastName = :lastName", Person.class)
                    .setParameter("lastName", lastName).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    @Override
    public Person create(Person person) {
        entityManager.persist(person);
        return person;
    }

    @Override
    public Person update(Person person) {
        entityManager.merge(person);
        return person;
    }

    @Override
    public Person delete(Person person) {
        entityManager.remove(entityManager.merge(person));
        return person;
    }

    @Override
    public List<Person> findAll() {
        return entityManager.createQuery("select person from Person person", Person.class).getResultList();
    } 
}
