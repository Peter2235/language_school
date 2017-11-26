package PA165.language_school_manager.service;

import PA165.language_school_manager.Entities.Lecture;
import org.springframework.beans.factory.annotation.Autowired;
import PA165.language_school_manager.Dao.LectureDao;

import java.util.List;

public class LectureServiceImpl implements LectureService {

    @Autowired
    private LectureDao lectureDao;

    @Override
    public Lecture findLectureById(Long id) {
        return lectureDao.findById(id);
    }

    @Override
    public List<Lecture> findAllLectures() {
        return lectureDao.findAll();
    }

    @Override
    public Lecture findLectureByTopic(String topic) {
        return lectureDao.findByTopic(topic);
    }
}
