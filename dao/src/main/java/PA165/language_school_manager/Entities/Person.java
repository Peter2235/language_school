/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Peter Tirala
 */
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(unique = true)
    private String userName;
    
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

    public Person(String userName, String firstName, String middleName, String lastName) {
        this.userName = userName;
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

    public String getUserName(){
        return userName;
    }
    
    public void setUserName(String userName){
        this.userName = userName;
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
         if (getUserName()!= null ? !getUserName().equals(person.getUserName()) : person.getUserName() != null)
            return false;
        return getLastName().equals(person.getLastName());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (userName == null ? 0 : userName.hashCode());
        result = prime * result + (firstName == null ? 0 : firstName.hashCode());
        result = prime * result + (middleName == null ? 0 : middleName.hashCode());
        result = prime * result + (lastName == null ? 0 : lastName.hashCode());
        return result;
    }
}
