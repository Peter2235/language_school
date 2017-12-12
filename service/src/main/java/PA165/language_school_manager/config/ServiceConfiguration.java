package PA165.language_school_manager.config;

import PA165.language_school_manager.ApplicationContext;
import org.dozer.DozerBeanMapper;
//import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Viktor Slany
 */

@Configuration
@Import(ApplicationContext.class)
@ComponentScan(basePackages = "PA165.language_school_manager")
public class ServiceConfiguration {

    @Bean
    public Mapper dozer() {

        List<String> mappingFiles = new ArrayList();
        mappingFiles.add("dozerJdk8Converters.xml");

        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.setMappingFiles(mappingFiles);
        return dozerBeanMapper;

        //return DozerBeanMapperBuilder.create()
        //        .withMappingFiles("mapping.xml", "dozerJdk8Converters.xml")
        //        .build();
    }
}