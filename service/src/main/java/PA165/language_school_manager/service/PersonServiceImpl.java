/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.service;

import PA165.language_school_manager.Dao.PersonDao;
import PA165.language_school_manager.Entities.Person;
import PA165.language_school_manager.LanguageSchoolException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Matúš
 */

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Override
    public List<Person> findAll() {
        return personDao.findAll();
    }

    @Override
    public Person findPersonById(Long personId) {
        if (personId == null) {
            throw new LanguageSchoolException("Wrong arguments.");
        }
        return personDao.findById(personId);
    }

    @Override
    public Person findPersonByUserName(String userName) {
        if (userName == null) {
            throw new LanguageSchoolException("Wrong arguments.");
        }
        return personDao.findByUserName(userName);
    }

    @Override
    public List<Person> findPersonsByLastName(String lastName) {
        if (lastName == null) {
            throw new LanguageSchoolException("Wrong arguments.");
        }
        return personDao.findByLastName(lastName);
    }

    @Override
    public void createPerson(Person person) {
        if (person == null || person.getLastName() == null || person.getUserName() == null) {
            throw new LanguageSchoolException("Wrong arguments.");
        }
        try {
            personDao.create(person);
        } catch (Throwable e) {
            throw new LanguageSchoolException("Person create failed");
        }
    }

    @Override
    public void updatePerson(Person person) {
        if (person == null || person.getLastName() == null || person.getUserName() == null) {
            throw new LanguageSchoolException("Wrong arguments.");
        }

        try {
            personDao.update(person);
        } catch (Throwable e) {
            throw new LanguageSchoolException("Person update failed");
        }
    }

    @Override
    public void deletePerson(Person person) {
        try {
            personDao.delete(person);
        } catch (Throwable e) {
            throw new LanguageSchoolException("Person delete failed");
        }
    }
}
