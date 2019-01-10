package cz.muni.fi.pa165.project.facade;

import cz.muni.fi.pa165.project.DeliveryService;
import cz.muni.fi.pa165.project.BeanMappingService;
import cz.muni.fi.pa165.project.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.project.dto.DeliveryDTO;
import cz.muni.fi.pa165.project.entity.Address;
import cz.muni.fi.pa165.project.entity.Delivery;
import cz.muni.fi.pa165.project.entity.Person;
import org.mockito.Spy;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * TODO: create javadoc *
 *
 * @author Patrik Majercik
 */
@ContextConfiguration(classes = {ServiceConfiguration.class})
public class DeliveryFacadeTest extends AbstractTestNGSpringContextTests {
    @Mock
    private DeliveryService deliveryService;

    @Spy
    @Inject
    private BeanMappingService beanMappingService;

    @InjectMocks
    private DeliveryFacade deliveryFacade = new DeliveryFacadeImpl();

    private DeliveryDTO deliveryDTO1;
    private DeliveryDTO deliveryDTO2;
    private Delivery delivery1;
    private Delivery delivery2;
    private Person p1;
    private Person p2;
    private Address ad1;
    private Address ad2;

    @BeforeMethod
    public void setup() {

        ad1 = new Address();
        ad2 = new Address();
        p1 = new Person();
        p2 = new Person();
        delivery1 = new Delivery();
        delivery2 = new Delivery();
        
        ad1.setCity("Brno");
        ad1.setStreet("Antoninova");
        ad2.setCity("Zlin");
        ad2.setStreet("Cecilova");
        p1.setAddress(ad1);
        p1.setName("prvy");
        p2.setAddress(ad2);
        p2.setName("druhy");

        delivery1.setCustomer(p1);
        delivery1.setCourier(p2);
        delivery2.setCustomer(p2);
        delivery2.setCourier(p1);
        this.deliveryDTO1 = beanMappingService.mapTo(delivery1, DeliveryDTO.class);
        this.deliveryDTO2 = beanMappingService.mapTo(delivery2, DeliveryDTO.class);
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void createTest() {
        deliveryFacade.create(deliveryDTO1);
        verify(deliveryService).create(delivery1);
    }
    
    @Test
    public void updateTest() {
        deliveryFacade.update(deliveryDTO1);
        verify(deliveryService).update(delivery1);
    }

    @Test
    public void deleteTest() {
        deliveryFacade.delete(deliveryDTO1);
        verify(deliveryService).delete(delivery1);
    }

    @Test
    public void findByIdTest() {
        when(deliveryService.findById(delivery1.getId())).thenReturn(delivery1);
        assertEquals(deliveryFacade.findById(deliveryDTO1.getId()), deliveryDTO1);
    }

    @Test
    public void findAllTest() {
        when(deliveryService.findAll()).thenReturn(new ArrayList<>(asList(delivery1, delivery2)));
        assertTrue(deliveryFacade.findAll().contains(deliveryDTO1));
        assertTrue(deliveryFacade.findAll().contains(deliveryDTO2));
    }

}
