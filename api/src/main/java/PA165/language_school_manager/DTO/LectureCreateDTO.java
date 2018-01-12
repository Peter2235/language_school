package PA165.language_school_manager.DTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jan Safarik
 */

public class LectureCreateDTO {

    //@NotNull
    private LocalDateTime time;

    @NotNull
    private CourseDTO course;

    @NotNull
    @Size(min = 2, max = 50)
    private String topic;
    
    @NotNull
    @Size(min = 1)
    private String timeString;

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    @NotNull
    private LecturerDTO lecturer;

    private List<PersonDTO> persons = new ArrayList<>();

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

    public List<PersonDTO> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonDTO> person){
        persons.addAll(person);
    }
    
    public void addPerson(PersonDTO person){
        persons.add(person);
    }
}
