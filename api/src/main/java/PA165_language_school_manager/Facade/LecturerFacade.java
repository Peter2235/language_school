package PA165_language_school_manager.Facade;

import PA165.language_school_manager.DTO.LectureDTO;
import PA165.language_school_manager.DTO.LecturerCreateDTO;
import PA165.language_school_manager.DTO.LecturerDTO;
import PA165.language_school_manager.Enums.Language;

import java.util.List;

/**
 * @author Peter Tirala
 */
public interface LecturerFacade {

    /**
     * Find lecturer by id
     *
     * @param id lecturer id
     * @return LecturerDto
     */
    LecturerDTO findLecturerById(Long id);

    /**
     * Find lecturer by first name
     * @param firstName first name of lecturer
     * @return LecturerDto
     */
    List<LecturerDTO> findLecturerByFirstName(String firstName);

    /**
     * Find lecturer by last name
     * @param lastName last name of lecturer
     * @return LecturerDto
     */
    List<LecturerDTO> findLecturerByLastName(String lastName);

    /**
     * Find lecturer by lecture he has assigned
     * @param lecture lecture
     * @return LecturerDto
     */
    LecturerDTO findLecturerByLecture(LectureDTO lecture);

    /**
     * Find all lecturers by language
     *
     * @param language language
     * @return List of LecturerDto
     */
    List<LecturerDTO> findLecturersByLanguage(Language language);

    /**
     * Assign new lecture to lecturer
     *
     * @param lecturerId lecturer id
     * @param lecture    lectured to assign
     */
    void assignNewLecture(Long lecturerId, LectureDTO lecture);

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
     * @return id of created lecturer
     */
    void createLecturer(LecturerCreateDTO newLecturer);

    /**
     * Delete lecturer
     *
     * @param id lecturerId
     */
    void deleteLecturer(Long id);
}
