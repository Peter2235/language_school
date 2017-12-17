/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.Dao;

import PA165.language_school_manager.Entities.Course;
import PA165.language_school_manager.Entities.Lecture;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Matúš Sedlák
 */
@Repository
public class LectureDaoImpl implements LectureDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Lecture findById(Long id) {
        return em.find(Lecture.class, id);
    }

    @Override
    public void create(Lecture l) {
        em.persist(l);
    }

    @Override
    public void delete(Lecture l) {
        em.remove(em.merge(l));
    }

    @Override
    public void update(Lecture l) {
        em.merge(l);
    }

    @Override
    public List<Lecture> findAll() {
        return em.createQuery("select l from Lecture l", Lecture.class)
                .getResultList();
    }

    @Override
    public Lecture findByTopic(String t) {
        return em.createQuery("select t from Lecture t where topic = :topic", Lecture.class).setParameter("topic", t)
                .getSingleResult();
    }

    @Override
    public List<Lecture> findByCourse(Course c) {
        if (c == null) {
            return new ArrayList<>();
        }
        List<Lecture> listToReturn = em.createQuery("select l from Lecture l where course = :course", Lecture.class).setParameter("course", c).getResultList();

        if (listToReturn == null) {
            listToReturn = new ArrayList<>();
        }
        return listToReturn;
    }
}
