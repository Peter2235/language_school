package PA165.language_school_manager.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Peter Tirala
 */
@Getter
@Setter
public class LecturerCreateDTO {

    @Size(min = 3, max = 50)
    private String userName;

    @NotNull
    private String firstName;
    private String middleName;

    @NotNull
    private String lastName;
    private boolean isNativeSpeaker;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LecturerCreateDTO)) return false;

        LecturerCreateDTO that = (LecturerCreateDTO) o;

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
        return "LecturerCreateDTO{" +
                "userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isNativeSpeaker=" + isNativeSpeaker +
                '}';
    }
}
