package PA165.language_school_manager.service;

import PA165.language_school_manager.Entities.Lecture;
import PA165.language_school_manager.Entities.Lecturer;
import PA165.language_school_manager.Enums.Language;

import java.util.List;

/**
 * @author Peter Tirala
 */
public interface LecturerService {
    /**
     * Find lecturer by id
     *
     * @param id lecturer id
     * @return LecturerDto
     */
    Lecturer findLecturerById(Long id);

    /**
     * Find lecturer by first name
     *
     * @param firstName first name of lecturer
     * @return LecturerDto
     */
    List<Lecturer> findLecturerByFirstName(String firstName);

    /**
     * Find lecturer by last name
     *
     * @param lastName last name of lecturer
     * @return LecturerDto
     */
    List<Lecturer> findLecturerByLastName(String lastName);

    /**
     * Find lecturer by lecture he has assigned
     *
     * @param lecture lecture
     * @return LecturerDto
     */
    Lecturer findLecturerByLecture(Lecture lecture);

    /**
     * Find all lecturers by language
     *
     * @param language language
     * @return List of LecturerDto
     */
    List<Lecturer> findLecturersByLanguage(Language language);

    /**
     * Assign new lecture to lecturer
     *
     * @param lecturerId lecturer id
     * @param lecture    lectured to assign
     */
    void assignNewLecture(Long lecturerId, Lecture lecture);

    /**
     * Add new language to lecturer
     *
     * @param lecturerId lecturer id
     * @param language   language to add
     */
    void addLanguage(Long lecturerId, Language language);

    /**
     * Create new lecturer
     *
     * @param newLecturer lecturerCreateDto
     */
    void createLecturer(Lecturer newLecturer);

    /**
     * Delete lecturer
     *
     * @param id lecturerId
     */
    void deleteLecturer(Long id);
    
    /**
     * Update lecturer
     * @param lecturer lecturer to update
     */
    void updateLecturer(Lecturer lecturer);
}
