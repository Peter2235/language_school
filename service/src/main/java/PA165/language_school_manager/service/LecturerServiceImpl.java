package PA165.language_school_manager.service;

import PA165.language_school_manager.Dao.LecturerDAO;
import PA165.language_school_manager.Entities.Lecture;
import PA165.language_school_manager.Entities.Lecturer;
import PA165.language_school_manager.Enums.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Peter Tirala
 */
@Service
public class LecturerServiceImpl implements LecturerService {

    @Autowired
    private LecturerDAO lecturerDAO;

    @Override
    public Lecturer findLecturerById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id can not be null");
        }
        return lecturerDAO.findById(id);
    }

    @Override
    public Lecturer findLecturerByFirstName(String firstName) {
        return null;
    }

    @Override
    public Lecturer findLecturerByLastName(String lastName) {
        return null;
    }

    @Override
    public Lecturer findLecturerByLecture(Lecture lecture) {
        return null;
    }

    @Override
    public List<Lecturer> findLecturersByLanguage(Language language) {
        return null;
    }

    @Override
    public void assignNewLecture(Long lecturerId, Lecture lecture) {

    }

    @Override
    public void addLanguage(Long lecturerId, Language language) {

    }

    @Override
    public Long createLecturer(Lecturer newLecturer) {
        return null;
    }

    @Override
    public void deleteLecturer(Long id) {

    }
}
