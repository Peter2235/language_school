package PA165_language_school_manager.Facade;

import PA165.language_school_manager.DTO.LecturerDTO;
import PA165.language_school_manager.Entities.Lecture;

public interface LecturerFacade {

    LecturerDTO findLecturerById(Long id);

    LecturerDTO findLecturerByFirstName(String firstName);

    LecturerDTO findLecturerByLastName(String lastName);

    LecturerDTO findLecturerByLecture(Lecture lecture);
}
