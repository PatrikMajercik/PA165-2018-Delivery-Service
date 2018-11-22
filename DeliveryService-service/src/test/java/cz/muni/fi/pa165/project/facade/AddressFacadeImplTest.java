package cz.muni.fi.pa165.project.facade;

import cz.muni.fi.pa165.project.AddressService;
import cz.muni.fi.pa165.project.BeanMappingService;
import cz.muni.fi.pa165.project.dto.AddressDTO;
import cz.muni.fi.pa165.project.entity.Address;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import javax.inject.Inject;
import javax.validation.ValidationException;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = cz.muni.fi.pa165.project.ApplicationContext.class)
public class AddressFacadeImplTest extends AbstractTestNGSpringContextTests {

    @Mock
    private AddressService addressService;

    @Inject
    private BeanMappingService beanMappingService;

    @InjectMocks
    private AddressFacade addressFacade = new AddressFacadeImpl();

    private Address address1;
    private Address address2;

    @BeforeMethod
    public void setUp() {
        setUpAddresses();
        MockitoAnnotations.initMocks(this);
        when(addressService.findById(address1.getId())).thenReturn(address1);
        when(addressService.findAll()).thenReturn(Arrays.asList(address1, address2));
    }

    @Test
    public void createAddress() {
        AddressDTO addressDTO = beanMappingService.mapTo(address1, AddressDTO.class);
        addressFacade.create(addressDTO);
        verify(addressService).create(any(Address.class));
    }

    @Test
    public void updateAddress() {
        AddressDTO addressDTO = beanMappingService.mapTo(address1, AddressDTO.class);
        addressDTO.setCity("Brno");
        addressFacade.update(addressDTO);
        verify(addressService).update(any(Address.class));
    }

    @Test
    public void deleteAddress() {
        AddressDTO addressDTO = beanMappingService.mapTo(address1, AddressDTO.class);
        addressFacade.delete(addressDTO);
        verify(addressService).delete(any(Address.class));
    }

    @Test
    public void findById() {
        AddressDTO addressDTO = addressFacade.findById(address1.getId());
        verify(addressService).findById(address1.getId());
        assertEquals(address1, beanMappingService.mapTo(addressDTO, Address.class));
    }

    @Test
    public void findAll() {
        List<AddressDTO> addressDTOs = addressFacade.findAll();
        verify(addressService).findAll();
        assertEquals(2, addressDTOs.size());
        List<Address> addresss = beanMappingService.mapTo(addressDTOs, Address.class);
        assertTrue(addresss.contains(address1));
        assertTrue(addresss.contains(address2));
    }

    private void setUpAddresses() {
        address1 = new Address();
        address1.setId(1L);
        address1.setStreet("Privet Drive");
        address1.setStreetNumber("4");
        address1.setCity("Little Whinging");
        address1.setPostalCode("62442");

        address2 = new Address();
        address1.setId(2L);
        address2.setStreet("Grimmauld Place");
        address2.setStreetNumber("12");
        address2.setCity("London");
        address2.setPostalCode("WC2N 5DU");
    }

}

