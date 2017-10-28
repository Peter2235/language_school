package PA165.language_school_manager.Dao;

import PA165.language_school_manager.ApplicationContext;
import PA165.language_school_manager.Entities.Course;
import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.Enums.ProficiencyLevel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = ApplicationContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CourseDaoImplTest {

    @Inject
    private CourseDao courseDao;

    @Test
    public void categoryTest() {
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
}
