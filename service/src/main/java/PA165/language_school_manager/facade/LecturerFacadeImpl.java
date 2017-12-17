package PA165.language_school_manager.facade;

import PA165.language_school_manager.service.BeanMappingService;
import PA165.language_school_manager.DTO.LectureDTO;
import PA165.language_school_manager.DTO.LecturerCreateDTO;
import PA165.language_school_manager.DTO.LecturerDTO;
import PA165.language_school_manager.Entities.Lecture;
import PA165.language_school_manager.Entities.Lecturer;
import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.service.LecturerService;
import PA165.language_school_manager.Facade.LecturerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Peter Tirala
 */

@Service
public class LecturerFacadeImpl implements LecturerFacade {

    private final static Logger log = LoggerFactory.getLogger(LecturerFacadeImpl.class);
    
    @Autowired
    private BeanMappingService beanMappingService;

    @Autowired
    private LecturerService lecturerService;

    @Override
    @Transactional
    public LecturerDTO findLecturerById(Long id) {
        Lecturer lecturer = lecturerService.findLecturerById(id);
        return lecturer == null ? null : beanMappingService.mapTo(lecturer, LecturerDTO.class);
    }

    @Override
    @Transactional
    public List<LecturerDTO> findLecturerByFirstName(String firstName) {
        List<Lecturer> lecturers = lecturerService.findLecturerByFirstName(firstName);
        return beanMappingService.mapTo(lecturers, LecturerDTO.class);
    }

    @Override
    @Transactional
    public List<LecturerDTO> findLecturerByLastName(String lastName) {
        List<Lecturer> lecturers = lecturerService.findLecturerByLastName(lastName);
        return beanMappingService.mapTo(lecturers, LecturerDTO.class);
    }

    @Override
    @Transactional
    public LecturerDTO findLecturerByLecture(LectureDTO lectureDto) {
        Lecture lecture = (lectureDto == null) ? null : beanMappingService.mapTo(lectureDto, Lecture.class);
        Lecturer lecturer = lecturerService.findLecturerByLecture(lecture);
        return lecturer == null ? null : beanMappingService.mapTo(lecturer, LecturerDTO.class);
    }

    @Override
    @Transactional
    public List<LecturerDTO> findLecturersByLanguage(Language language) {
        List<Lecturer> lecturers = lecturerService.findLecturersByLanguage(language);
        return beanMappingService.mapTo(lecturers, LecturerDTO.class);
    }

    @Override
    @Transactional
    public void assignNewLecture(Long lecturerId, LectureDTO lectureDto) {
        Lecture lecture = (lectureDto == null) ? null : beanMappingService.mapTo(lectureDto, Lecture.class);
        lecturerService.assignNewLecture(lecturerId, lecture);
    }

//    @Override
//    @Transactional
//    public void addLanguage(Long lecturerId, Language language) {
//        lecturerService.addLanguage(lecturerId, language);
//    }

    @Override
    @Transactional
    public Long createLecturer(LecturerCreateDTO newLecturer) {
        Lecturer lecturer = beanMappingService.mapTo(newLecturer,Lecturer.class);
        lecturerService.createLecturer(lecturer);
        return lecturer.getId();
        /*
        Lecturer lecturer = new Lecturer();
        lecturer.setFirstName(newLecturer.getFirstName());
        lecturer.setMiddleName(newLecturer.getMiddleName());
        lecturer.setLastName(newLecturer.getLastName());
        lecturer.setUserName(newLecturer.getUserName());
        lecturer.setNativeSpeaker(newLecturer.isNativeSpeaker());
        lecturerService.createLecturer(lecturer);
        */
    }

    @Override
    @Transactional
    public void deleteLecturer(Long id) {
        lecturerService.deleteLecturer(id);
    }

    @Override
    @Transactional
    public List<LecturerDTO> findAllLecturers() {
        List<Lecturer> lecturers = lecturerService.findAllLecturers();
        return beanMappingService.mapTo(lecturers, LecturerDTO.class);
    }

    @Override
    @Transactional
    public void updateLecturer(LecturerDTO lecturer) {
        Lecturer lecturer1 = lecturerService.findLecturerById(lecturer.getId());
        lecturer1 = beanMappingService.mapTo(lecturer, Lecturer.class);
        lecturerService.updateLecturer(lecturer1);
    }
}
