package PA165.language_school_manager.service;

import PA165.language_school_manager.Dao.*;
import PA165.language_school_manager.Entities.Course;
import PA165.language_school_manager.Entities.Lecture;
import PA165.language_school_manager.Entities.Lecturer;
import PA165.language_school_manager.Entities.Person;
import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.Enums.ProficiencyLevel;
import PA165.language_school_manager.config.ServiceConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = ServiceConfiguration.class)
@RunWith(MockitoJUnitRunner.class)
public class LectureServiceTest {

    @Mock
    private PersonDao personDao;

    @Mock
    private LecturerDAO lecturerDAO;

    @Mock
    private CourseDao courseDao;

    @Mock
    private LectureDao lectureDao;

    @Autowired
    @InjectMocks
    private LecturerService lecturerService = new LecturerServiceImpl();

    @Autowired
    @InjectMocks
    private CourseService courseService = new CourseServiceImpl();

    @Autowired
    @InjectMocks
    private PersonService personService = new PersonServiceImpl();

    @Autowired
    @InjectMocks
    private LectureService lectureService = new LectureServiceImpl();

    private Lecture lecture;

    private List<Person> students = new ArrayList<>();
    private List<Lecturer> lecturers = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private List<Lecture> lectures = new ArrayList<>();

    @Before
    public void beforeClass(){
        MockitoAnnotations.initMocks(this);

        TestUtils.mockPersonDao(personDao, students);
        TestUtils.mockLecturerDao(lecturerDAO, lecturers);
        TestUtils.mockCourseDao(courseDao, courses);
        TestUtils.mockLectureDao(lectureDao, lectures);

        creationOfLecture();
    }


    public void creationOfLecture(){

        Person student1 = TestUtils.createPerson("Makaveli", "Tupac", "Amaru", "Shakur");
        Person student2 = TestUtils.createPerson("Biggie", "Christopher", "George", "Wallace");
        students.add(student1);
        students.add(student2);
        personService.createPerson(student1);
        personService.createPerson(student2);

        Lecturer lecturer = TestUtils.createLecturer("eazyE", "Eric", "Lynn", "Wright",
                Collections.singleton(Language.ENGLISH), true);
        lecturerService.createLecturer(lecturer);

        Course course = TestUtils.createCourse(ProficiencyLevel.C1, "How to hustle", Language.ENGLISH);
        courseService.createCourse(course);

        lecture = TestUtils.createLecture("Have a vision", lecturer, course);
        lectureService.createLecture(lecture);

        course.addLecture(lecture);
        courseService.updateCourse(course);
        student1.addLecture(lecture);
        student2.addLecture(lecture);
        lecture.addStudent(student1);
        lecture.addStudent(student2);
        personService.updatePerson(student1);
        personService.updatePerson(student2);
        lectureService.updateLecture(lecture);

        lecturerService.assignNewLecture(lecturer.getId(), lecture);

        course.addLecture(lecture);
        courseService.updateCourse(course);

    }

    @Test
    public void findLectureByIdTest(){
        Lecture lectureToFind = lectureService.findLectureById(lecture.getId());
        assertThat(lectureToFind.getTopic()).isEqualTo(lecture.getTopic());
    }

    @Test
    public void findAllLecturesTest(){
        throw new UnsupportedOperationException();
    }

    @Test
    public void findLectureByTopicTest(){
        throw new UnsupportedOperationException();
    }

    @Test
    public void createLectureTest(){
        throw new UnsupportedOperationException();
    }

    @Test
    public void deleteLectureTest(){
        throw new UnsupportedOperationException();
    }

    @Test
    public void updateLectureTest(){
        throw new UnsupportedOperationException();
    }

}
