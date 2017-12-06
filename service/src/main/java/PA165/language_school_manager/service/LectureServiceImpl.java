package PA165.language_school_manager.service;

import PA165.language_school_manager.Entities.Course;
import PA165.language_school_manager.Entities.Lecture;
import PA165.language_school_manager.LanguageSchoolException;
import org.dozer.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import PA165.language_school_manager.Dao.LectureDao;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;
import java.util.Collections;
import java.util.List;

@Service
public class LectureServiceImpl implements LectureService {

    @Inject
    private LectureDao lectureDAO;

    @Override
    public Lecture findLectureById(Long id) {
        if (id == null){
            throw new NullPointerException("ID cant be null");
        }
        try {
            return lectureDAO.findById(id);
        } catch (Throwable e) {
            throw new LanguageSchoolException("find lecture by id failed " + e);
        }

    }

    @Override
    public List<Lecture> findAllLectures() {
        try {
            return Collections.unmodifiableList(lectureDAO.findAll());
        }catch (Throwable e){
            throw new LanguageSchoolException("find all lectures failed " + e);
        }

    }

    @Override
    public Lecture findLectureByTopic(String topic) {
        if (topic == null){
            throw new NullPointerException("topic cant be null");
        }
        try {
            return lectureDAO.findByTopic(topic);
        } catch (Throwable e){
            throw new LanguageSchoolException("find lecture by topic failed " + e);
        }

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
        if (lecture.getId() == null){
            throw new NullPointerException("lectures ID is null - not in DB");
        }
        try {
            lectureDAO.update(lecture);
        } catch (Exception e){
            throw new LanguageSchoolException("Problem with updating lecture");
        }
    }


}
