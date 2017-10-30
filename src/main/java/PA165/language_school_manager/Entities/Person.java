/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.Entities;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
/**
 *
 * @author Peter Tirala
 */
@Entity
public class Person{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String firstName;
    private String middleName;
    
    @NotNull
    private String lastName;

    @ManyToMany
    private Set<Lecture> lectures = new HashSet<Lecture>();
    
    public Person() {
    }

    public Person(Long id) {
        this.id = id;
    }

    public Person(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void addLecture(Lecture lecture) {
		this.lectures.add(lecture);
    }

    public Set<Lecture> getLectures() {
        return Collections.unmodifiableSet(lectures);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (!getId().equals(person.getId())) return false;
        if (getFirstName() != null ? !getFirstName().equals(person.getFirstName()) : person.getFirstName() != null)
            return false;
        if (getMiddleName() != null ? !getMiddleName().equals(person.getMiddleName()) : person.getMiddleName() != null)
            return false;
        return getLastName().equals(person.getLastName());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getMiddleName() != null ? getMiddleName().hashCode() : 0);
        result = 31 * result + getLastName().hashCode();
        return result;
    }
}
