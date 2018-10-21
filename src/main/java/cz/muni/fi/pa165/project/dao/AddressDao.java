package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.Address;

import java.util.List;

/**
 * @author Tomas Terem
 */
public interface AddressDao {

    /**
     * Create new address in database
     *
     * @param address address to create
     */
    void create(Address address);

    /**
     * Updates given address in database
     *
     * @param address address to update
     */
    void update(Address address);

    /**
     * Removes given address from database
     *
     * @param address address to delete
     */
    void delete(Address address);

    /**
     * Return specific address based on its unique id
     *
     * @param id id to search for
     * @return address with given id
     */
    Address findById(Long id);

    /**
     * Return all addresses in database
     *
     * @return list of all addresses in database
     */
    List<Address> findAll();
}
