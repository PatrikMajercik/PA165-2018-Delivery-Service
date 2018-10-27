package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.Address;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Tomas Terem
 */
public interface AddressDao {

    /**
     * Create new address in database
     *
     * @param address address to create
     * @throws IllegalArgumentException             if address is null
     * @throws ValidationException if address id is not null
     */
    void create(@NotNull Address address);

    /**
     * Updates given address in database
     *
     * @param address address to update
     * @throws IllegalArgumentException             if address is null
     * @throws ValidationException if address id is null
     */
    void update(@NotNull Address address);

    /**
     * Removes given address from database
     *
     * @param address address to delete
     * @throws IllegalArgumentException             if address is null
     * @throws ValidationException if address id is null
     */
    void delete(@NotNull Address address);

    /**
     * Return specific address based on its unique id
     *
     * @param id id to search for
     * @return address with given id
     * @throws IllegalArgumentException             if id is null
     */
    Address findById(@NotNull Long id);

    /**
     * Return all addresses in database
     *
     * @return list of all addresses in database
     */
    List<Address> findAll();
}
