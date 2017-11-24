package PA165.language_school_manager.DTO;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.Enums.ProficiencyLevel;

/**
 * @author Viktor Slany
 */
@Getter
@Setter
public class CourseDTO {

    private Long id;
    private String name;
    private Language language;
    private ProficiencyLevel proficiencyLevel;
    private List<LectureDTO> lectures = new ArrayList<>();

    public CourseDTO() {
    }

    public void addLecture(LectureDTO lecture) {
        lectures.add(lecture);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((language == null) ? 0 : language.hashCode());
        result = prime * result + ((proficiencyLevel == null) ? 0 : proficiencyLevel.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CourseDTO other = (CourseDTO) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (language != other.language)
            return false;
        if (proficiencyLevel == null) {
            if (other.proficiencyLevel != null)
                return false;
        } else if (!proficiencyLevel.equals(other.proficiencyLevel))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", language=" + language +
                ", proficiencyLevel=" + proficiencyLevel +
                ", lectures=" + lectures +
                '}';
    }
}
