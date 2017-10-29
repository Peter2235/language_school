/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.Dao;

import PA165.language_school_manager.Entities.Lecture;
import PA165.language_school_manager.Entities.Lecturer;
import java.util.List;

/**
 *
 * @author Matúš Sedlák
 */
public interface LectureDao {
    public Lecture findById(Long id);
    public void create(Lecture l);
    public void delete(Lecture l);
    public void update(Lecture l);
    public List<Lecture> findAll();
    public Lecture findByTopic(String l);
}
