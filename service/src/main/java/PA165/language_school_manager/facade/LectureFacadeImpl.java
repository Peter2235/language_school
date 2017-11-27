package PA165.language_school_manager.facade;

import PA165.language_school_manager.DTO.LectureDTO;
import PA165.language_school_manager.Entities.Lecture;
import PA165.language_school_manager.Entities.Lecturer;
import PA165.language_school_manager.service.BeanMappingService;
import PA165.language_school_manager.service.LectureService;
import PA165_language_school_manager.Facade.LectureFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class LectureFacadeImpl implements LectureFacade {


    @Autowired
    private LectureService lectureService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    @Transactional
    public LectureDTO findLectureById(Long id) {
        Lecture lecture = lectureService.findLectureById(id);
        return (lecture == null) ? null : beanMappingService.mapTo(lecture, LectureDTO.class);
    }

    @Override
    @Transactional
    public List<LectureDTO> findAllLectures() {
        return beanMappingService.mapTo(lectureService.findAllLectures(), LectureDTO.class);
    }

    @Override
    @Transactional
    public LectureDTO findLectureByTopic(String topic) {
        Lecture lecture = lectureService.findLectureByTopic(topic);
        return (lecture == null) ? null : beanMappingService.mapTo(lecture, LectureDTO.class);
    }

    @Override
    @Transactional
    public void deleteLecture(Long id) {
        lectureService.deleteLecture(id);
    }

    @Override
    @Transactional
    public void createLecture(LectureDTO lecture) {
        Lecture lecture1 = beanMappingService.mapTo(lecture,Lecture.class);
        lectureService.createLecture(lecture1);
    }
}
