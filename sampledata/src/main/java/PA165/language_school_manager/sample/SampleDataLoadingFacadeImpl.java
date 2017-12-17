package PA165.language_school_manager.sample;

import PA165.language_school_manager.Entities.Course;
import PA165.language_school_manager.Entities.Lecture;
import PA165.language_school_manager.Entities.Lecturer;
import PA165.language_school_manager.Entities.Person;
import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.Enums.ProficiencyLevel;
import PA165.language_school_manager.service.CourseService;
import PA165.language_school_manager.service.LectureService;
import PA165.language_school_manager.service.LecturerService;
import PA165.language_school_manager.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Viktor Slany
 */

@Service
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

    Course course = new Course();
    Lecturer lecturer;
    Lecture lecture = new Lecture();
    
    @Autowired
    private PersonService personService;

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private LectureService lectureService;

    private Map<String, Person> personMap = new HashMap<>();
    private Map<String, Lecturer> lecturerMap = new HashMap<>();
    private Map<String, Course> courseMap = new HashMap<>();
    private Map<String, Lecture> lectureMap = new HashMap<>();

    @Override
    public void loadData() {
        loadLecturer();
        loadCourse();
        loadPerson();
        loadLecture();
    }

    private void loadPerson() {
        log.info("Creating people.");
        createPerson("Dandy", "tajomna", "komnata","drevo", "admin", true);
        log.info("People have been created!");

    }

    private void loadLecturer() {
        log.info("Creating lecturers.");
        createLecturer("Murica", "inglisgod", true, false);
        log.info("Lecturers have been created!");

    }

    private void loadCourse() {
        log.info("Creating courses.");
        createCourse("TeaAndRain", Language.ENGLISH, ProficiencyLevel.B1);
        log.info("Courses have been created!");

    }

    private void loadLecture() {
        log.info("Creating lectures.");
        createLecture(course, lecturer, "topic", LocalDateTime.now());
        log.info("Lectures have been created!");

    }

    private void createPerson(String userName, String firstName, String middleName, String lastName, String password, boolean isAdmin) {
        Person person = new Person();
        person.setUserName(userName);
        person.setFirstName(firstName);
        person.setMiddleName(middleName);
        person.setLastName(lastName);
        person.setAdmin(isAdmin);
        personService.registerPerson(person, password);
        log.debug("Creating person: \"" + userName.toLowerCase() + "\": " + person);
        personMap.put(userName.toLowerCase(), person);

    }

    private void createLecturer(String userName, String lastName, boolean isAdmin, boolean isNativeSpeaker) {
        lecturer = new Lecturer(userName, "A.", "B.", lastName, isNativeSpeaker);
        lecturer.setAdmin(isAdmin);
        lecturerService.createLecturer(lecturer);
        log.debug("Creating lecturer: \"" + userName.toLowerCase() + "\": " + lecturer);
        lecturerMap.put(userName.toLowerCase(), lecturer);
    }

    private void createCourse(String name, Language language, ProficiencyLevel level) {
        course.setName(name);
        course.setLanguage(language);
        course.setProficiencyLevel(level);
        courseService.createCourse(course);
        log.debug("Creating course: \"" + name.toLowerCase() + "\": " + course);
        courseMap.put(name.toLowerCase(), course);
    }

    private void createLecture(Course course, Lecturer lecturer, String topic, LocalDateTime time) {
        lecture.setTime(time);
        lecture.setCourse(course);
        lecture.setLecturer(lecturer);
        lecture.setTopic(topic);
        
        lectureService.createLecture(lecture);

        log.debug("Creating lecture: \"" + topic.toLowerCase() + "\": " + lecture);
        lectureMap.put(topic.toLowerCase(), lecture);
    }
}
