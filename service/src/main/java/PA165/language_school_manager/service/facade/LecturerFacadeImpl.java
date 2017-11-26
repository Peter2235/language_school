package PA165.language_school_manager.service.facade;

import PA165.language_school_manager.DTO.LecturerCreateDTO;
import PA165.language_school_manager.DTO.LecturerDTO;
import PA165.language_school_manager.Entities.Lecture;
import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.service.BeanMappingService;
import PA165_language_school_manager.Facade.LecturerFacade;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Peter Tirala
 */
public class LecturerFacadeImpl implements LecturerFacade {


    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public LecturerDTO findLecturerById(Long id) {
        return null;
    }

    @Override
    public LecturerDTO findLecturerByFirstName(String firstName) {
        return null;
    }

    @Override
    public LecturerDTO findLecturerByLastName(String lastName) {
        return null;
    }

    @Override
    public LecturerDTO findLecturerByLecture(Lecture lecture) {
        return null;
    }

    @Override
    public List<LecturerDTO> findLecturersByLanguage(Language language) {
        return null;
    }

    @Override
    public void assignNewLecture(Long lecturerId, Lecture lecture) {

    }

    @Override
    public void addLanguage(Long lecturerId, Language language) {

    }

    @Override
    public Long createLecturer(LecturerCreateDTO newLecturer) {
        return null;
    }

    @Override
    public void deleteLecturer(Long id) {

    }
}
