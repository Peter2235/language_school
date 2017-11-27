package PA165.language_school_manager.config;

import PA165.language_school_manager.ApplicationContext;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Collections;

/**
 * @author Viktor Slany
 */

@Configuration
@Import(ApplicationContext.class)
@ComponentScan(basePackages = "PA165.language_school_manager")
public class ServiceConfiguration {

    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.setMappingFiles(Collections.singletonList("dozerJdk8Converters.xml"));
        dozer.addMapping(new EntityMapping());
        return dozer;
    }
}