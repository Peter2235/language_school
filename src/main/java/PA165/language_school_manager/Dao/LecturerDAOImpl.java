package PA165.language_school_manager.Dao;

import PA165.language_school_manager.Entities.Lecturer;
import PA165.language_school_manager.Enums.Language;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author Viktor Slaný
 */
public class LecturerDAOImpl implements LecturerDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Lecturer findById(Long id) {
        return entityManager.find(Lecturer.class,id);
    }

    @Override
    public void create(Lecturer lecturer) {
        entityManager.persist(lecturer);
    }

    @Override
    public void update(Lecturer lecturer) {
        entityManager.merge(lecturer);
    }

    @Override
    public void delete(Lecturer lecturer) {
        entityManager.remove(entityManager.merge(lecturer));
    }

    @Override
    public List<Lecturer> findAll() {
        return entityManager.createQuery("select lecturer from Lecturer lecturer",Lecturer.class).getResultList();
    }

    @Override
    public List<Lecturer> findByLanguage(Language language) {
        try {
            return entityManager.createQuery("select lecturer from Lecturer lecturer where language = :language",Lecturer.class)
                    .setParameter("language",language).getResultList();
        }catch (NoResultException e){
            return null;
        }

    }
}
