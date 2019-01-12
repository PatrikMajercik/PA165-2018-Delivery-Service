package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.project.dao.PersonDao;

import cz.muni.fi.pa165.project.entity.Address;
import cz.muni.fi.pa165.project.entity.Person;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
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


import java.util.List;

import static org.junit.Assert.assertNull;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Tests for PersonDao
 *
 * @author Patrik Majercik
 */
@ContextConfiguration(classes = cz.muni.fi.pa165.project.ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional

public class PersonDaoTest extends AbstractTestNGSpringContextTests {
    @Autowired
    PersonDao personDao;

    @PersistenceContext
    private EntityManager em;

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteWithNull() {
        personDao.delete(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateWithNull() {
        personDao.update(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByIdWithNull() {
        personDao.findById(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNameWithNull() {
        personDao.findPersonByName(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByEmailWithNull() {
        personDao.findPersonByEmail(null);
    }

    @Test(expectedExceptions = ValidationException.class)
    public void testCreateWithIdNotNull() {
        personDao.create(getValidPersonWithId());
    }

    @Test(expectedExceptions = ValidationException.class)
    public void testDeleteWithIdNotNull() {
        personDao.delete(getValidPersonWithoutId());
    }

    @Test(expectedExceptions = ValidationException.class)
    public void testUpdateWithIdNotNull() {
        personDao.update(getValidPersonWithoutId());
    }

    @Test
    public void testCreate() {
        Person p = getValidPersonWithoutId();
        personDao.create(p);
        assertEquals(p, em.find(Person.class, p.getId()));
    }
    @Test
    public void testDelete() {
        Person p = getValidPersonWithoutId();
        em.persist(p);
        personDao.delete(p);
        assertNull(em.find(Person.class, p.getId()));
    }

    @Test
    public void testUpdate() {
        Person p = getValidPersonWithoutId();
        em.persist(p);
        p.setName("Jozo");
        personDao.update(p);
        assertEquals(p,em.find(Person.class, p.getId()));
    }

    @Test
    public void testFindByName() {
    Person p = getValidPersonWithoutId();
        em.persist(p);
        personDao.findPersonByName(p.getName());
        assertEquals(p,em.find(Person.class, p.getId()));
    }

    @Test
    public void testFindById() {
        Person p = getValidPersonWithoutId();
        em.persist(p);
        personDao.findById(p.getId());
        assertEquals(p,em.find(Person.class, p.getId()));
    }

    @Test
    public void testFindByEmail() {
        Person p = getValidPersonWithoutId();
        em.persist(p);
        personDao.findPersonByName(p.getEmail());
        assertEquals(p,em.find(Person.class, p.getId()));
    }

    @Test
    public void testFindAll() {
        Person p1 = getValidPersonWithoutId();
        Person p2 = getValidPersonWithoutId();
        em.persist(p1);
        em.persist(p2);
        List<Person> personList =personDao.findAll();
        assertTrue(personList.contains(p1));
        assertTrue(personList.contains(p2));


    }

    private Person getValidPersonWithId() {
        Address address = Address.builder()
                .city("Brno")
                .postalCode("00060")
                .street("Lipova")
                .streetNumber("25")
                .build();
        return Person.builder()
                .id(1L)
                .address(address)
                .email("pmajercik@centrum.sk")
                .name("Paprik")
                .phoneNumber("+421918207555")
                .passwordHash("ccccc")
                .admin(true)
                .build();
    }

    private Person getValidPersonWithoutId() {
        Address address = Address.builder()
                .city("Brno")
                .postalCode("00060")
                .street("Lipova")
                .streetNumber("25")
                .build();
        em.persist(address);
        return Person.builder()
                .address(address)
                .email("pmajercik@centrum.sk")
                .name("Paprik")
                .phoneNumber("+421918207555")
                .passwordHash("ccccc")
                .admin(true)
                .build();
    }
}
