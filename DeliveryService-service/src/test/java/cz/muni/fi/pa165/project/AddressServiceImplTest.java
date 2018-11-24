package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.project.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.project.dao.AddressDao;
import cz.muni.fi.pa165.project.entity.Address;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for AddressService
 *
 * @author Tomas Terem
 */
@ContextConfiguration(classes = {ServiceConfiguration.class})
public class AddressServiceImplTest extends AbstractTransactionalTestNGSpringContextTests {

    @Mock
    private AddressDao addressDao;

    @Autowired
    @InjectMocks
    private AddressService addressService;

    private Address address1;
    private Address address2;
    private Address address3;
    private Address address4;
    
    @BeforeClass
    public void setup() {
        address1 = new Address();
        address1.setId(1L);
        address1.setStreet("Privet Drive");
        address1.setStreetNumber("4");
        address1.setCity("Little Whinging");
        address1.setPostalCode("62442");

        address2 = new Address();
        address2.setId(2L);
        address2.setStreet("Grimmauld Place");
        address2.setStreetNumber("12");
        address2.setCity("London");
        address2.setPostalCode("WC2N 5DU");

        address3 = new Address();
        address3.setId(3L);
        address3.setStreet("Privet Drive");
        address3.setStreetNumber("4");
        address3.setCity("Little Whinging");
        address3.setPostalCode("62442");

        address4 = new Address();
        address4.setId(4L);
        address4.setStreet("Grimmauld Place");
        address4.setStreetNumber("12");
        address4.setCity("London");
        address4.setPostalCode("WC2N 5DU");
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createTest(){
        Address address = address1;
        addressService.create(address);
        verify(addressDao).create(address);
    }


    @Test
    public void deleteTest(){
        Address address = address1;
        addressService.delete(address);
        verify(addressDao).delete(address);
    }

    @Test
    public void updateTest(){
        Address address = address1;
        addressService.update(address);
        verify(addressDao).update(address);
    }

    @Test
    public void findByIdTest(){
        Long id = 1L;
        addressService.findById(id);
        verify(addressDao).findById(id);
    }

    @Test
    public void findAllTest(){
        addressService.findAll();
        verify(addressDao).findAll();
    }

    @Test
    public void findCityWithMostAddressesTest() {
        List<Address> addresses = Arrays.asList(address1, address2, address3);
        when(addressDao.findAll()).thenReturn(new ArrayList<>(addresses));
        String city = addressService.findCityWithMostAddresses();
        assertTrue(city.equals("Little Whinging"));
    }

    @Test
    public void findCityWithMostAddressesTieTest() {
        List<Address> addresses = Arrays.asList(address1, address2, address3, address4);
        when(addressDao.findAll()).thenReturn(new ArrayList<>(addresses));
        String city = addressService.findCityWithMostAddresses();
        assertTrue(city.equals("Little Whinging") || city.equals("London"));
    }

    @Test
    public void findCityWithMostAddressesNullTest() {
        List<Address> addresses = Arrays.asList();
        when(addressDao.findAll()).thenReturn(new ArrayList<>(addresses));
        String city = addressService.findCityWithMostAddresses();
        assertTrue(city == null);
    }
}