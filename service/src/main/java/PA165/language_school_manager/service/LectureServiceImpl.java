package PA165.language_school_manager.service;

import PA165.language_school_manager.Entities.Lecture;
import org.springframework.beans.factory.annotation.Autowired;
import PA165.language_school_manager.Dao.LectureDao;

import java.util.List;

public class LectureServiceImpl implements LectureService {

    @Autowired
    private LectureDao lectureDAO;

    @Override
    public Lecture findLectureById(Long id) {
        return lectureDAO.findById(id);
    }

    @Override
    public List<Lecture> findAllLectures() {
        return lectureDAO.findAll();
    }

    @Override
    public Lecture findLectureByTopic(String topic) {
        return lectureDAO.findByTopic(topic);
    }
}
