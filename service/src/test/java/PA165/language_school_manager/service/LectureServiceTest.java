package PA165.language_school_manager.service;

import PA165.language_school_manager.Dao.*;
import PA165.language_school_manager.Entities.Course;
import PA165.language_school_manager.Entities.Lecture;
import PA165.language_school_manager.Entities.Lecturer;
import PA165.language_school_manager.Entities.Person;
import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.Enums.ProficiencyLevel;
import PA165.language_school_manager.LanguageSchoolException;
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
    private LectureDao lectureDao;

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
        TestUtils.mockLectureDao(lectureDao, lectures);
        creationOfLecture();
    }


    public void creationOfLecture(){
        Person student1 = TestUtils.createPerson("Makaveli", "Tupac", "Amaru", "Shakur");
        Person student2 = TestUtils.createPerson("Biggie", "Christopher", "George", "Wallace");
        student1.setId(1L);
        student2.setId(2L);
        students.add(student1);
        students.add(student2);

        Lecturer lecturer = TestUtils.createLecturer("eazyE", "Eric", "Lynn", "Wright",
                Language.ENGLISH, true);
        lecturer.setId(3L);
        lecturers.add(lecturer);


        Course course = TestUtils.createCourse(ProficiencyLevel.C1, "How to hustle", Language.ENGLISH);
        course.setId(4L);
        courses.add(course);

        lecture = TestUtils.createLecture("Have a vision", lecturer, course);
        lectureService.createLecture(lecture);

        course.addLecture(lecture);
        student1.addLecture(lecture);
        student2.addLecture(lecture);
        lecture.addPerson(student1);
        lecture.addPerson(student2);
        lectureService.updateLecture(lecture);
        lecture.setLecturer(lecturer);

        lecturer.addLecture(lecture);

        course.addLecture(lecture);
        lectureService.updateLecture(lecture);

    }

    @Test
    public void findLectureByIdTest(){
        Lecture lectureToFind = lectureService.findLectureById(lecture.getId());
        assertThat(lectureToFind.getTopic()).isEqualTo(lecture.getTopic());
    }

    @Test(expected = LanguageSchoolException.class)
    public void findLectureByIdNullId(){
        lectureService.findLectureById(null);
    }

    @Test
    public void findAllLecturesTest(){
        List<Lecture> newList = lectureService.findAllLectures();
        assertThat(newList).containsExactlyInAnyOrder(lectures.get(0));
    }

    @Test
    public void findLectureByTopicTest(){
        Lecture newLecture = lectureService.findLectureByTopic("Have a vision");
        assertThat(newLecture.getTopic()).isEqualTo("Have a vision");
    }

    @Test
    public void createLectureTest(){
        Lecture newLecture = TestUtils.createLecture("create lecture", lecturers.get(0), courses.get(0));
        lecturers.get(0).addLecture(newLecture);
        courses.get(0).addLecture(newLecture);
        newLecture.addPerson(students.get(0));
        students.get(0).addLecture(newLecture);
        assertThat(newLecture.getId()).isEqualTo(null);
        lectureService.createLecture(newLecture);
        assertThat(newLecture.getId()).isNotEqualTo(null).isInstanceOf(Long.class);
    }

    @Test(expected = LanguageSchoolException.class)
    public void createAlreadyExistingLecture(){
        lectureService.createLecture(lecture);
    }

    @Test(expected = LanguageSchoolException.class)
    public void createLectureNullLecture(){
        lectureService.createLecture(null);
    }

    @Test
    public void deleteLectureTest(){
        assertThat(lectures.get(0)).isNotNull();
        lectureService.deleteLecture(lectures.get(0).getId());
        assertThat(lectures.size()).isEqualTo(0);
    }

    @Test(expected = LanguageSchoolException.class)
    public void deleteLectureNullLectureIdTest(){
        lectureService.deleteLecture(null);
    }

    @Test
    public void updateLectureTest(){
        assertThat(lectures.get(0).getTopic()).isEqualTo("Have a vision");
        lectureService.deleteLecture(lectures.get(0).getId());
        assertThat(lectures.size()).isEqualTo(0);
    }

    @Test(expected = NullPointerException.class)
    public void updateLectureNullIdTest(){
        lectures.get(0).setId(null);
        lectureService.updateLecture(lectures.get(0));
    }

    @Test(expected = NullPointerException.class)
    public void updateLectureNullLecture(){
        lectureService.updateLecture(null);
    }
    
    /*@Test
    public void findLecturesByLecturer(){
        List<Lecture> lecturesToBeFound = lectureService.findLecturesByLecturer(lecturers.get(0));
        assertThat(lecturesToBeFound.size()).isEqualTo(1);
        assertThat(lecturesToBeFound.get(0)).isEqualTo(lectures.get(0));
    }*/

}
