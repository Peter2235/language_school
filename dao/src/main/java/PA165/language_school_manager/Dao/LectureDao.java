/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.Dao;

import PA165.language_school_manager.Entities.Lecture;

import java.util.List;

/**
 * @author Matúš Sedlák
 */
public interface LectureDao {

    /**
     * Get lecture with given id
     *
     * @param id - id of lecture
     * @return lecture with given id
     */
    public Lecture findById(Long id);

    /**
     * Store lecture given as parameter in database
     *
     * @param l - lecture to be stored in database
     */
    public void create(Lecture l);

    /**
     * Delete lecture from database
     *
     * @param l - lecture which should be deleted
     */
    public void delete(Lecture l);

    /**
     * Update the lecture information in database
     *
     * @param l - Lecture which should be updated
     */
    public void update(Lecture l);

    /**
     * Retrieve all lectures from database
     *
     * @return List of lectures stored in database
     */
    public List<Lecture> findAll();

    /**
     * Get lecture with given topic (every lecture has a unique topic)
     *
     * @param l - Topic of the searched lecture
     * @return Lecture with given topic
     */
    public Lecture findByTopic(String l);
}
