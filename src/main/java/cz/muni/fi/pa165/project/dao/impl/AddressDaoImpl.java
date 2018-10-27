package cz.muni.fi.pa165.project.dao.impl;

import cz.muni.fi.pa165.project.dao.AddressDao;
import cz.muni.fi.pa165.project.entity.Address;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public void create(@NotNull Address address) {
        entityManager.persist(address);
    }

    @Override
    public void update(@NotNull Address address) {
        entityManager.merge(address);
    }

    @Override
    public void delete(@NotNull Address address) {
        entityManager.remove(findById(address.getId()));
    }

    @Override
    public Address findById(@NotNull Long id) {
        return entityManager.find(Address.class, id);
    }

    @Override
    public List<Address> findAll() {
        return entityManager.createQuery("SELECT a FROM Address a", Address.class)
                .getResultList();
    }
}
