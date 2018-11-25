package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.project.entity.Delivery;
import java.util.List;

/**
 *
 * @author jakub Gavlas
 */
public interface DeliveryService {
    public void create(Delivery d);
    public void update(Delivery d);
    public void delete(Delivery d);
    public Delivery findById(Long id);
    public List<Delivery> findAll();
}
