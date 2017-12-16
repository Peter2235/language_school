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
        loadLecture();
        loadLecturer();
        loadCourse();
        loadPerson();

    }

    private void loadPerson() {
        log.info("Creating people.");
        createPerson("Dandy", "drevo", false);
        createPerson("Wolf223", "smutny", false);
        createPerson("carcoolka", "cervena", false);
        createPerson("bestAdmin", "admin", true);
        log.info("People have been created!");

    }

    private void loadLecturer() {
        log.info("Creating lecturers.");
        createLecturer("Murica", "inglisgod", false, true);
        createLecturer("NeinNein", "neinman", false, false);
        createLecturer("PizzaPasta", "italiano", false, true);
        createLecturer("Halusky", "Bryndzove", true, true);
        log.info("Lecturers have been created!");

    }

    private void loadCourse() {
        log.info("Creating courses.");
        createCourse("TeaAndRain", Language.ENGLISH, ProficiencyLevel.B1);
        createCourse("Macarony", Language.ITALIAN, ProficiencyLevel.A1);
        createCourse("Engineering", Language.GERMAN, ProficiencyLevel.C2);
        log.info("Courses have been created!");

    }

    private void loadLecture() {
        log.info("Creating lectures.");
        createLecture(LocalDateTime.now().minusHours(2), "NumberOne", Language.ENGLISH, ProficiencyLevel.A1, "UcitelFero", "Tukabel", false, true);
        createLecture(LocalDateTime.now().minusHours(2), "NumerZwei", Language.GERMAN, ProficiencyLevel.B1, "Zidan", "Tamkabel", false, true);
        createLecture(LocalDateTime.now().minusHours(2), "BagetaTri", Language.FRENCH, ProficiencyLevel.C2, "Sufurky", "Remix", false, true);
        log.info("Lectures have been created!");

    }

    private void createPerson(String userName, String lastName, boolean isAdmin) {
        Person person = new Person();
        person.setUserName(userName);
        person.setLastName(lastName);
        person.setAdmin(isAdmin);
        personService.createPerson(person);
        log.debug("Creating person: \"" + userName.toLowerCase() + "\": " + person);
        personMap.put(userName.toLowerCase(), person);

    }

    private void createLecturer(String userName, String lastName, boolean isAdmin, boolean isNativeSpeaker) {
        Lecturer lecturer = new Lecturer();
        lecturer.setUserName(userName);
        lecturer.setLastName(lastName);
        lecturer.setAdmin(isAdmin);
        lecturer.setNativeSpeaker(isNativeSpeaker);
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

    private void createLecture(LocalDateTime time, String name, Language language, ProficiencyLevel level,
                               String userName, String lastName, boolean isAdmin, boolean isNativeSpeaker) {
        Course course = new Course();
        course.setName(name);
        course.setLanguage(language);
        course.setProficiencyLevel(level);
        courseService.createCourse(course);
        
        Lecture lecture = new Lecture();
        lecture.setTime(time);
        lecture.setCourse(course);
        lectureService.createLecture(lecture);


        Lecturer lecturer = new Lecturer();
        lecturer.setUserName(userName);
        lecturer.setLastName(lastName);
        lecturer.setAdmin(isAdmin);
        lecturer.setNativeSpeaker(isNativeSpeaker);
        lecturerService.createLecturer(lecturer);
        
        lecture.setLecturer(lecturer);
        

        log.debug("Creating lecture: \"" + name.toLowerCase() + "\": " + lecture);
        lectureMap.put(name.toLowerCase(), lecture);
    }
}
