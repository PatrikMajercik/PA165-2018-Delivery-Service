package cz.muni.fi.pa165.project.facade;

import cz.muni.fi.pa165.project.DeliveryService;
import cz.muni.fi.pa165.project.BeanMappingService;
import cz.muni.fi.pa165.project.dto.DeliveryDTO;
import cz.muni.fi.pa165.project.entity.Delivery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Jakub Gavlas
 */
public class DeliveryFacadeImpl implements DeliveryFacade{

    @Autowired
    private DeliveryService deliveryService;
    
    @Autowired
    private BeanMappingService beanMappingService;
    
    @Override
    public void create(DeliveryDTO d) {
        Delivery mappedDelivery = beanMappingService.mapTo(d, Delivery.class);
        deliveryService.create(mappedDelivery);
    }

    @Override
    public void update(DeliveryDTO d) {
        Delivery mappedDelivery = beanMappingService.mapTo(d, Delivery.class);
        deliveryService.update(mappedDelivery);
    }

    @Override
    public void delete(DeliveryDTO d) {
        Delivery mappedDelivery = beanMappingService.mapTo(d, Delivery.class);
        deliveryService.delete(mappedDelivery);
    }

    @Override
    public DeliveryDTO findById(Long id) {
        return beanMappingService.mapTo(deliveryService.findById(id), DeliveryDTO.class);
    }

    @Override
    public List<DeliveryDTO> findAll() {
        return beanMappingService.mapTo(deliveryService.findAll(), DeliveryDTO.class);
    }
    
}
