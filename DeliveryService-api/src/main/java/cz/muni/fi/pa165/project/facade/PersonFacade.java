package cz.muni.fi.pa165.project.facade;

import cz.muni.fi.pa165.project.dto.PersonAuthenticateDTO;
import cz.muni.fi.pa165.project.dto.PersonEditDTO;
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
     * @param personEditDTO personEditDTO
     */
    void create(PersonEditDTO personEditDTO);

    /**
     * Updates given person
     *
     * @param personDTO personDTO
     */
    void update(PersonDTO personDTO);

    /**
     * Updates given person
     *
     * @param personEditDTO personDTO
     */
    void update(PersonEditDTO personEditDTO);

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
    PersonDTO findPersonByEmail(String email);

    /**
     * Return all people
     *
     * @return list of all people
     */
    List<PersonDTO> findAll();
    
    
    
	/**
	 * Try to authenticate a user. Return true only if the hashed password matches the records.
	 */
	boolean authenticate(PersonAuthenticateDTO u);

	/**
	 * Check if the given user is admin.
	 */
	boolean isAdmin(PersonDTO u);
}
