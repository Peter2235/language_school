/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.DTO;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Matúš
 */
public class PersonDTO {

    private Long id;
    private String userName;
    private String firstName;
    private String middleName;
    private String lastName;
    private boolean isAdmin;
    private Set<LectureDTO> lectures = new HashSet<LectureDTO>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Set<LectureDTO> getLectures() {
        return Collections.unmodifiableSet(lectures);
    }

    public void addLecture(LectureDTO lectureDto) {
        lectures.add(lectureDto);
    }

    public void dropLecture(LectureDTO lectureDto) {
        if (lectures.contains(lectureDto)) {
            lectures.remove(lectureDto);
        }
    }

    public PersonDTO() {

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PersonDTO other = (PersonDTO) obj;
        if (userName == null) {
            if (other.userName != null) {
                return false;
            }
        } else if (!userName.equals(other.userName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Username: " + userName + " LastName: " + lastName;
        /*return "PersonDTO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isAdmin=" + isAdmin +
                ", lectures=" + lectures +
                '}';*/
    }
}
