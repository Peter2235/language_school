/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.Entities;

import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.Enums.ProficiencyLevel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Matúš Sedlák
 */
@Entity
@Table(name = "COURSES")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Column(unique = true)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private Language language;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private ProficiencyLevel proficiencyLevel;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Lecture> lectures = new ArrayList<Lecture>();

    public Course() {
    }

    public Course(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

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

    public List<Lecture> getLectures() {
        return Collections.unmodifiableList(lectures);
    }

    public void addLecture(Lecture lecture) {
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
        Course other = (Course) obj;
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
}
