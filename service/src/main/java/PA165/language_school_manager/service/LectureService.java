package PA165.language_school_manager.service;

import PA165.language_school_manager.Entities.Lecture;

import java.util.List;

public interface LectureService {

    Lecture findLectureById(Long id);
    List<Lecture> findAllLectures();
    Lecture findLectureByTopic(String topic);

}
