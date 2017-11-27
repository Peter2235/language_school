package PA165.language_school_manager.service;

import PA165.language_school_manager.Entities.Lecture;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LectureService {

    Lecture findLectureById(Long id);
    List<Lecture> findAllLectures();
    Lecture findLectureByTopic(String topic);
    void deleteLecture(Long id);
    void updateLecture(Lecture lecture);
    void createLecture(Lecture lecture);

}
