package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.project.dao.AddressDao;
import cz.muni.fi.pa165.project.entity.Address;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ValidationException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

/**
 * Tests for AddressDao
 *
 * @author Jakub Gavlas
 */
@ContextConfiguration(classes = cz.muni.fi.pa165.project.ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class AddressDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private AddressDao addressDao;

    @PersistenceContext
    private EntityManager em;


    @Test
    public void createAddress() {
        Address a = genericAddress();
        addressDao.create(a);
        assertEquals(a, em.find(Address.class, a.getId()));
    }    
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullAddress(){
        Address a = null;
        addressDao.create(a);        
    }
    
    @Test(expectedExceptions = ValidationException.class)
    public void createAddressWithId() {
        Address a = genericAddress();
        addressDao.create(a); // id assigned
        addressDao.create(a); // exception thrown
    }
    
    @Test
    public void updateAddress(){
        Address a = genericAddress();
        em.persist(a);
        a.setCity("Brno");
        addressDao.update(a);
        assertEquals(a.getCity(), em.find(Address.class, a.getId()).getCity());
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNullAddress(){
        Address a = null;
        addressDao.update(a);
    }
    
    @Test(expectedExceptions = ValidationException.class)
    public void updateAddressWithNullId() {
        Address a = genericAddress();
        addressDao.update(a);
    }
    
    @Test
    public void removeAddress(){
        Address a = genericAddress();
        em.persist(a);
        addressDao.delete(a);
        assertNull(em.find(Address.class, a.getId()));        
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void removeNullAddress(){
        Address a = null;
        addressDao.delete(a);
    }
    
    @Test(expectedExceptions = ValidationException.class)
    public void deleteAddressWithNullId() {
        Address a = genericAddress();
        addressDao.delete(a);
    }
    
    @Test
    public void findById(){
        Address a = genericAddress();
        em.persist(a);
        assertEquals(a, addressDao.findById(a.getId()));
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByNullId(){
        Address a = addressDao.findById(null);
    }
    @Test
    public void findAll(){
        Address a1 = genericAddress();
        Address a2 = genericAddress();
        a2.setCity("Brno");
        em.persist(a1);
        em.persist(a2);
        List<Address> list = addressDao.findAll();
        assertTrue(list.contains(a2));
        assertTrue(list.contains(a1));
    }
    
    private Address genericAddress() {
        Address a = new Address();
        a.setCity("Sliac");
        a.setStreet("Rybarska");
        a.setStreetNumber("56");
        a.setPostalCode("56787");
        return a;
    }
}
