package PA165.language_school_manager.service;

import PA165.language_school_manager.Dao.CourseDao;
import PA165.language_school_manager.Dao.LectureDao;
import PA165.language_school_manager.Dao.LecturerDAO;
import PA165.language_school_manager.Dao.PersonDao;
import PA165.language_school_manager.Entities.Course;
import PA165.language_school_manager.Entities.Lecture;
import PA165.language_school_manager.Entities.Lecturer;
import PA165.language_school_manager.Entities.Person;
import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.Enums.ProficiencyLevel;
import org.mockito.Mockito;

import javax.persistence.EntityExistsException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

public class TestUtils {

    public static Course createCourse(ProficiencyLevel proficiencyLevel,
                                      String name, Language language) {
        Course course = new Course();
        course.setProficiencyLevel(proficiencyLevel);
        course.setName(name);
        course.setLanguage(language);
        return course;
    }

    public static Person createPerson(String userName, String firstName,
                                      String middleName, String lastName) {
        // For consistency in test code

        Person person = new Person(userName, firstName, middleName, lastName);
        return person;
    }

    public static Lecturer createLecturer(String userName, String firstName,
                                          String middleName, String lastName,
                                          Language language, boolean nativeSpeaker) {
        // For consistency in test code

        Lecturer lecturer = new Lecturer(userName, firstName, middleName, lastName, language, nativeSpeaker);
        return lecturer;
    }

    public static Lecture createLecture(String topic, Lecturer lecturer, Course course) {
        Lecture lecture = new Lecture();
        lecture.setTopic(topic);
        lecture.setTime(LocalDateTime.now().plusDays(1));
        lecture.setLecturer(lecturer);
        lecture.setCourse(course);
        return lecture;
    }

    public static void mockPersonDao(PersonDao personDao, List<Person> students) {
        when(personDao.create(any(Person.class))).then(invoke -> {
            Person person = invoke.getArgumentAt(0, Person.class);
            Random random = new Random();
            person.setId(((Double) (random.nextDouble() * 20L)).longValue());
            students.add(person);
            return person;
        });

        when(personDao.findAll()).thenReturn(Collections.unmodifiableList(students));

        when(personDao.findById(any(Long.class))).then(invoke -> {
            Long id = invoke.getArgumentAt(0, Long.class);
            Person person = null;
            for (Person student : students) {
                if (student.getId().equals(id)) {
                    person = student;
                    break;
                }
            }
            return person;
        });

        when(personDao.findByUserName(any(String.class))).then(invoke -> {
            String name = invoke.getArgumentAt(0, String.class);
            Person person = null;
            for (Person student : students) {
                if (student.getUserName().equals(name)) {
                    person = student;
                    break;
                }
            }
            return person;
        });

        when(personDao.findByLastName(any(String.class))).then(invoke -> {
            String name = invoke.getArgumentAt(0, String.class);
            List<Person> people = new ArrayList<>();
            for (Person student : students) {
                if (student.getLastName().equals(name)) {
                    people.add(student);
                }
            }
            return people;
        });

        when(personDao.update(any(Person.class))).then(invoke -> {
            Person person = invoke.getArgumentAt(0, Person.class);
            for (Person student : students) {
                if (student.getId().equals(person.getId())) {
                    students.remove(student);
                    break;
                }
            }
            if (person.getId() == null) {
                Random random = new Random();
                person.setId(((Double) (random.nextDouble() * 20L)).longValue());
            }
            students.add(person);
            return person;
        });

        when(personDao.delete(any(Person.class))).then(invoke -> {
            Person person = invoke.getArgumentAt(0, Person.class);
            students.remove(person);
            person.setId(null);
            return person;
        });

    }

