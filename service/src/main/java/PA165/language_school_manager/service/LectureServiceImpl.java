package PA165.language_school_manager.service;

import PA165.language_school_manager.Entities.Course;
import PA165.language_school_manager.Entities.Lecture;
import PA165.language_school_manager.LanguageSchoolException;
import org.springframework.beans.factory.annotation.Autowired;
import PA165.language_school_manager.Dao.LectureDao;

import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;
import java.util.List;

public class LectureServiceImpl implements LectureService {

    @Autowired
    private LectureDao lectureDAO;

    @Override
    public Lecture findLectureById(Long id) {
        if (id == null){
            throw new NullPointerException("ID cant be null");
        }
        return lectureDAO.findById(id);
    }

    @Override
    public List<Lecture> findAllLectures() {
        return lectureDAO.findAll();
    }

    @Override
    public Lecture findLectureByTopic(String topic) {
        if (topic == null){
            throw new NullPointerException("topic cant be null");
        }
        return lectureDAO.findByTopic(topic);
    }

    @Override
    public void createLecture(Lecture lecture) {
        if (lecture == null){
            throw new NullPointerException("lecture cant be null");
        }
        try {
            lectureDAO.create(lecture);
        } catch (Exception e){
            throw new LanguageSchoolException("Problem with creating lecture " + lecture.toString());
        }
    }

    @Override
    public void deleteLecture(Long id) {
        if (id == null){
            throw new NullPointerException("ID cant be null");
        }
        try {
            Lecture lecture = lectureDAO.findById(id);
            lectureDAO.delete(lecture);
        } catch (Exception e){
            throw new LanguageSchoolException("Problem with deleting lecture");
        }
    }

    @Override
    public void updateLecture(Lecture lecture) {
        if (lecture == null){
            throw new NullPointerException("lecture cant be null");
        }
        try {
            lectureDAO.update(lecture);
        } catch (Exception e){
            throw new LanguageSchoolException("Problem with updating lecture");
        }
    }


}
