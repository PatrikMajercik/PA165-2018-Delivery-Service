/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.project.dao.impl;

import cz.muni.fi.pa165.project.entity.Delivery;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;

import cz.muni.fi.pa165.project.dao.DeliveryDao;
import org.springframework.stereotype.Repository;

/**
 * @author Jakub Gavlas
 */
@Repository
public class DeliveryDaoImpl implements DeliveryDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(@NotNull Delivery delivery) throws IllegalArgumentException, ValidationException {
        if (delivery == null) {
            throw new IllegalArgumentException("delivery is null");
        }
        if (delivery.getId() != null) {
            throw new ValidationException("delivery id cannot be set before creation");
        }
        em.persist(delivery);
    }

    @Override
    public Delivery update(@NotNull Delivery delivery) throws IllegalArgumentException, ValidationException {
        if (delivery == null) {
            throw new IllegalArgumentException("delivery is null");
        }
        if (delivery.getId() != null) {
            throw new ValidationException("delivery id is null");
        }
        return em.merge(delivery);
    }

    @Override
    public void delete(@NotNull Delivery delivery) throws IllegalArgumentException, ValidationException {
        if (delivery == null) {
            throw new IllegalArgumentException("delivery is null");
        }
        if (delivery.getId() != null) {
            throw new ValidationException("delivery id is null");
        }
        em.remove(delivery);
    }

    @Override
    public Delivery findById(@NotNull Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        return em.find(Delivery.class, id);
    }

    @Override
    public List<Delivery> findAll() {
        return Collections.unmodifiableList(em.createQuery("SELECT d FROM Delivery d", Delivery.class).getResultList());
    }


}