    public static void mockLecturerDao(LecturerDAO lecturerDAO, List<Lecturer> lecturers) {

        when(lecturerDAO.findById(any(Long.class))).then(invoke -> {
            Long id = invoke.getArgumentAt(0, Long.class);
            Lecturer lecturer = null;
            for (Lecturer lecturer1 : lecturers) {
                if (lecturer1.getId().equals(id)) {
                    lecturer = lecturer1;
                    break;
                }
            }
            return lecturer;
        });

        doAnswer(invocationOnMock -> {
            Lecturer lecturer = invocationOnMock.getArgumentAt(0, Lecturer.class);
            Random random = new Random();
            lecturer.setId(((Double) (random.nextDouble() * 20L)).longValue());
            lecturers.add(lecturer);
            return null;
        }).when(lecturerDAO).create(any(Lecturer.class));

        doAnswer(invocationOnMock -> {
            Lecturer lecturer = invocationOnMock.getArgumentAt(0, Lecturer.class);
            for (Lecturer lecturer1 : lecturers) {
                if (lecturer1.getId().equals(lecturer.getId())) {
                    lecturers.remove(lecturer1);
                    break;
                }
            }
            if (lecturer.getId() == null) {
                Random random = new Random();
                lecturer.setId(((Double) (random.nextDouble() * 20L)).longValue());
            }
            lecturers.add(lecturer);
            return null;
        }).when(lecturerDAO).update(any(Lecturer.class));

        doAnswer(invocationOnMock -> {
            Lecturer lecturer = invocationOnMock.getArgumentAt(0, Lecturer.class);
            if (lecturers.contains(lecturer)) {
                lecturers.remove(lecturer);
            }
            lecturer.setId(null);
            return null;
        }).when(lecturerDAO).delete(any(Lecturer.class));

        when(lecturerDAO.findAll()).thenReturn(Collections.unmodifiableList(lecturers));

        when(lecturerDAO.findByFirstName(any(String.class))).then(invoke -> {
            String name = invoke.getArgumentAt(0, String.class);
            List<Lecturer> lecturersToReturn = new ArrayList<>();
            for (Lecturer lecturer1 : lecturers) {
                if (lecturer1.getFirstName().equals(name)) {
                    lecturersToReturn.add(lecturer1);
                }
            }
            return lecturersToReturn;
        });

        when(lecturerDAO.findByLastName(any(String.class))).then(invoke -> {
            String name = invoke.getArgumentAt(0, String.class);
            List<Lecturer> lecturersToReturn = new ArrayList<>();
            for (Lecturer lecturer1 : lecturers) {
                if (lecturer1.getLastName().equals(name)) {
                    lecturersToReturn.add(lecturer1);
                }
            }
            return lecturersToReturn;
        });

        when(lecturerDAO.findByLecture(any(Long.class))).then(invoke -> {
            Long id = invoke.getArgumentAt(0, Long.class);
            for (Lecturer lecturer1 : lecturers) {
                for (Lecture lecture : lecturer1.getLectures()) {
                    if (lecture.getId().equals(id)) {
                        return lecturer1;
                    }
                }
            }
            return null;
        });

        when(lecturerDAO.findByLanguage(any(Language.class))).then(invoke -> {
            Language language = invoke.getArgumentAt(0, Language.class);
            List<Lecturer> lecturersToReturn = new ArrayList<>();
            for (Lecturer lecturer : lecturers) {
                if (lecturer.getLanguage().equals(language)) {
                    lecturersToReturn.add(lecturer);
                }
            }
            return lecturersToReturn;
        });

    }

