package PA165.language_school_manager.config;

import PA165.language_school_manager.DTO.CourseDTO;
import PA165.language_school_manager.DTO.LectureDTO;
import PA165.language_school_manager.DTO.LecturerDTO;
import PA165.language_school_manager.DTO.PersonDTO;
import PA165.language_school_manager.Entities.Course;
import PA165.language_school_manager.Entities.Lecture;
import PA165.language_school_manager.Entities.Lecturer;
import PA165.language_school_manager.Entities.Person;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;

/**
 * @author Viktor Slany
 */
public class EntityMapping extends BeanMappingBuilder {
    @Override
    protected void configure() {
        mapping(Person.class, PersonDTO.class, TypeMappingOptions.mapNull(false))
                .fields(field("lectures").accessible(true), field("lectures").accessible(true));
        mapping(Lecturer.class, LecturerDTO.class, TypeMappingOptions.mapNull(false))
                .fields(field("lectures").accessible(true), field("lectures").accessible(true))
                .fields(field("languages").accessible(true), field("languages").accessible(true));
        mapping(Course.class, CourseDTO.class, TypeMappingOptions.mapNull(false))
                .fields(field("lectures").accessible(true), field("lectures").accessible(true));
        mapping(Lecture.class, LectureDTO.class, TypeMappingOptions.mapNull(false))
                .fields(field("lecturers").accessible(true), field("lecturers").accessible(true))
                .fields(field("students").accessible(true), field("students").accessible(true));
        //TODO add other mapping options in future
    }
}
