package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.project.dao.DeliveryDao;
import cz.muni.fi.pa165.project.entity.Delivery;
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
    
}
