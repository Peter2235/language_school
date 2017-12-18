package PA165.language_school_manager.facade;

import PA165.language_school_manager.DTO.CourseDTO;
import PA165.language_school_manager.DTO.LectureCreateDTO;
import PA165.language_school_manager.DTO.LectureDTO;
import PA165.language_school_manager.DTO.LecturerDTO;
import PA165.language_school_manager.Entities.Course;
import PA165.language_school_manager.Entities.Lecture;
import PA165.language_school_manager.Entities.Lecturer;
import PA165.language_school_manager.Facade.LectureFacade;
import PA165.language_school_manager.service.BeanMappingService;
import PA165.language_school_manager.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
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

    @Override
    public void deleteLecture(LectureDTO lecture) {
        lectureService.deleteLecture(lecture.getId());
    }

    @Override
    public Long createLecture(LectureCreateDTO lecture) {
        Lecture lecture1 = beanMappingService.mapTo(lecture,Lecture.class);
        lectureService.createLecture(lecture1);
        return lecture1.getId();
    }

    @Override
    public void updateLecture(LectureDTO lecture) {
        Lecture lecture1 = beanMappingService.mapTo(lecture, Lecture.class);
        lectureService.updateLecture(lecture1);
    }

    @Override
    public List<LectureDTO> findLectureByCourse(CourseDTO courseDTO) {
        Course course = beanMappingService.mapTo(courseDTO, Course.class);
        return beanMappingService.mapTo(lectureService.findLectureByCourse(course), LectureDTO.class);
    }
    
    @Override
    public List<LectureDTO> findLecturesByLecturer(LecturerDTO lecturer) {
        Lecturer lecturer1 = beanMappingService.mapTo(lecturer, Lecturer.class);
        List<Lecture> lectures = lectureService.findLecturesByLecturer(lecturer1);
        return beanMappingService.mapTo(lectures, LectureDTO.class);
    }
}
