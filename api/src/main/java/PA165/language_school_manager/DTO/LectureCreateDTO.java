package PA165.language_school_manager.DTO;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Jan Safarik
 */

public class LectureCreateDTO {

    @NotNull
    private LocalDateTime time;

    @NotNull
    private CourseDTO course;

    private String topic;

    @NotNull
    private LecturerDTO lecturer;

    private Set<PersonDTO> students = new HashSet<>();

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

    public Set<PersonDTO> getStudents() {
        return students;
    }

    public void setStudents(Set<PersonDTO> students) {
        this.students = students;
    }
}
