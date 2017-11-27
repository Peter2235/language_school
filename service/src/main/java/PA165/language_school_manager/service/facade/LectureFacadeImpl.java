package PA165.language_school_manager.service.facade;

import PA165.language_school_manager.DTO.LectureDTO;
import PA165.language_school_manager.Entities.Lecture;
import PA165.language_school_manager.service.BeanMappingService;
import PA165.language_school_manager.service.LectureService;
import PA165_language_school_manager.Facade.LectureFacade;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LectureFacadeImpl implements LectureFacade {


    @Autowired
    private LectureService lectureService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public LectureDTO findLectureById(Long id) {
        Lecture lecture = lectureService.findLectureById(id);
        return (lecture == null) ? null : beanMappingService.mapTo(lecture, LectureDTO.class);
    }

    @Override
    public List<LectureDTO> findAllLectures() {
        return beanMappingService.mapTo(lectureService.findAllLectures(), LectureDTO.class);
    }

    @Override
    public LectureDTO findLectureByTopic(String topic) {
        Lecture lecture = lectureService.findLectureByTopic(topic);
        return (lecture == null) ? null : beanMappingService.mapTo(lecture, LectureDTO.class);
    }
}
