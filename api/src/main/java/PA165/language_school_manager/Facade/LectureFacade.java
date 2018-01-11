package PA165.language_school_manager.Facade;

import PA165.language_school_manager.DTO.CourseDTO;
import PA165.language_school_manager.DTO.LectureCreateDTO;
import PA165.language_school_manager.DTO.LectureDTO;
import PA165.language_school_manager.DTO.LecturerDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface LectureFacade {

    /**
     * Finds lecture by ID
     * @param id - ID of the lecture
     * @return LectureDTO of searched lecture
     */
    LectureDTO findLectureById(Long id);

    /**
     * Finds all lectures
     * @return List of LectureDTOs
     */
    List<LectureDTO> findAllLectures();

    /**
     * Finds lecture by its topic
     * @param topic - topic of the lecture
     * @return - LectureDTO of searchd lecture
     */
    LectureDTO findLectureByTopic(String topic);

    /**
     * Method to delete lecture
     * @param lecture - lecture to be deleted
     */
    void deleteLecture(LectureDTO lecture);

    /**
     * Method to create lecture
     * @param lecture - lecture to be created
     */
    Long createLecture(LectureCreateDTO lecture);

    /**
     * Method to update lecture
     * @param lecture - lecture to be updated
     */
    void updateLecture(LectureDTO lecture);

    /**
     * Find all lectures by course
     *
     * @param courseDTO course
     * @return list of lectures
     */
    List<LectureDTO> findLectureByCourse(CourseDTO courseDTO);

    /**
     * method to find all lectures of given lecturer
     * @param lecturer - lecturer of lectures
     * @return List of LectureDTOs
     */
    List<LectureDTO> findLecturesByLecturer(LecturerDTO lecturer);

    /**
     * Find all lectures between the specific time
     *
     * @param from time from
     * @param to   time to
     * @return List of lectures
     */
    List<LectureDTO> findByTime(LocalDateTime from, LocalDateTime to);
}
