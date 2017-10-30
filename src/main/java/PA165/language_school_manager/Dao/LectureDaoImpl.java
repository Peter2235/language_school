/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.Dao;

import PA165.language_school_manager.Entities.Lecture;
import PA165.language_school_manager.Entities.Lecturer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
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
        em.remove(l);
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
        try {
            return em.createQuery("select t from Lecture t where topic = :topic",
                    Lecture.class).setParameter(":topic", t)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    

}
