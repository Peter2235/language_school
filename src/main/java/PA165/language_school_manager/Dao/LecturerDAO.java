package PA165.language_school_manager.Dao;

import PA165.language_school_manager.Entities.Lecturer;
import PA165.language_school_manager.Enums.Language;

import java.util.List;

/**
 *
 * @author Viktor Slaný
 */
public interface LecturerDAO {

    Lecturer findById(Long id);
    void create(Lecturer lecturer);
    void update(Lecturer lecturer);
    void delete(Lecturer lecturer);
    List<Lecturer> findAll();
    List<Lecturer> findByLanguage(Language language);
}
