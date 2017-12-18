package PA165.language_school_manager.DTO;

import PA165.language_school_manager.Enums.Language;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Peter Tirala
 */
@Getter
@Setter
public class LecturerDTO extends PersonDTO{

    private String firstName;
    private String middleName;
    private Language language;
    private boolean nativeSpeaker;

    public boolean getNativeSpeaker() {
        return nativeSpeaker;
    }

    public LecturerDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LecturerDTO)) return false;

        LecturerDTO that = (LecturerDTO) o;

        if (nativeSpeaker != that.nativeSpeaker) return false;
        if (getUserName()!= null ? !getUserName().equals(that.getUserName()) : that.getUserName() != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (middleName != null ? !middleName.equals(that.middleName) : that.middleName != null) return false;
        return getLastName() != null ? getLastName().equals(that.getLastName()) : that.getLastName() == null;
    }

    @Override
    public int hashCode() {
        int result = getUserName() != null ? getUserName().hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (nativeSpeaker ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LecturerDTO{" +
                "id=" + getId() +
                ", userName='" + getUserName() + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + getLastName() + '\'' +
      //          ", lectures=" +  +
                ", language=" + language +
                ", isNativeSpeaker=" + nativeSpeaker +
                '}';
    }
}
