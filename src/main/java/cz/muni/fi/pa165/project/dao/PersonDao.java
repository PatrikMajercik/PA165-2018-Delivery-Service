package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.Person;

import java.util.List;

/**
 * @author Tomas Terem
 */
public interface PersonDao {

    /**
     * Create new person in database
     *
     * @param person person to create
     */
    void create(Person person);

    /**
     * Updates given person in database
     *
     * @param person person to update
     */
    void update(Person person);

    /**
     * Removes given person from database
     *
     * @param person person to delete
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
     * Return all people with given name in database
     *
     * @return list of all people with given name in database
     */
    List<Person> findPersonByName(String name);

    /**
     * Return all people with given email in database
     *
     * @return list of all people with given email in database
     */
    List<Person> findPersonByEmail(String email);

    /**
     * Return all people in database
     *
     * @return list of all people in database
     */
    List<Person> findAll();
}
