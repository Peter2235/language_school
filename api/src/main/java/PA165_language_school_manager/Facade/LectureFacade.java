package PA165_language_school_manager.Facade;

import PA165.language_school_manager.DTO.LectureCreateDTO;
import PA165.language_school_manager.DTO.LectureDTO;

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

}
