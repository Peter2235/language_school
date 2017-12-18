package PA165.language_school_manager;

import PA165.language_school_manager.Dao.CourseDao;
import PA165.language_school_manager.Dao.LectureDao;
import PA165.language_school_manager.Dao.LecturerDAO;
import PA165.language_school_manager.Entities.Course;
import PA165.language_school_manager.Entities.Lecture;
import PA165.language_school_manager.Entities.Lecturer;
import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.Enums.ProficiencyLevel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Viktor Slaný
 */

@ContextConfiguration(classes = ApplicationContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class LectureDaoTest {

    @Autowired
    private LectureDao lectureDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private LecturerDAO lecturerDAO;

    private Lecture lecture1;

    @Before
    public void setup() {
        lecture1 = new Lecture();
        lecture1.setTopic("jamesBond");
        lecture1.setTime(LocalDateTime.now().plusDays(2));

        Lecturer lecturer = new Lecturer();
        lecturer.setUserName("lecturer123");
        lecturer.setLastName("Bond");
        lecturer.setFirstName("James");
        lecturer.setMiddleName("007");
        lecturer.setNativeSpeaker(true);
        lecturer.setLanguage(Language.ENGLISH);
        lecturerDAO.create(lecturer);

        Course course = new Course();
        course.setName("agent007");
        course.setLanguage(Language.ENGLISH);
        course.setProficiencyLevel(ProficiencyLevel.A1);
        course.addLecture(lecture1);
        courseDao.create(course);


        lecture1.setCourse(course);
        lecture1.setLecturer(lecturer);

    }

    @Test
    public void createLecture() {
        lectureDao.create(lecture1);
        assertThat(lectureDao.findById(lecture1.getId())).isNotNull();
        assertThat(lecture1.getTopic()).isEqualTo("jamesBond");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createNullLecture() {
        lectureDao.create(null);
    }


    @Test(expected = PersistenceException.class)
    public void createLectureWithNullDate() {
        Lecture lecture2 = new Lecture();
        lecture2.setTopic("jamesBond");
        Lecturer lecturer = new Lecturer();
        lecturer.setUserName("lecturer");
        lecturer.setLastName("Bond");
        lecturer.setFirstName("James");
        lecturer.setMiddleName("007");
        lecturer.setNativeSpeaker(true);
        lecturer.setLanguage(Language.ITALIAN);
        lecturerDAO.create(lecturer);
        Course course = new Course();
        course.setName("agent007");
        course.setLanguage(Language.ENGLISH);
        course.setProficiencyLevel(ProficiencyLevel.A1);
        course.addLecture(lecture2);
        courseDao.create(course);
        lecture2.setCourse(course);
        lecture2.setLecturer(lecturer);
        lectureDao.create(lecture2);

    }

    @Test(expected = ConstraintViolationException.class)
    public void createLectureWithNullCourse() {
        Lecture lecture3 = new Lecture();
        lecture3.setTopic("jamesBond");
        Lecturer lecturer = new Lecturer();
        lecturer.setLastName("Bond");
        lecturer.setFirstName("James");
        lecturer.setMiddleName("007");
        lecturer.setNativeSpeaker(true);
        lecturer.setLanguage(Language.ENGLISH);
        lecturerDAO.create(lecturer);
        lecture3.setLecturer(lecturer);
        lectureDao.create(lecture3);
    }

    @Test(expected = PersistenceException.class)
    public void createLectureWithNullLecturer() {
        Lecture lecture2 = new Lecture();
        lecture2.setTopic("jamesBond");
        lecture1.setTime(LocalDateTime.now().plusDays(2));
        Course course = new Course();
        course.setName("agent007");
        course.setLanguage(Language.ENGLISH);
        course.setProficiencyLevel(ProficiencyLevel.A1);
        course.addLecture(lecture2);
        courseDao.create(course);
        lecture2.setCourse(course);
        lectureDao.create(lecture2);
    }

    @Test
    public void findLectureById() {
        lectureDao.create(lecture1);
        Lecture lecture = lectureDao.findById(lecture1.getId());
        assertThat(lecture.getTopic()).isNotNull().isEqualTo("jamesBond");
    }

    @Test(expected = IllegalArgumentException.class)
    public void findLectureByIdNull() {
        lectureDao.findById(null);
    }

    @Test
    public void findLectureByIdNonExisting() {
        lectureDao.create(lecture1);
        Lecture lecture = lectureDao.findById(1000000L);
        assertThat(lecture).isNull();
    }

    @Test
    public void findAllLectures() {
        lectureDao.create(lecture1);
        List<Lecture> lectures = lectureDao.findAll();
        assertThat(lectures).isNotNull().isNotEmpty().hasSize(1);
    }

    @Test
    public void deleteLecture() {
        lectureDao.create(lecture1);
        assertThat(lectureDao.findById(lecture1.getId())).isNotNull();
        lectureDao.delete(lecture1);
        assertThat(lectureDao.findById(lecture1.getId())).isNull();
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteNullLecture() {
        lectureDao.delete(null);
    }

    @Test(expected = ConstraintViolationException.class)
    public void deleteNotExistingLecture() {
        lectureDao.delete(new Lecture());
    }

    @Test
    public void updateLecture() {
        lecture1.setTopic("secondTopic");
        lectureDao.update(lecture1);
        assertThat(lecture1.getTopic()).isEqualTo("secondTopic");
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateNullLecture() {
        lectureDao.update(null);
    }

    @Test(expected = PersistenceException.class)
    public void updateLectureWithNullDate() {
        Lecture lecture2 = new Lecture();
        lecture2.setTopic("jamesBond");
        Lecturer lecturer = new Lecturer();
        lecturer.setUserName("lecturer");
        lecturer.setLastName("Bond");
        lecturer.setFirstName("James");
        lecturer.setMiddleName("007");
        lecturer.setNativeSpeaker(true);
        lecturer.setLanguage(Language.ITALIAN);
        lecturerDAO.create(lecturer);
        Course course = new Course();
        course.setName("agent007");
        course.setLanguage(Language.ENGLISH);
        course.setProficiencyLevel(ProficiencyLevel.A1);
        course.addLecture(lecture2);
        courseDao.create(course);
        lecture2.setCourse(course);
        lecture2.setLecturer(lecturer);
        lectureDao.update(lecture2);
    }

    @Test(expected = ConstraintViolationException.class)
    public void updateLectureWithNullCourse() {
        Lecture lecture3 = new Lecture();
        lecture3.setTopic("jamesBond");
        Lecturer lecturer = new Lecturer();
        lecturer.setLastName("Bond");
        lecturer.setFirstName("James");
        lecturer.setMiddleName("007");
        lecturer.setNativeSpeaker(true);
        lecturer.setLanguage(Language.ENGLISH);
        lecturerDAO.create(lecturer);
        lecture3.setLecturer(lecturer);
        lectureDao.update(lecture3);
    }

    @Test(expected = PersistenceException.class)
    public void updateLectureWithNullLecturer() {
        Lecture lecture2 = new Lecture();
        lecture2.setTopic("jamesBond");
        lecture1.setTime(LocalDateTime.now().plusDays(2));
        Course course = new Course();
        course.setName("agent007");
        course.setLanguage(Language.ENGLISH);
        course.setProficiencyLevel(ProficiencyLevel.A1);
        course.addLecture(lecture2);
        courseDao.create(course);
        lecture2.setCourse(course);
        lectureDao.update(lecture2);
    }

    @Test
    public void findLectureByTopic() {
        lectureDao.create(lecture1);
        Lecture lecture = lectureDao.findByTopic("jamesBond");
        assertThat(lecture).isEqualTo(lecture1);
    }
    
    @Test
    public void findLectureByLecturer(){
        lectureDao.create(lecture1);
        List<Lecture> lectures = lectureDao.findByLecturer(lecture1.getLecturer());
        assertThat(lectures.get(0)).isEqualTo(lecture1);
    }
}
