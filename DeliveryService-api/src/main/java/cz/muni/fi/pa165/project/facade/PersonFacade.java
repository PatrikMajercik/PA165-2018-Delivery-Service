package cz.muni.fi.pa165.project.facade;

import cz.muni.fi.pa165.project.dto.PersonCreateDTO;
import cz.muni.fi.pa165.project.dto.PersonDTO;

import java.util.List;

/**
 * Interface for PersonFacade
 *
 * @author Tomas Terem
 */
public interface PersonFacade {

    /**
     * Create new person
     *
     * @param personDTO personDTO
     */
    void create(PersonDTO personDTO);

    /**
     * Create new person
     *
     * @param personCreateDTO personCreateDTO
     */
    void create(PersonCreateDTO personCreateDTO);

    /**
     * Updates given person
     *
     * @param personDTO personDTO
     */
    void update(PersonDTO personDTO);

    /**
     * Delete given person
     *
     * @param personDTO personDTO
     */
    void delete(PersonDTO personDTO);

    /**
     * Return specific person based on their unique id
     *
     * @param id id to search for
     * @return personDTO with given id
     */
    PersonDTO findById(Long id);

    /**
     * Return all people with given name
     *
     * @return list of all people with given name
     */
    List<PersonDTO> findPersonByName(String name);

    /**
     * Return all people with given email
     *
     * @return list of all people with given email
     */
    List<PersonDTO> findPersonByEmail(String email);

    /**
     * Return all people
     *
     * @return list of all people
     */
    List<PersonDTO> findAll();
}
