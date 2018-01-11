package PA165.language_school_manager;

import PA165.language_school_manager.Dao.CourseDao;
import PA165.language_school_manager.Entities.Course;
import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.Enums.ProficiencyLevel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for CourseDao
 *
 * @author Peter Tirala
 */
@ContextConfiguration(classes = ApplicationContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CourseDaoTest {

    @Autowired
    private CourseDao courseDao;

    @Test
    public void create() {
        Course course = new Course();
        course.setName("TestCourse");
        course.setLanguage(Language.ENGLISH);
        course.setProficiencyLevel(ProficiencyLevel.A1);

        courseDao.create(course);

        List<Course> all = courseDao.findAll();
        assertThat(all).isNotNull();
        assertThat(all).hasSize(1);
        assertThat(all).contains(course);
    }

    @Test(expected = PersistenceException.class)
    public void createWithSameId() {
        Course course = new Course();
        course.setId(1L);
        course.setName("TestCourse");
        course.setLanguage(Language.ENGLISH);
        course.setProficiencyLevel(ProficiencyLevel.A1);

        courseDao.create(course);

        Course course2 = new Course();
        course2.setName("TestCourse2");
        course2.setId(1L);
        course2.setLanguage(Language.ENGLISH);
        course2.setProficiencyLevel(ProficiencyLevel.A1);

        courseDao.create(course);
        courseDao.create(course2);
    }

    @Test(expected = PersistenceException.class)
    public void createWithSameName() {
        Course course = new Course();
        course.setName("TestCourse");
        course.setLanguage(Language.ENGLISH);
        course.setProficiencyLevel(ProficiencyLevel.A1);

        courseDao.create(course);

        Course course2 = new Course();
        course2.setName("TestCourse");
        course2.setLanguage(Language.ENGLISH);
        course2.setProficiencyLevel(ProficiencyLevel.A1);

        courseDao.create(course2);
    }

    @Test(expected = ConstraintViolationException.class)
    public void createWithNullLanguage() {
        Course course = new Course();
        course.setName("TestCourse");
        course.setProficiencyLevel(ProficiencyLevel.A1);

        courseDao.create(course);
    }

    @Test(expected = ConstraintViolationException.class)
    public void createWithNullProficiencyLevel() {
        Course course = new Course();
        course.setName("TestCourse");
        course.setProficiencyLevel(ProficiencyLevel.A1);

        courseDao.create(course);
    }

    @Test
    public void findById() {
        Course course = new Course();
        course.setName("TestCourse");
        course.setLanguage(Language.ENGLISH);
        course.setProficiencyLevel(ProficiencyLevel.A1);

        courseDao.create(course);

        Course course2 = courseDao.findById(course.getId());
        assertThat(course2).isNotNull();
        assertThat(course2.getName()).isEqualTo("TestCourse");
        assertThat(course2.getLanguage()).isEqualTo(Language.ENGLISH);
        assertThat(course2.getProficiencyLevel()).isEqualTo(ProficiencyLevel.A1);
    }

    @Test
    public void findByIdNonExisting() {
        Course course = courseDao.findById(1l);
        assertThat(course).isNull();
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByIdNullTest() {
        courseDao.findById(null);
    }

    @Test
    public void findAll() {
        Course course1 = new Course();
        course1.setName("TestName");
        course1.setLanguage(Language.ENGLISH);
        course1.setProficiencyLevel(ProficiencyLevel.A1);
        courseDao.create(course1);

        Course course2 = new Course();
        course2.setName("TestName2");
        course2.setLanguage(Language.ENGLISH);
        course2.setProficiencyLevel(ProficiencyLevel.A1);
        courseDao.create(course2);

        List<Course> allCourses = courseDao.findAll();

        assertThat(allCourses).isNotNull();
        assertThat(allCourses).isNotEmpty();
        assertThat(allCourses.size()).isEqualTo(2);
        assertThat(allCourses).contains(course1, course2);
    }

    @Test
    public void findAllEmpty() {
        List<Course> allCourses = courseDao.findAll();

        assertThat(allCourses).isNotNull();
        assertThat(allCourses).isEmpty();
    }

    @Test
    public void findByLanguage() {
        Course course1 = new Course();
        course1.setName("TestName");
        course1.setLanguage(Language.ENGLISH);
        course1.setProficiencyLevel(ProficiencyLevel.A1);
        courseDao.create(course1);

        Course course2 = new Course();
        course2.setName("TestName2");
        course2.setLanguage(Language.ENGLISH);
        course2.setProficiencyLevel(ProficiencyLevel.A1);
        courseDao.create(course2);

        List<Course> allCourses = courseDao.findByLanguage(Language.ENGLISH);

        assertThat(allCourses).isNotNull();
        assertThat(allCourses).isNotEmpty();
        assertThat(allCourses.size()).isEqualTo(2);
        assertThat(allCourses).contains(course1, course2);
    }

    @Test
    public void findByLanguage2() {
        Course course1 = new Course();
        course1.setName("TestName");
        course1.setLanguage(Language.ENGLISH);
        course1.setProficiencyLevel(ProficiencyLevel.A1);
        courseDao.create(course1);

        Course course2 = new Course();
        course2.setName("TestName2");
        course2.setLanguage(Language.FRENCH);
        course2.setProficiencyLevel(ProficiencyLevel.A1);
        courseDao.create(course2);

        List<Course> allCourses = courseDao.findByLanguage(Language.ENGLISH);

        assertThat(allCourses).isNotNull();
        assertThat(allCourses).isNotEmpty();
        assertThat(allCourses.size()).isEqualTo(1);
        assertThat(allCourses).contains(course1);
    }

    @Test
    public void findByLanguageEmpty() {
        Course course = new Course();
        course.setName("TestName");
        course.setLanguage(Language.FRENCH);
        course.setProficiencyLevel(ProficiencyLevel.A1);
        courseDao.create(course);

        List<Course> allCourses = courseDao.findByLanguage(Language.ENGLISH);

        assertThat(allCourses).isNotNull();
        assertThat(allCourses).isEmpty();
    }

    @Test
    public void findByLanguageNull() {
        List<Course> byLanguage = courseDao.findByLanguage(null);
        assertThat(byLanguage).isEmpty();
    }

    @Test
    public void findByName() {
        Course course1 = new Course();
        course1.setName("English course");
        course1.setLanguage(Language.ENGLISH);
        course1.setProficiencyLevel(ProficiencyLevel.A1);
        courseDao.create(course1);

        Course course2 = courseDao.findByName("English course");

        assertThat(course2).isNotNull();
        assertThat(course2.getName()).isEqualTo("English course");
        assertThat(course2.getLanguage()).isEqualTo(Language.ENGLISH);
        assertThat(course2.getProficiencyLevel()).isEqualTo(ProficiencyLevel.A1);
    }


    @Test
    public void findByNameEmpty() {
        Course course = new Course();
        course.setName("TestName2");
        course.setLanguage(Language.FRENCH);
        course.setProficiencyLevel(ProficiencyLevel.A1);
        courseDao.create(course);

        Course course2 = courseDao.findByName("TestName");

        assertThat(course2).isNull();
    }

    @Test
    public void findByNameNull() {
        Course byName = courseDao.findByName(null);
        assertThat(byName).isNull();
    }

    @Test
    public void deleteCourse() {
        Course course = new Course();
        course.setName("TestName");
        course.setLanguage(Language.FRENCH);
        course.setProficiencyLevel(ProficiencyLevel.A1);
        courseDao.create(course);

        Course course2 = courseDao.findById(course.getId());
        assertThat(course2).isNotNull();

        courseDao.delete(course);

        course2 = courseDao.findById(course.getId());
        assertThat(course2).isNull();
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteCourseNull() {
        courseDao.delete(null);
    }

    @Test
    public void update() {
        Course course = new Course();
        course.setName("TestName");
        course.setLanguage(Language.FRENCH);
        course.setProficiencyLevel(ProficiencyLevel.A1);
        courseDao.create(course);

        Course coures2 = courseDao.findById(course.getId());
        assertThat(coures2.getName()).isEqualTo("TestName");

        course.setName("TestCourse2");
        courseDao.update(course);

        coures2 = courseDao.findById(course.getId());
        assertThat(coures2.getName()).isEqualTo("TestCourse2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateNull() {
        courseDao.update(null);
    }
}
