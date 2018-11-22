package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.project.entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface for PersonService
 *
 * @author Tomas Terem
 */
public interface PersonService {

    /**
     * Create new person
     *
     * @param person person
     */
    void create(Person person);

    /**
     * Updates given person
     *
     * @param person person
     */
    void update(Person person);

    /**
     * Delete given person
     *
     * @param person person
     */
    void delete(Person person);

    /**
     * Return specific person based on their unique id
     *
     * @param id id to search for
     * @return person with given id
     */
    Person findById(Long id);

    /**
     * Return all people with given name
     *
     * @return list of all people with given name
     */
    List<Person> findPersonByName(String name);

    /**
     * Return all people with given email
     *
     * @return list of all people with given email
     */
    List<Person> findPersonByEmail(String email);

    /**
     * Return all people
     *
     * @return list of all people
     */
    List<Person> findAll();
}

