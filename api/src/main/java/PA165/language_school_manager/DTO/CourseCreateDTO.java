package PA165.language_school_manager.DTO;

import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.Enums.ProficiencyLevel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Viktor Slany
 */

public class CourseCreateDTO {

    @NotNull
    private String name;

    @NotNull
    private Language language;

    @NotNull
    private ProficiencyLevel proficiencyLevel;

    private List<LectureDTO> lectures = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public ProficiencyLevel getProficiencyLevel() {
        return proficiencyLevel;
    }

    public void setProficiencyLevel(ProficiencyLevel proficiencyLevel) {
        this.proficiencyLevel = proficiencyLevel;
    }

    public List<LectureDTO> getLectures() {
        return lectures;
    }

    public void setLectures(List<LectureDTO> lectures) {
        this.lectures = lectures;
    }

    public CourseCreateDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseCreateDTO)) return false;

        CourseCreateDTO that = (CourseCreateDTO) o;

        if (!name.equals(that.name)) return false;
        if (language != that.language) return false;
        if (proficiencyLevel != that.proficiencyLevel) return false;
        return lectures != null ? lectures.equals(that.lectures) : that.lectures == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + language.hashCode();
        result = 31 * result + proficiencyLevel.hashCode();
        result = 31 * result + (lectures != null ? lectures.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CourseCreateDTO{" +
                "name='" + name + '\'' +
                ", language=" + language +
                ", proficiencyLevel=" + proficiencyLevel +
                ", lectures=" + lectures +
                '}';
    }
}
