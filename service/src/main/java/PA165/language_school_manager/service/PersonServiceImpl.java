/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.service;

import PA165.language_school_manager.Dao.PersonDao;
import PA165.language_school_manager.Entities.Person;
import PA165.language_school_manager.LanguageSchoolException;
import java.math.BigInteger;
import java.security.SecureRandom;

import java.util.List;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

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
            throw new LanguageSchoolException("Wrong person ID.");
        }
        return personDao.findById(personId);
    }

    @Override
    public Person findPersonByUserName(String userName) {
        if (userName == null) {
            throw new LanguageSchoolException("Wrong person username .");
        }
        return personDao.findByUserName(userName);
    }

    @Override
    public List<Person> findPersonsByLastName(String lastName) {
        if (lastName == null) {
            throw new LanguageSchoolException("Wrong person last name.");
        }
        return personDao.findByLastName(lastName);
    }

    @Override
    public void createPerson(Person person) {
        if (person == null || person.getLastName() == null || person.getUserName() == null) {
            throw new LanguageSchoolException("Can't create this person");
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
            throw new LanguageSchoolException("Can't update this person");
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

    @Override
    public boolean isAdmin(Person person) {
        return findPersonById(person.getId()).isAdmin();
    }

    @Override
    public boolean authenticate(Person person, String password) {
        Person found = findPersonById(person.getId());
        return validatePassword(password, found.getPasswordHash());
    }

    public static boolean validatePassword(String password, String correctHash) {
        if(password==null) return false;
        if(correctHash==null) throw new IllegalArgumentException("password hash is null");
        String[] params = correctHash.split(":");
        int iterations = Integer.parseInt(params[0]);
        byte[] salt = fromHex(params[1]);
        byte[] hash = fromHex(params[2]);
        byte[] testHash = pbkdf2(password.toCharArray(), salt, iterations, hash.length);
        return slowEquals(hash, testHash);
    }
    
    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];
        return diff == 0;
    }
    
    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        return paddingLength > 0 ? String.format("%0" + paddingLength + "d", 0) + hex : hex;
    }

    @Override
    public void registerPerson(Person person, String unencryptedPassword) {
        person.setPasswordHash(createHash(unencryptedPassword));
        personDao.create(person);
    }

    //see  https://crackstation.net/hashing-security.htm#javasourcecode
    private static String createHash(String password) {
        final int SALT_BYTE_SIZE = 24;
        final int HASH_BYTE_SIZE = 24;
        final int PBKDF2_ITERATIONS = 1000;
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        // Hash the password
        byte[] hash = pbkdf2(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        // format iterations:salt:hash
        return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
    }
}
