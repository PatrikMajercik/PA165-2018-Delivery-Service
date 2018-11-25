package cz.muni.fi.pa165.project.facade;

import cz.muni.fi.pa165.project.dto.DeliveryDTO;
import java.util.List;

/**
 *Delivery Facade Interface
 * 
 * @author Jakub Gavlas
 */
public interface  DeliveryFacade {    
    /**
     * Creates new Delivery d
     * 
     * @param d 
     */
    public void create(DeliveryDTO d);
    
    /**
     * Updates Delivery d
     * 
     * @param d 
     */
    public void update(DeliveryDTO d);
    
    /**
     * Deletes Delivery d
     * 
     * @param d 
     */
    public void delete(DeliveryDTO d);
    
    /**
     * Finds Delivery with id
     * 
     * @param id
     * @return 
     */
    public DeliveryDTO findById(Long id);
    
    /**
     * Finds all Deliveries
     * 
     * @return 
     */
    public List<DeliveryDTO> findAll();
    
}
