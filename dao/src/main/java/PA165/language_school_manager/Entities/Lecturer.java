/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.Entities;

import PA165.language_school_manager.Enums.Language;
import java.util.ArrayList;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EnumType;
import javax.persistence.FetchType;

/**
 * @author Viktor Slaný + Peter Tirala
 */
@Entity
public class Lecturer extends Person {

    @Enumerated(EnumType.STRING)
    private Language language;

    @NotNull
    private boolean nativeSpeaker;

    public Lecturer() {
    }

    public Lecturer(String userName, String firstName, String middleName, String lastName, Language language, boolean isNativeSpeaker) {
        super(userName, firstName, middleName, lastName);
        this.language = language;
        this.nativeSpeaker = isNativeSpeaker;
    }

    public Lecturer(String userName, String firstName, String middleName, String lastName, boolean isNativeSpeaker) {
        super(userName, firstName, middleName, lastName);
        this.nativeSpeaker = isNativeSpeaker;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public boolean getNativeSpeaker() {
        return nativeSpeaker;
    }

    public void setNativeSpeaker(boolean nativeSpeaker) {
        this.nativeSpeaker = nativeSpeaker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lecturer)) return false;
        if (!super.equals(o)) return false;

        Lecturer lecturer = (Lecturer) o;

        if (getNativeSpeaker() != lecturer.getNativeSpeaker()) return false;
        return getLanguage().equals(lecturer.getLanguage());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + super.hashCode();
        result = prime * result + ((language == null) ? 0 : language.hashCode());
        result = prime * result + (getNativeSpeaker() ? 1 : 0);
        return result;
    }
}
