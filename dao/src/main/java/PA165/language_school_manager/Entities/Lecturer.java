/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.Entities;

import PA165.language_school_manager.Enums.Language;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EnumType;
import javax.persistence.FetchType;

/**
 * @author Viktor Slaný + Peter Tirala
 */
@Entity
public class Lecturer extends Person {

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Language> languages = new HashSet<>();

    @NotNull
    private boolean isNativeSpeaker;
    
    public Lecturer() {
    }

    public Lecturer(String userName, String firstName, String middleName, String lastName, Set<Language> languages, boolean isNativeSpeaker) {
        super(userName, firstName, middleName, lastName);
        this.languages = languages;
        this.isNativeSpeaker = isNativeSpeaker;
    }

    public Lecturer(String userName, String firstName, String middleName, String lastName, boolean isNativeSpeaker) {
        super(userName, firstName, middleName, lastName);
        this.isNativeSpeaker = isNativeSpeaker;
    }

    public Set<Language> getLanguages() {
        return Collections.unmodifiableSet(languages);
    }

    public void addLanguage(Language language) {
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
        final int prime = 31;
        int result = 1;
        result = prime * result + super.hashCode();
        result = prime * result + ((languages == null) ? 0 : languages.hashCode());
        result = prime * result + (isNativeSpeaker() ? 1 : 0);
        return result;
    }
}
