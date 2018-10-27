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
import javax.validation.constraints.NotNull;
import cz.muni.fi.pa165.project.dao.DeliveryDao;

/**
 *
 * @author Jakub Gavlas
 */
public class DeliveryDaoImpl implements DeliveryDao{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void create(@NotNull Delivery delivery) {
        em.persist(delivery);
    }

    @Override
    public Delivery update(@NotNull Delivery delivery) {
        return em.merge(delivery);
    }

    @Override
    public void delete(@NotNull Delivery delivery) {
        em.remove(delivery);
    }

    @Override
    public Delivery findById(@NotNull long id) {
        return em.find(Delivery.class, id);
    }

    @Override
    public List<Delivery> findAll() {
        return Collections.unmodifiableList(em.createQuery("SELECT d FROM Delivery d", Delivery.class).getResultList());
    }
    
    
}
