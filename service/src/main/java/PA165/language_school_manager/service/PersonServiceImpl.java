/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.service;

import PA165.language_school_manager.Dao.PersonDao;
import PA165.language_school_manager.Entities.Person;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Matúš
 */
public class PersonServiceImpl implements PersonService{

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
}
