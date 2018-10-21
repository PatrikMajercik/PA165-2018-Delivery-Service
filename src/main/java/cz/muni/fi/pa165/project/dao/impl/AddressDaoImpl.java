package cz.muni.fi.pa165.project.dao.impl;

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
public class AddressDaoImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(@NotNull Address address) {
        entityManager.persist(address);
    }

    public void update(@NotNull Address address) {
        entityManager.merge(address);
    }

    public void delete(@NotNull Address address) {
        entityManager.remove(findById(address.getId()));
    }

    public Address findById(@NotNull Long id) {
        return entityManager.find(Address.class, id);
    }

    public List<Address> findAll() {
        return entityManager.createQuery("SELECT a FROM Address a", Address.class)
                .getResultList();
    }
}
