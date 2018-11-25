package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.project.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.project.dao.DeliveryDao;
import cz.muni.fi.pa165.project.entity.Article;
import cz.muni.fi.pa165.project.entity.Delivery;
import cz.muni.fi.pa165.project.entity.DeliveryState;
import cz.muni.fi.pa165.project.entity.Person;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jakub Gavlas
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class DeliveryServiceTest extends AbstractTestNGSpringContextTests {
    
    @Mock
    private DeliveryDao deliveryDao;
    
    @Autowired
    @InjectMocks
    private DeliveryService deliveryService;
    
    private Delivery delivery1;
    private Person p1;
    private Person p2;
    private Article a1;
    private Article a2;
    
    @BeforeMethod
    public void createOrders() {
        p1 = new Person();
        p2 = new Person();
        a1 = new Article();
        a2 = new Article();
        delivery1 = new Delivery();
        
        delivery1.setId(1L);
        delivery1.setOrdered(LocalDateTime.MIN);
        delivery1.setDelivered(LocalDateTime.MIN);
        delivery1.setPrice(BigDecimal.ONE);
        delivery1.setCourier(p1);
        delivery1.setCustomer(p2);
        delivery1.setArticles(Arrays.asList(a1, a2));
        delivery1.setDeliveryState(DeliveryState.CANCELED);
    }
    
    @BeforeClass
    public void setup() throws ServiceException {
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
        verify(deliveryDao).findById(id);
    }

    @Test
    public void findAllTest(){
        deliveryService.findAll();
        verify(deliveryDao).findAll();
    }
}
