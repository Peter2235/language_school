/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.Entities;

import PA165.language_school_manager.Enums.Language;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Viktor Slaný + Peter Tirala
 */
@Entity
public class Lecturer extends Person{

    @ElementCollection
    @Enumerated
    private Set<Language> languages = new HashSet<>();

    @NotNull
    private boolean isNativeSpeaker;

    public Lecturer(String firstName, String middleName, String lastName, Set<Language> languages, boolean isNativeSpeaker) {
        super(firstName, middleName, lastName);
        this.languages = languages;
        this.isNativeSpeaker = isNativeSpeaker;
    }

    public Set<Language> getLanguages() {
        return Collections.unmodifiableSet(languages);
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    public void addLanguage(Language language){
        languages.add(language);
    }

    public boolean isNativeSpeaker() {
        return isNativeSpeaker;
    }

    public void setNativeSpeaker(boolean nativeSpeaker) {
        isNativeSpeaker = nativeSpeaker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lecturer)) return false;
        if (!super.equals(o)) return false;

        Lecturer lecturer = (Lecturer) o;

        if (isNativeSpeaker() != lecturer.isNativeSpeaker()) return false;
        return getLanguages().equals(lecturer.getLanguages());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getLanguages().hashCode();
        result = 31 * result + (isNativeSpeaker() ? 1 : 0);
        return result;
    }
}
