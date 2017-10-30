/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Matúš Sedlák
 */
@Entity
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime time;

    @ManyToOne
    @NotNull
    private Course course;

    private String topic;

    @ManyToOne
    @NotNull
    private Lecturer lecturer;

    @ManyToMany(mappedBy = "lectures")
    private Set<Person> students = new HashSet<Person>();

    public void addStudent(Person student) {
        this.students.add(student);
    }

    public Set<Person> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    public Lecture() {
    }

    public Lecture(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        result = prime * result + ((course == null) ? 0 : course.hashCode());
        result = prime * result + ((topic == null) ? 0 : topic.hashCode());
        result = prime * result + ((lecturer == null) ? 0 : lecturer.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lecture)) return false;

        Lecture lecture = (Lecture) o;

        if (time != null ? !time.equals(lecture.time) : lecture.time != null) return false;
        if (course != null ? !course.equals(lecture.course) : lecture.course != null) return false;
        if (topic != null ? !topic.equals(lecture.topic) : lecture.topic != null) return false;
        return lecturer != null ? lecturer.equals(lecture.lecturer) : lecture.lecturer == null;
    }
}
