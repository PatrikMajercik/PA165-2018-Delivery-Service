/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.Delivery;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Interface for Delivery DAO
 * @author Jakub Gavlas
 */
public interface DeliveryDao {
    
    /**
     * Creates new delivery
     * @param delivery to create
     */
    void create(@NotNull Delivery delivery);
    
    /**
     * Updates delivery
     * @param delivery to update
     * @return updated delivery
     */
    Delivery update(@NotNull Delivery delivery);
    
    /**
     * Deletes delivery
     * @param delivery to delete
     */
    void delete(@NotNull Delivery delivery);
    
    /**
     * Finds delivery by Id
     * @param id of the delivery
     * @return delivery with id "id"(if exists)
     */
    Delivery findById(@NotNull long id);
    
    /**
     * Finds all deliveries
     * @return List of all deliveries
     */
    List<Delivery> findAll();
}
