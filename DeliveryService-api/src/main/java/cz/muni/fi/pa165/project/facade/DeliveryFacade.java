package cz.muni.fi.pa165.project.facade;

import cz.muni.fi.pa165.project.dto.DeliveryCreateDTO;
import cz.muni.fi.pa165.project.dto.DeliveryDTO;
import cz.muni.fi.pa165.project.dto.PersonDTO;
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
     * Creates new Delivery d
     *
     * @param d
     */
    public void create(DeliveryCreateDTO d);
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
    
    /**
     * Reorder Courier's Deliveries alphabeticaly by Cities
     * 
     * @param courier
     * @return 
     */
    public List<DeliveryDTO> reorderCouriersDeliveries(PersonDTO courier);
}
