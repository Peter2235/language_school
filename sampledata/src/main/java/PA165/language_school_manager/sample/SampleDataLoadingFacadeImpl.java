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
        createPerson("agent.orange", "Donald", "John", "Trump", "1234", true);
        createPerson("darth.vader", "Anakin", null, "Skywalker", "empire", true);
        createPerson("ring.bearer", "Frodo", null, "Baggins", "hobbit1", false);
        createPerson("harrys.sidekick", "Ronald", "Bilius", "Weasley", "alohomora", false);
        createPerson("its.captain.jack", "Jack", null, "Sparrow", "black_pearl", false);
        createPerson("the.don", "Vito", null, "Corleone", "family.first", false);
        log.info("People have been created!");

    }

    private void loadLecturer() {
        log.info("Creating lecturers.");
        createLecturer("Neo", "Thomas", "A.", "Anderson", Language.ENGLISH, true, true);
        createLecturer("Indiana", "Henry", "Walton", "Jones", Language.ITALIAN, true, false);
        createLecturer("gladiator", "Maximus", "Decimus", "Meridius", Language.SPANISH, false, false);
        createLecturer("Rocky", "Robert", null, "Balboa", Language.GERMAN, false, false);
        createLecturer("Tony", "Antonio", null, "Montana", Language.SPANISH, false, true);
        createLecturer("Bohuš", "Bohumil", null, "Stejskal", Language.FRENCH, true, false);
        log.info("Lecturers have been created!");

    }

    private void loadCourse() {
        log.info("Creating courses.");
        createCourse("Archeology in Italia", Language.ITALIAN, ProficiencyLevel.C2);
        createCourse("Boxing with germans", Language.GERMAN, ProficiencyLevel.A1);
        createCourse("How to get out of the system", Language.ENGLISH, ProficiencyLevel.B1);
        log.info("Courses have been created!");

    }

    private void loadLecture() {
        log.info("Creating lectures.");
        createLecture(courseMap.get(("Archeology in Italia").toLowerCase()), lecturerMap.get(("Indiana").toLowerCase()), "How to hold a whip", LocalDateTime.now().plusHours(3));
        createLecture(courseMap.get(("Archeology in Italia").toLowerCase()), lecturerMap.get(("Indiana").toLowerCase()), "How to steal old stuff", LocalDateTime.now().plusDays(1));
        createLecture(courseMap.get(("Boxing with germans").toLowerCase()), lecturerMap.get(("Rocky").toLowerCase()), "First lose, then win", LocalDateTime.now().plusHours(5));
        createLecture(courseMap.get(("Boxing with germans").toLowerCase()), lecturerMap.get(("Rocky").toLowerCase()), "Lesson of ice skating", LocalDateTime.now().plusDays(2));
        createLecture(courseMap.get(("How to get out of the system").toLowerCase()), lecturerMap.get(("Neo").toLowerCase()), "How to contact Morpheus", LocalDateTime.now().plusDays(1));
        createLecture(courseMap.get(("How to get out of the system").toLowerCase()), lecturerMap.get(("gladiator").toLowerCase()), "Started from the bottom, now we here", LocalDateTime.now().plusDays(3));
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

    private void createLecturer(String userName, String firstName, String middleName, String lastName, Language language, boolean isAdmin, boolean isNativeSpeaker) {
        Lecturer lecturer = new Lecturer(userName, firstName, middleName, lastName, language, isNativeSpeaker);
        lecturer.setAdmin(isAdmin);
        lecturerService.createLecturer(lecturer);
        log.debug("Creating lecturer: \"" + userName.toLowerCase() + "\": " + lecturer);
        lecturerMap.put(userName.toLowerCase(), lecturer);
    }

    private void createCourse(String name, Language language, ProficiencyLevel level) {
        Course course = new Course();
        course.setName(name);
        course.setLanguage(language);
        course.setProficiencyLevel(level);
        courseService.createCourse(course);
        log.debug("Creating course: \"" + name.toLowerCase() + "\": " + course);
        courseMap.put(name.toLowerCase(), course);
    }

    private void createLecture(Course course, Lecturer lecturer, String topic, LocalDateTime time) {
        Lecture lecture = new Lecture();
        lecture.setTime(time);
        lecture.setCourse(course);
        lecture.setLecturer(lecturer);
        lecture.setTopic(topic);
        
        Person person2 = new Person("feri", "abcd", "efgh", "VECNYSTUDENT");
        personService.createPerson(person2);
        
        lecture.addPerson(person2);
        lectureService.createLecture(lecture);

        log.debug("Creating lecture: \"" + topic.toLowerCase() + "\": " + lecture);
        lectureMap.put(topic.toLowerCase(), lecture);
    }
}
