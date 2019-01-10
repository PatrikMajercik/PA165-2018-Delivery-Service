package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.project.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.project.dao.DeliveryDao;
import cz.muni.fi.pa165.project.entity.Address;
import cz.muni.fi.pa165.project.entity.Delivery;
import cz.muni.fi.pa165.project.entity.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.mockito.Mockito.when;
import org.testng.Assert;

/**
 *
 * @author Jakub Gavlas
 */
@ContextConfiguration(classes = {ServiceConfiguration.class})
public class DeliveryServiceTest extends AbstractTestNGSpringContextTests {
    
    @Mock
    private DeliveryDao deliveryDao;
    
    @Autowired
    @InjectMocks
    private DeliveryService deliveryService;
    
    private Delivery delivery1;
    private Delivery delivery2;
    private Delivery delivery3;
    private Delivery delivery4;
    private Person p1;
    private Person p2;
    private Person p3;
    private Person p4;
    private Person p5;
    private Address ad1;
    private Address ad2;
    private Address ad3;
    private Address ad4;
    
    @BeforeClass
    public void setup() throws ServiceException {
        p1 = new Person();
        p2 = new Person();
        p3 = new Person();
        p4 = new Person();
        p5 = new Person();
        ad1 = new Address();
        ad2 = new Address();
        ad3 = new Address();
        ad4 = new Address();
        delivery1 = new Delivery();
        delivery2 = new Delivery();
        delivery3 = new Delivery();
        delivery4 = new Delivery();
        
        ad1.setCity("Brno");
        ad1.setStreet("Antoninova");
        ad2.setCity("Zlin");
        ad2.setStreet("Cecilova");
        ad3.setCity("Zlin");
        ad3.setStreet("Bohusova");
        ad4.setCity("Praha");
        ad4.setStreet("Frantova");
        
        p1.setAddress(ad1);
        p1.setName("prvy");
        p2.setAddress(ad2);
        p2.setName("druhy");
        p3.setAddress(ad3);
        p3.setName("treti");
        p4.setAddress(ad4);
        p4.setName("stvrty");
        
        delivery1.setId(1L);
        delivery1.setCustomer(p1);
        delivery1.setCourier(p5);
        
        delivery2.setId(2L);
        delivery2.setCustomer(p2);
        delivery2.setCourier(p5);
        
        delivery3.setId(3L);
        delivery3.setCustomer(p3);
        delivery3.setCourier(p5);
        
        delivery4.setId(4L);
        delivery4.setCustomer(p4);
        delivery4.setCourier(p5);
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void createTest(){
        Delivery delivery = delivery1;
        deliveryService.create(delivery);
        verify(deliveryDao).create(delivery);
    }


    @Test
    public void deleteTest(){
        Delivery delivery = delivery1;
        deliveryService.delete(delivery);
        verify(deliveryDao).delete(delivery);
    }

    @Test
    public void updateTest(){
        Delivery delivery = delivery1;
        deliveryService.update(delivery);
        verify(deliveryDao).update(delivery);
    }

    @Test
    public void findByIdTest(){
        Long id = 1L;
        deliveryService.findById(id);
        verify(deliveryDao, times(1)).findById(id);
    }

    
    @Test
    public void findAllTest(){
        List<Delivery> expected = Arrays.asList(delivery1, delivery1);
        when(deliveryDao.findAll()).thenReturn(expected);
        List<Delivery> returned = deliveryService.findAll();
        verify(deliveryDao, times(3)).findAll();
        Assert.assertEquals(expected,returned);
    }
    
    
    @Test
    public void DeliveryOrdering(){
        List<Delivery> deliveries = Arrays.asList(delivery1, delivery2, delivery3, delivery4);
        when(deliveryDao.findAll()).thenReturn(new ArrayList<>(deliveries));
        Person courier = p5;
        List<Delivery> ordered = deliveryService.reorderCouriersDeliveries(courier);
        Assert.assertEquals(ordered.size(), 4);
        Assert.assertEquals(ordered.get(0),(delivery1));
        Assert.assertEquals(ordered.get(1),(delivery4));
        Assert.assertEquals(ordered.get(2),(delivery3));
        Assert.assertEquals(ordered.get(3),(delivery2));
        
    }
    
    @Test
    public void DeliveryOrderNull(){
        List<Delivery> deliveries = Arrays.asList(delivery1, delivery2, delivery3, delivery4);
        when(deliveryDao.findAll()).thenReturn(new ArrayList<>(deliveries));
        Person courier = p1;
        List<Delivery> ordered = deliveryService.reorderCouriersDeliveries(courier);
        Assert.assertTrue(ordered.isEmpty());
    }
}
