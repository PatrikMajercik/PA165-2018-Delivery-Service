package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.Person;

import javax.validation.ValidationException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Interface for Person DAO
 *
 * @author Tomas Terem
 */
public interface PersonDao {

    /**
     * Create new person in database
     *
     * @param person person to create
     * @throws IllegalArgumentException if person is null
     * @throws ValidationException      if person id is not null
     */
    void create(@NotNull Person person) throws IllegalArgumentException, ValidationException;

    /**
     * Updates given person in database
     *
     * @param person person to update
     * @throws IllegalArgumentException if person is null
     * @throws ValidationException      if person id is null
     */
    void update(@NotNull Person person) throws IllegalArgumentException, ValidationException;

    /**
     * Removes given person from database
     *
     * @param person person to delete
     * @throws IllegalArgumentException if person is null
     * @throws ValidationException      if person id is null
     */
    void delete(@NotNull Person person) throws IllegalArgumentException, ValidationException;

    /**
     * Return specific person based on their unique id
     *
     * @param id id to search for
     * @return person with given id
     * @throws IllegalArgumentException if id is null
     */
    Person findById(@NotNull Long id) throws IllegalArgumentException;

    /**
     * Return all people with given name in database
     *
     * @return list of all people with given name in database
     * @throws IllegalArgumentException if name is null
     * @throws IllegalArgumentException if name is empty
     */
    List<Person> findPersonByName(@NotNull @NotBlank String name) throws IllegalArgumentException;

    /**
     * Return all people with given email in database
     *
     * @return list of all people with given email in database
     * @throws IllegalArgumentException if email is null
     * @throws IllegalArgumentException if email is empty
     */
    List<Person> findPersonByEmail(@NotNull @NotBlank String email) throws IllegalArgumentException;

    /**
     * Return all people in database
     *
     * @return list of all people in database
     */
    List<Person> findAll();
}
