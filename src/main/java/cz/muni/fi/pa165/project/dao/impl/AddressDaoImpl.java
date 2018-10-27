package cz.muni.fi.pa165.project.dao.impl;

import cz.muni.fi.pa165.project.dao.AddressDao;
import cz.muni.fi.pa165.project.entity.Address;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Tomas Terem
 */
@Repository
public class AddressDaoImpl implements AddressDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(@NotNull Address address) throws IllegalArgumentException, ValidationException {
        if (address == null) {
            throw new IllegalArgumentException("address is null");
        }
        if (address.getId() != null) {
            throw new ValidationException("address id cannot be set before creation");
        }
        entityManager.persist(address);
    }

    @Override
    public void update(@NotNull Address address) throws IllegalArgumentException, ValidationException {
        if (address == null) {
            throw new IllegalArgumentException("address is null");
        }
        if (address.getId() == null) {
            throw new ValidationException("address id is null");
        }

        entityManager.merge(address);
    }

    @Override
    public void delete(@NotNull Address address) throws IllegalArgumentException, ValidationException {
        if (address == null) {
            throw new IllegalArgumentException("address is null");
        }
        if (address.getId() == null) {
            throw new ValidationException("address id is null");
        }
        entityManager.remove(findById(address.getId()));
    }

    @Override
    public Address findById(@NotNull Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new ValidationException("id is null");
        }
        return entityManager.find(Address.class, id);
    }

    @Override
    public List<Address> findAll() {
        return entityManager.createQuery("SELECT a FROM Address a", Address.class)
                .getResultList();
    }
}
