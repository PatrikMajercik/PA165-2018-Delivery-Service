package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.Person;

import javax.validation.constraints.NotNull;
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
    void create(@NotNull Person person);

    /**
     * Updates given person in database
     *
     * @param person person to update
     */
    void update(@NotNull Person person);

    /**
     * Removes given person from database
     *
     * @param person person to delete
     */
    void delete(@NotNull Person person);

    /**
     * Return specific person based on their unique id
     *
     * @param id id to search for
     * @return person with given id
     */
    Person findById(@NotNull Long id);

    /**
     * Return all people with given name in database
     *
     * @return list of all people with given name in database
     */
    List<Person> findPersonByName(@NotNull String name);

    /**
     * Return all people with given email in database
     *
     * @return list of all people with given email in database
     */
    List<Person> findPersonByEmail(@NotNull String email);

    /**
     * Return all people in database
     *
     * @return list of all people in database
     */
    List<Person> findAll();
}