    public static void mockCourseDao(CourseDao courseDao, List<Course> courses) {

        doAnswer(invocationOnMock -> {
            Course course = invocationOnMock.getArgumentAt(0, Course.class);
            Random random = new Random();
            course.setId(((Double) (random.nextDouble() * 20L)).longValue());
            courses.add(course);
            return null;
        }).when(courseDao).create(any(Course.class));

        when(courseDao.findById(any(Long.class))).then(invocationOnMock -> {
            Long id = invocationOnMock.getArgumentAt(0, Long.class);
            for (Course course : courses) {
                if (course.getId().equals(id)) {
                    return course;
                }
            }
            return null;
        });

        when(courseDao.findAll()).then(invocationOnMock -> {
            return Collections.unmodifiableList(courses);
        });

        when(courseDao.findByLanguage(any(Language.class))).then(invocationOnMock -> {
            Language language = invocationOnMock.getArgumentAt(0, Language.class);
            List<Course> coursesToReturn = new ArrayList<>();
            for (Course course : courses) {
                if (course.getLanguage().equals(language)) {
                    coursesToReturn.add(course);
                }
            }
            return coursesToReturn;
        });

        when(courseDao.findByName(any(String.class))).then(invocationOnMock -> {
            String name = invocationOnMock.getArgumentAt(0, String.class);
            for (Course course : courses) {
                if (course.getName().equals(name)) {
                    return course;
                }
            }
            return null;
        });

        doAnswer(invocationOnMock -> {
            Course course = invocationOnMock.getArgumentAt(0, Course.class);
            for (Course oldCourse : courses) {
                if (oldCourse.getId().equals(course.getId())) {
                    oldCourse.setLanguage(course.getLanguage());
                    oldCourse.setName(course.getName());
                    oldCourse.setProficiencyLevel(course.getProficiencyLevel());
                    break;
                }
            }
            if (course.getId() == null) {
                Random random = new Random();
                course.setId(((Double) (random.nextDouble() * 20L)).longValue());
                courses.add(course);
            }
            return null;
        }).when(courseDao).update(any(Course.class));

        doAnswer(invocationOnMock -> {
            Course course = invocationOnMock.getArgumentAt(0, Course.class);
            courses.remove(course);
            return null;
        }).when(courseDao).delete(any(Course.class));

    }

    public static void mockLectureDao(LectureDao lectureDao, List<Lecture> lectures) {

        when(lectureDao.findById(any(Long.class))).then(invocationOnMock -> {
            Long id = invocationOnMock.getArgumentAt(0, Long.class);
            if (id == null) {
                throw new IllegalArgumentException();
            }
            for (Lecture lecture : lectures) {
                if (lecture.getId().equals(id)) {
                    return lecture;
                }
            }
            return null;
        });

        doAnswer(invocationOnMock -> {
            Lecture lecture = invocationOnMock.getArgumentAt(0, Lecture.class);
            if (lecture == null) {
                throw new IllegalArgumentException();
            }
            if (lecture.getId() instanceof Long) {
                throw new EntityExistsException();
            }
            Random random = new Random();
            lecture.setId(((Double) (random.nextDouble() * 20L)).longValue());
            lectures.add(lecture);
            return null;
        }).when(lectureDao).create(any(Lecture.class));

        doAnswer(invocationOnMock -> {
            Lecture lecture = invocationOnMock.getArgumentAt(0, Lecture.class);
            lectures.remove(lecture);
            lecture.setId(null);
            return null;
        }).when(lectureDao).delete(any(Lecture.class));

        doAnswer(invocationOnMock -> {
            Lecture lecture = invocationOnMock.getArgumentAt(0, Lecture.class);
            for (Lecture oldLecture : lectures) {
                if (oldLecture.getId().equals(lecture.getId())) {
                    lectures.remove(oldLecture);
                    break;
                }
            }
            if (lecture.getId() == null) {
                Random random = new Random();
                lecture.setId(((Double) (random.nextDouble() * 20L)).longValue());
            }
            lectures.add(lecture);
            return null;
        }).when(lectureDao).update(any(Lecture.class));

        when(lectureDao.findAll()).then(invocationOnMock -> {
            return Collections.unmodifiableList(lectures);
        });

        when(lectureDao.findByTopic(any(String.class))).then(invocationOnMock -> {
            String topic = invocationOnMock.getArgumentAt(0, String.class);
            for (Lecture lecture : lectures) {
                if (lecture.getTopic().equals(topic)) {
                    return lecture;
                }
            }
            return null;
        });

        when(lectureDao.findByLecturer(any(Lecturer.class))).then((invocation) -> {
            Lecturer lecturer = invocation.getArgumentAt(0, Lecturer.class);
            List<Lecture> lecturesToReturn = new ArrayList<>();
            for (Lecture lecture : lectures) {
                if (lecture.getLecturer().equals(lecturer)) {
                    lecturesToReturn.add(lecture);
                }
            }
            return lecturesToReturn;
        });
        
    }

}
