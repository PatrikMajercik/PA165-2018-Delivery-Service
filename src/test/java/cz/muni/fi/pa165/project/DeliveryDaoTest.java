package cz.muni.fi.pa165.project;


import cz.muni.fi.pa165.project.dao.DeliveryDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ValidationException;

import cz.muni.fi.pa165.project.entity.Address;
import cz.muni.fi.pa165.project.entity.Article;
import cz.muni.fi.pa165.project.entity.Delivery;
import cz.muni.fi.pa165.project.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static org.testng.Assert.*;

import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Tomas Terem
 */
@ContextConfiguration(classes = cz.muni.fi.pa165.project.ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class DeliveryDaoTest extends AbstractTestNGSpringContextTests{

    @Autowired
    private DeliveryDao deliveryDao;

    @PersistenceContext
    EntityManager em;


    @Test
    public void createDelivery(){
        Delivery d = getSimpleDelivery();
        deliveryDao.create(d);
        assertEquals(d, em.find(Delivery.class, d.getId()));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullDelivery(){
        Delivery d = null;
        deliveryDao.create(d);
    }

    @Test(expectedExceptions = ValidationException.class)
    public void createDeliveryWithId() {
        Delivery d = getSimpleDelivery();
        deliveryDao.create(d); // id assigned
        deliveryDao.create(d); // exception thrown
    }

    @Test
    public void updateDelivery(){
        Delivery d = getSimpleDelivery();
        em.persist(d);
        d.setPrice(new BigDecimal(500));
        deliveryDao.update(d);
        assertEquals(d.getPrice(), em.find(Delivery.class, d.getId()).getPrice());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNullDelivery(){
        deliveryDao.update(null);
    }

    @Test(expectedExceptions = ValidationException.class)
    public void updateDeliveryWithNullId() {
        Delivery d = getSimpleDelivery();
        deliveryDao.update(d);
    }

    @Test
    public void deleteDelivery(){
        Delivery d = getSimpleDelivery();
        em.persist(d);
        deliveryDao.delete(d);
        assertNull(em.find(Delivery.class, d.getId()));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNullDelivery(){
        deliveryDao.delete(null);
    }

    @Test(expectedExceptions = ValidationException.class)
    public void deleteDeliveryWithNullId() {
        Delivery a = getSimpleDelivery();
        deliveryDao.delete(a);
    }

    @Test
    public void findById(){
        Delivery d = getSimpleDelivery();
        em.persist(d);
        assertEquals(d, deliveryDao.findById(d.getId()));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByNullId(){
        Delivery delivery = deliveryDao.findById(null);
    }

    @Test
    public void findAll(){
        Delivery d1 = getSimpleDelivery();
        Delivery d2 = getSimpleDelivery();
        d2.setPrice(new BigDecimal(500));
        em.persist(d1);
        em.persist(d2);
        List<Delivery> list = deliveryDao.findAll();
        assertTrue(list.contains(d2));
        assertTrue(list.contains(d1));
    }

    private Delivery getSimpleDelivery() {
        // Address for Customer
        Address address = new Address();
        address.setStreet("Privet Drive");
        address.setStreetNumber("4");
        address.setCity("Little Whinging");
        address.setPostalCode("62442");
        em.persist(address);

        // Customer
        Person person = new Person();
        person.setName("Harry Potter");
        person.setPhoneNumber("+006244262442");
        person.setEmail("hpotter@hogwarts.com");
        person.setAddress(address);
        em.persist(person);

        // Address for Courier
        Address address2 = new Address();
        address2.setStreet("Grimmauld Place");
        address2.setStreetNumber("12");
        address2.setCity("London");
        address2.setPostalCode("WC2N 5DU");
        em.persist(address2);

        // Courier
        Person person2 = new Person();
        person2.setName("Sirius Black");
        person2.setPhoneNumber("+390390390390");
        person2.setEmail("padfoot@hogwarts.com");
        person2.setAddress(address2);
        em.persist(person2);

        // Article
        Article article = new Article();
        article.setName("Christmas present");
        article.setWeight(new BigDecimal(25));
        em.persist(article);
        List<Article> articles = new ArrayList<>();
        articles.add(article);

        // Delivery
        Delivery d = new Delivery();
        d.setPrice(new BigDecimal(200));
        d.setCustomer(person);
        d.setCourier(person2);
        d.setArticles(articles);
        d.setOrdered(new GregorianCalendar(2017, Calendar.DECEMBER, 20).getTime());
        d.setDelivered(new GregorianCalendar(2017, Calendar.DECEMBER, 24).getTime());
        return d;
    }
}