/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.Entities;

import PA165.language_school_manager.Enums.Language;
import lombok.Getter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Peter Tirala
 */
@Entity
@Getter
public class Lecturer extends Person {

    private Boolean isNativeSpeaker;

    @ElementCollection
    @Enumerated
    private Set<Language> languages = new HashSet<>();

    public void addLanguage(Language language) {
        languages.add(language);
    }

    public void setNativeSpeaker(Boolean nativeSpeaker) {
        isNativeSpeaker = nativeSpeaker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lecturer)) return false;
        if (!super.equals(o)) return false;

        Lecturer lecturer = (Lecturer) o;

        return isNativeSpeaker != null ? isNativeSpeaker.equals(lecturer.isNativeSpeaker) : lecturer.isNativeSpeaker == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (isNativeSpeaker != null ? isNativeSpeaker.hashCode() : 0);
        return result;
    }
}
