package PA165.language_school_manager.Dao;

import PA165.language_school_manager.ApplicationContext;
import PA165.language_school_manager.Entities.Course;
import PA165.language_school_manager.Entities.Lecture;
import PA165.language_school_manager.Entities.Lecturer;
import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.Enums.ProficiencyLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Viktor Slaný
 */

@ContextConfiguration(classes = ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class LectureDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    public EntityManager em;

    @Autowired
    private LectureDao lectureDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private LecturerDAO lecturerDAO;

    private Lecture lecture1;

    @BeforeMethod
    public void setup() {
        lecture1 = new Lecture();
        lecture1.setTopic("jamesBond");
        lecture1.setTime(LocalDateTime.now().plusDays(2));

        Lecturer lecturer = new Lecturer();
        lecturer.setLastName("Bond");
        lecturer.setFirstName("James");
        lecturer.setMiddleName("007");
        lecturer.setNativeSpeaker(true);
        lecturer.addLanguage(Language.ENGLISH);
        lecturer.addLanguage(Language.ITALIAN);
        lecturerDAO.create(lecturer);

        Course course = new Course();
        course.setName("agent007");
        course.setLanguage(Language.ENGLISH);
        course.setProficiencyLevel(ProficiencyLevel.A1);
        course.addLecture(lecture1);
        courseDao.create(course);


        lecture1.setCourse(course);
        lecture1.setLecturer(lecturer);

        lectureDao.create(lecture1);
    }

    @Test
    public void createLecture() {
        assertThat(lectureDao.findById(lecture1.getId())).isNotNull();
        assertThat(lecture1.getTopic()).isEqualTo("jamesBond");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullLecture() {
        lectureDao.create(null);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createLectureWithNullDate() {
        lecture1.setTime(null);
        lectureDao.create(lecture1);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createLectureWithNullCourse() {
        lecture1.setCourse(null);
        lectureDao.create(lecture1);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createLectureWithNullLecturer() {
        lecture1.setLecturer(null);
        lectureDao.create(lecture1);
    }

    @Test
    public void findLectureById() {
        Lecture lecture = lectureDao.findById(lecture1.getId());
        assertThat(lecture.getTopic()).isNotNull().isEqualTo("jamesBond");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findLectureByIdNull() {
        lectureDao.findById(null);
    }

    @Test
    public void findLectureByIdNonExisting() {
        Lecture lecture = lectureDao.findById(1000000L);
        assertThat(lecture).isNull();
    }

    @Test
    public void findAllLectures() {
        List<Lecture> lectures = lectureDao.findAll();
        assertThat(lectures).isNotNull().isNotEmpty().hasSize(1);
    }

    @Test
    public void deleteLecture() {
        assertThat(lectureDao.findById(lecture1.getId())).isNotNull();
        lectureDao.delete(lecture1);
        assertThat(lectureDao.findById(lecture1.getId())).isNull();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNullLecture() {
        lectureDao.delete(null);
    }

    @Test
    public void deleteNotExistingLecture() {
        lectureDao.delete(new Lecture());
    }

    @Test
    public void updateLecture() {
        lecture1.setTopic("secondTopic");
        lectureDao.update(lecture1);
        assertThat(lecture1.getTopic()).isEqualTo("secondTopic");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNullLecture() {
        lectureDao.update(null);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void updateLectureWithNullDate() {
        lecture1.setTime(null);
        lectureDao.update(lecture1);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void updateLectureWithNullCourse() {
        lecture1.setCourse(null);
        lectureDao.update(lecture1);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void updateLectureWithNullLecturer() {
        lecture1.setLecturer(null);
        lectureDao.update(lecture1);
    }

    @Test
    public void findLectureByTopic() {
        Lecture lecture = lectureDao.findByTopic("jamesBond");
        assertThat(lecture).isEqualTo(lecture1);
    }
}
