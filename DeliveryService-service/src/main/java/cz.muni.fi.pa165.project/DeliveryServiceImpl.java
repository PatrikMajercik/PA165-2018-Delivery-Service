package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.project.dao.DeliveryDao;
import cz.muni.fi.pa165.project.entity.Delivery;
import cz.muni.fi.pa165.project.entity.Person;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jakub Gavlas
 */
@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryDao deliveryDao;
    
    @Override
    public void create(Delivery d) {
        deliveryDao.create(d);
    }

    @Override
    public void update(Delivery d) {
        deliveryDao.update(d);
    }

    @Override
    public void delete(Delivery d) {
        deliveryDao.delete(d);
    }

    @Override
    public Delivery findById(Long id) {
        return deliveryDao.findById(id);
    }

    @Override
    public List<Delivery> findAll() {
        return deliveryDao.findAll();
    }
    
    @Override
    public List<Delivery> reorderCouriersDeliveries(Person courier){
        List<Delivery> deliveries = deliveryDao.findAll();
        List<Delivery> couriersDeliveries = new ArrayList<>();
        deliveries.stream().filter((d) -> (d.getCourier().equals(courier))).forEachOrdered((d) -> {
            couriersDeliveries.add(d);
        });
        //Order by address
        couriersDeliveries.sort((o1,o2) -> o1.getCustomer().getAddress().getStreet().compareTo(o2.getCustomer().getAddress().getStreet()));
        //Order by city
        couriersDeliveries.sort((o1,o2) -> o1.getCustomer().getAddress().getCity().compareTo(o2.getCustomer().getAddress().getCity()));
        if(couriersDeliveries.isEmpty()) return null;
        return couriersDeliveries;
    }
}
