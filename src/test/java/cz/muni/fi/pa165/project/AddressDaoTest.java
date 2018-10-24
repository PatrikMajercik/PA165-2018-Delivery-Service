package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.project.dao.AddressDao;
import cz.muni.fi.pa165.project.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.testng.Assert.assertEquals;

@ContextConfiguration(classes = ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class AddressDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private AddressDao addressDao;

    @PersistenceContext
    private EntityManager em;


    @Test
    public void testCreateAddress() {
        Address a = new Address();
        a.setCity("Sliac");
        a.setStreet("Rybarska");
        a.setStreetNumber("56");
        a.setPostalCode("56787");
        addressDao.create(a);
        assertEquals(a, em.find(Address.class, a.getId()));
    }
}
