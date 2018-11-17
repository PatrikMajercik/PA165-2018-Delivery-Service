package cz.muni.fi.pa165.project.facade;

import cz.muni.fi.pa165.project.dto.AddressDTO;

import java.util.List;

/**
 * Interface for AddressFacade
 *
 * @author Tomas Terem
 */
public interface AddressFacade {

    /**
     * Create new addressDTO in database
     *
     * @param addressDTO addressDTO to create
     */
    void create(AddressDTO addressDTO);

    /**
     * Updates given addressDTO in database
     *
     * @param addressDTO addressDTO to update
     */
    void update(AddressDTO addressDTO);

    /**
     * Removes given addressDTO from database
     *
     * @param addressDTO addressDTO to delete
     */
    void delete(AddressDTO addressDTO);

    /**
     * Return specific addressDTO based on its unique id
     *
     * @param id id to search for
     */
    AddressDTO findById(Long id);

    /**
     * Return all addressDTOes in database
     *
     * @return list of all addressDTOes in database
     */
    List<AddressDTO> findAll();
}
