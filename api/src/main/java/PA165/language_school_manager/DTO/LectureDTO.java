package PA165.language_school_manager.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class LectureDTO {

    private Long id;
    private LocalDateTime time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    private CourseDTO course;
    private String topic;
    private LecturerDTO lecturer;
    private Set<PersonDTO> students = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LectureDTO)) return false;

        LectureDTO that = (LectureDTO) o;

        if (!time.equals(that.time)) return false;
        if (!course.equals(that.course)) return false;
        return lecturer.equals(that.lecturer);
    }

    @Override
    public int hashCode() {
        int result = time.hashCode();
        result = 31 * result + course.hashCode();
        result = 31 * result + lecturer.hashCode();
        return result;
    }

    /*@Override
    public String toString() {
        return "LectureDTO{" +
                "id=" + id +
                ", time=" + time +
                ", course=" + course +
                ", topic='" + topic + '\'' +
                ", lecturer=" + lecturer +
                ", students=" + students +
                '}';
    }*/

}
