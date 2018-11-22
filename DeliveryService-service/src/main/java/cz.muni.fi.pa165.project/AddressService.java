package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.project.entity.Address;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Interface for AddressService
 *
 * @author Tomas Terem
 */
public interface AddressService {

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
     */
    Address findById(Long id);

    /**
     * Return all addresses in database
     *
     * @return list of all addresses in database
     */
    List<Address> findAll();

    /**
     * Return a single string - city with most addresses in database
     * If there are two or more cities with same number of addresses then choose one random to return
     *
     * @return city with most addresses in database
     */
    String findCityWithMostAddresses();
}
