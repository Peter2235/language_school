package PA165.language_school_manager.Dao;

import PA165.language_school_manager.Entities.Lecturer;
import PA165.language_school_manager.Enums.Language;

import java.util.List;

/**
 * @author Viktor Slaný
 */
public interface LecturerDAO {

    /**
     * Find lecturer by Id.
     *
     * @param id identification number.
     * @return Lecturer with this identification number.
     */
    Lecturer findById(Long id);

    /**
     * Create and add lecturer to database.
     *
     * @param lecturer lecturer in language school.
     */
    void create(Lecturer lecturer);

    /**
     * Update lecturer in database.
     *
     * @param lecturer lecturer in language school.
     */
    void update(Lecturer lecturer);

    /**
     * Update lecturer from database.
     *
     * @param lecturer lecturer in language school.
     */
    void delete(Lecturer lecturer);

    /**
     * Find all lecturers in database.
     *
     * @return List of all lecturers in database.
     */
    List<Lecturer> findAll();

    /**
     * Find all lecturers who speak with this language.
     *
     * @param language lecturers language.
     * @return List of all lecturers in database who speak with this language.
     */
    List<Lecturer> findByLanguage(Language language);
}
