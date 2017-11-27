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

/**
 *
 * @author Matúš
 */
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Override
    public List<Person> findAll() {
        return personDao.findAll();
    }

    @Override
    public Person findPersonById(Long personId) {
        return personDao.findById(personId);
    }

    @Override
    public Person findPersonByUserName(String userName) {
        return personDao.findByUserName(userName);
    }

    @Override
    public List<Person> findPersonsByLastName(String lastName) {
        return personDao.findByLastName(lastName);
    }

    @Override
    public void createPerson(Person person) {
        try {
            personDao.create(person);
        } catch (Throwable e) {
            throw new LanguageSchoolException("Person create failed");
        }
    }

    @Override
    public void updatePerson(Person person) {
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
