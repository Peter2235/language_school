package PA165.language_school_manager.DTO;

import PA165.language_school_manager.Enums.Language;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Peter Tirala
 */
@Getter
@Setter
public class LecturerCreateDTO extends PersonCreateDTO{


    private String middleName;

    private Language language;
    
    private boolean nativeSpeaker;

    public void setNativeSpeaker(boolean nativeSpeaker){
        this.nativeSpeaker = nativeSpeaker;
    }
    
    public boolean getNativeSpeaker() {
        return nativeSpeaker;
    }

    public LecturerCreateDTO() {
    }

    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LecturerCreateDTO)) return false;

        LecturerCreateDTO that = (LecturerCreateDTO) o;

        if (nativeSpeaker != that.nativeSpeaker) return false;
        if (getUserName() != null ? !getUserName().equals(that.getUserName()) : that.getUserName() != null) return false;
        if (getFirstName() != null ? !getFirstName().equals(that.getFirstName()) : that.getFirstName() != null) return false;
        if (middleName != null ? !middleName.equals(that.middleName) : that.middleName != null) return false;
        return getLastName() != null ? getLastName().equals(that.getLastName()) : that.getLastName() == null;
    }

    @Override
    public int hashCode() {
        int result = getUserName() != null ? getUserName().hashCode() : 0;
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (nativeSpeaker ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LecturerCreateDTO{" +
                "userName='" + getUserName() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", isNativeSpeaker=" + nativeSpeaker +
                '}';
    }
}
