/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.project.dao;

import cz.muni.fi.pa165.project.entity.Delivery;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Interface for Delivery DAO
 *
 * @author Jakub Gavlas
 */
public interface DeliveryDao {

    /**
     * Creates new delivery
     *
     * @param delivery to create
     * @throws IllegalArgumentException             if delivery is null
     * @throws ValidationException if delivery id is not null
     */
    void create(@NotNull Delivery delivery);

    /**
     * Updates delivery
     *
     * @param delivery to update
     * @return updated delivery
     * @throws IllegalArgumentException             if delivery is null
     * @throws ValidationException if delivery id is null
     */
    Delivery update(@NotNull Delivery delivery);

    /**
     * Deletes delivery
     *
     * @param delivery to delete
     * @throws IllegalArgumentException             if delivery is null
     * @throws ValidationException if delivery id is null
     */
    void delete(@NotNull Delivery delivery);

    /**
     * Finds delivery by Id
     *
     * @param id of the delivery
     * @return delivery with id "id"(if exists)
     * @throws IllegalArgumentException             if id is null
     */
    Delivery findById(@NotNull Long id);

    /**
     * Finds all deliveries
     *
     * @return List of all deliveries
     */
    List<Delivery> findAll();
}
