package PA165.language_school_manager.config;

import PA165.language_school_manager.ApplicationContext;
import PA165.language_school_manager.facade.CourseFacadeImpl;
import PA165.language_school_manager.facade.LectureFacadeImpl;
import PA165.language_school_manager.facade.LecturerFacadeImpl;
import PA165.language_school_manager.facade.PersonFacadeImpl;
import PA165.language_school_manager.service.CourseServiceImpl;
import PA165.language_school_manager.service.LectureServiceImpl;
import PA165.language_school_manager.service.LecturerServiceImpl;
import PA165.language_school_manager.service.PersonServiceImpl;
import org.dozer.DozerBeanMapper;
//import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Viktor Slany
 */
@Configuration
@Import(ApplicationContext.class)
@ComponentScan(basePackageClasses = {CourseFacadeImpl.class, LecturerFacadeImpl.class, LectureFacadeImpl.class, PersonFacadeImpl.class,
        CourseServiceImpl.class, LecturerServiceImpl.class, LectureServiceImpl.class, PersonServiceImpl.class})
public class ServiceConfiguration {

    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        List<String> mappingFiles = new ArrayList();
        mappingFiles.add("dozerJdk8Converters.xml");
        dozer.setMappingFiles(mappingFiles);
        return dozer;
    }
}
