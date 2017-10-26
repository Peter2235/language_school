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
import java.util.Set;

/**
 *
 * @author Viktor Slaný
 */
@Entity
public class Lecturer extends Person{

    @ManyToOne
    @NotNull
    private Set<Language> languages;

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
}
