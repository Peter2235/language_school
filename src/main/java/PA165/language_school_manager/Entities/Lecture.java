/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.Entities;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
/**
 *
 * @author Matúš Sedlák
 */
@Entity
public class Lecture {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    @ManyToOne
    @NotNull
    private Course course;
    
    private String topic;
    
    @ManyToOne
    @NotNull
    private Lecturer lecturer;
    
    @ManyToMany(mappedBy = "lecture")
    private Set<Person> students = new HashSet<Person>();  

    public void addStudent(Person student) {
		this.students.add(student);
    }

    public Set<Person> getStudents() {
        return Collections.unmodifiableSet(students);
    }
    
    public Lecture(){   
    }
    
    public Lecture(long id){
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }
    
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
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
    
}
