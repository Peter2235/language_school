package PA165.language_school_manager.DTO;

import PA165.language_school_manager.Enums.Language;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Peter Tirala
 */
@Getter
@Setter
public class LecturerDTO extends PersonDTO{

    private Long id;
    private String userName;
    private String firstName;
    private String middleName;
    private String lastName;
    private Set<Language> languages = new HashSet<>();
    private boolean isNativeSpeaker;

    public LecturerDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LecturerDTO)) return false;

        LecturerDTO that = (LecturerDTO) o;

        if (isNativeSpeaker != that.isNativeSpeaker) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (middleName != null ? !middleName.equals(that.middleName) : that.middleName != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (isNativeSpeaker ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LecturerDTO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
      //          ", lectures=" +  +
                ", languages=" + languages +
                ", isNativeSpeaker=" + isNativeSpeaker +
                '}';
    }
}
