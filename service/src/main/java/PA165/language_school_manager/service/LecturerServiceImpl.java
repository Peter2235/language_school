package PA165.language_school_manager.service;

import PA165.language_school_manager.DTO.LectureDTO;
import PA165.language_school_manager.Dao.LecturerDAO;
import PA165.language_school_manager.Entities.Lecture;
import PA165.language_school_manager.Entities.Lecturer;
import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.LanguageSchoolException;
import java.util.Collections;
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
        try {
            return lecturerDAO.findById(id);
        } catch (Throwable e) {
            throw new LanguageSchoolException("Course create failed");
        }
    }

    @Override
    public List<Lecturer> findLecturerByFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("First name can not be empty");
        }
        try {
            return lecturerDAO.findByFirstName(firstName);
        } catch (Throwable e) {
            throw new LanguageSchoolException("Course create failed");
        }
    }

    @Override
    public List<Lecturer> findLecturerByLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Last name can not be empty");
        }
        try {
            return lecturerDAO.findByLastName(lastName);
        } catch (Throwable e) {
            throw new LanguageSchoolException("Course create failed");
        }
    }

    @Override
    public Lecturer findLecturerByLecture(Lecture lecture) {
        if (lecture == null) {
            throw new IllegalArgumentException("Lecture can not be null");
        }
        try {
            return lecturerDAO.findByLecture(lecture.getId());
        } catch (Throwable e) {
            throw new LanguageSchoolException("Course create failed");
        }
    }

    @Override
    public List<Lecturer> findLecturersByLanguage(Language language) {
        if (language == null) {
            throw new IllegalArgumentException("Language can not be null");
        }
        try {
            return lecturerDAO.findByLanguage(language);
        } catch (Throwable e) {
            throw new LanguageSchoolException("Course create failed");
        }
    }

    @Override
    public void assignNewLecture(Long lecturerId, Lecture lecture) {
        if (lecturerId == null) {
            throw new IllegalArgumentException("Lecturer id can not be null");
        } else if (lecture == null) {
            throw new IllegalArgumentException("Lecture can not be null");
        }

        Lecturer lecturer = lecturerDAO.findById(lecturerId);

        if (lecturer == null) {
            throw new NullPointerException("Lecturer not found");
        }

        lecturer.addLecture(lecture);
        try {
            lecturerDAO.update(lecturer);
        } catch (Throwable e) {
            throw new LanguageSchoolException("Course create failed");
        }
    }

    @Override
    public void addLanguage(Long lecturerId, Language language) {
        if (lecturerId == null) {
            throw new IllegalArgumentException("Lecturer id can not be null");
        } else if (language == null) {
            throw new IllegalArgumentException("Language can not be null");
        }

        Lecturer lecturer = lecturerDAO.findById(lecturerId);

        if (lecturer == null) {
            throw new NullPointerException("Lecturer not found");
        }

        lecturer.getLanguages().add(language);

        try {
            lecturerDAO.update(lecturer);
        } catch (Throwable e) {
            throw new LanguageSchoolException("Course create failed");
        }
    }

    @Override
    public void createLecturer(Lecturer newLecturer) {
        if (newLecturer == null) {
            throw new IllegalArgumentException("Lecturer can not be null");
        }
        try {
            lecturerDAO.create(newLecturer);
        } catch (Throwable e) {
            throw new LanguageSchoolException("Course create failed");
        }
    }

    @Override
    public void deleteLecturer(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id can not be null");
        }
        try {
            Lecturer lecturer = lecturerDAO.findById(id);
            lecturerDAO.delete(lecturer);
        } catch (Throwable e) {
            throw new LanguageSchoolException("Course create failed");
        }
    }

    @Override
    public List<Lecturer> findAllLecturers() {
        try {
            return Collections.unmodifiableList(lecturerDAO.findAll());
        }catch (Throwable e){
            throw new LanguageSchoolException("find all lectures failed " + e);
        }
    }
}
