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

import static org.mockito.Mockito.verify;

@ContextConfiguration(classes = {ServiceConfiguration.class})
public class AddressServiceImplTest extends AbstractTransactionalTestNGSpringContextTests {

    @Mock
    private AddressDao addressDao;

    @Autowired
    @InjectMocks
    private AddressService addressService;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    private Address getAnAddress() {
        Address address = new Address();
        address.setId(1L);
        address.setStreet("Privet Drive");
        address.setStreetNumber("4");
        address.setCity("Little Whinging");
        address.setPostalCode("62442");
        return address;
    }


    @Test
    public void testFindById(){
        Long id = 1L;
        addressService.findById(id);
        verify(addressDao).findById(id);
    }

    @Test
    public void testFindAll(){
        addressService.findAll();
        verify(addressDao).findAll();
    }

    @Test
    public void testCreate(){
        Address address = getAnAddress();
        addressService.create(address);
        verify(addressDao).create(address);
    }


    @Test
    public void testDelete(){
        Address address = getAnAddress();
        addressService.delete(address);
        verify(addressDao).delete(address);
    }

    @Test
    public void testUpdate(){
        Address address = getAnAddress();
        addressService.update(address);
        verify(addressDao).update(address);
    }
}