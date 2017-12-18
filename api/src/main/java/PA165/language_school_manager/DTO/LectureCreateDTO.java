package PA165.language_school_manager.DTO;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Jan Safarik
 */

public class LectureCreateDTO {

    //@NotNull
    private LocalDateTime time;

    @NotNull
    private CourseDTO course;

    private String topic;

    @NotNull
    private LecturerDTO lecturer;

    private Set<PersonDTO> persons = new HashSet<>();

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public LecturerDTO getLecturer() {
        return lecturer;
    }

    public void setLecturer(LecturerDTO lecturer) {
        this.lecturer = lecturer;
    }

    public Set<PersonDTO> getPersons() {
        return persons;
    }

    public void setPersons(Set<PersonDTO> person){
        persons.addAll(person);
    }
    
    public void addPerson(PersonDTO person){
        persons.add(person);
    }
}
