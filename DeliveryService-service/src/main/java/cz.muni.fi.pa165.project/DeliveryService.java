package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.project.entity.Delivery;
import cz.muni.fi.pa165.project.entity.Person;
import java.util.List;

/**
 *
 * @author Jakub Gavlas
 */
public interface DeliveryService {
    /**
     * Create Delivery d
     * 
     * @param d 
     */
    public void create(Delivery d);
    
    /**
     * Update Deivery d
     * 
     * @param d 
     */
    public void update(Delivery d);
    
    /**
     * Delete Delivery d
     * 
     * @param d 
     */
    public void delete(Delivery d);
    
    /**
     * Find Delivery by id
     * 
     * @param id
     * @return 
     */
    public Delivery findById(Long id);
    
    /**
     * Find all Deliveries
     * 
     * @return 
     */
    public List<Delivery> findAll();
    
    /**
     * Reorder Courier's Deliveries alphabeticaly by Cities
     * 
     * @param courier
     * @return 
     */
    public List<Delivery> orderCouriersDeliveries(Person courier);
}
