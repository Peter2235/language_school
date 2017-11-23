/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.Dao;

import PA165.language_school_manager.Entities.Course;
import PA165.language_school_manager.Enums.Language;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jan Safarik
 */
@Repository
public class CourseDaoImpl implements CourseDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Course course) {

        em.persist(course);

    }

    @Override
    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    @Override
    public List<Course> findAll() {
        return em.createQuery("select c from Course c", Course.class).getResultList();
    }

    @Override
    public List<Course> findByLanguage(Language language) {
        if (language == null) {
            return new ArrayList<>();
        }
        List<Course> listToReturn = em.createQuery("select c from Course c where language = :language", Course.class).setParameter("language", language).getResultList();

        if (listToReturn == null) {
            listToReturn = new ArrayList<>();
        }
        return listToReturn;
    }

    @Override
    public Course findByName(String name) {
        try {
            return em.createQuery("select c from Course c where name = :name", Course.class)
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void delete(Course course) {
        em.remove(course);
    }

    @Override
    public void update(Course course) {
        if (course == null) {
            throw new IllegalArgumentException();
        }
        em.merge(course);
    }
}
