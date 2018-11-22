package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.project.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.project.dao.PersonDao;
import cz.muni.fi.pa165.project.entity.Address;
import cz.muni.fi.pa165.project.entity.Person;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.Mockito.verify;

/**
 * Tests for PersonService
 *
 * @author Tomas Terem
 */
@ContextConfiguration(classes = {ServiceConfiguration.class})
public class PersonServiceImplTest extends AbstractTransactionalTestNGSpringContextTests {

    @Mock
    private PersonDao personDao;

    @Autowired
    @InjectMocks
    private PersonService personService;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    private Person getPerson() {
        Address address = new Address();
        address.setId(1L);
        address.setStreet("Privet Drive");
        address.setStreetNumber("4");
        address.setCity("Little Whinging");
        address.setPostalCode("62442");

        Person person = new Person();
        person.setId(1L);
        person.setName("Harry Potter");
        person.setPhoneNumber("+006244262442");
        person.setEmail("hpotter@hogwarts.com");
        person.setAddress(address);
        return person;
    }


    @Test
    public void findByIdTest(){
        Long id = 1L;
        personService.findById(id);
        verify(personDao).findById(id);
    }

    @Test
    public void findPersonByEmailTest(){
        personService.findPersonByEmail(getPerson().getEmail());
        verify(personDao).findPersonByEmail(getPerson().getEmail());
    }

    @Test
    public void findPersonByNameTest(){
        personService.findPersonByName(getPerson().getName());
        verify(personDao).findPersonByName(getPerson().getName());
    }

    @Test
    public void findAllTest(){
        personService.findAll();
        verify(personDao).findAll();
    }

    @Test
    public void createTest(){
        Person person = getPerson();
        personService.create(person);
        verify(personDao).create(person);
    }


    @Test
    public void deleteTest(){
        Person person = getPerson();
        personService.delete(person);
        verify(personDao).delete(person);
    }

    @Test
    public void updateTest(){
        Person person = getPerson();
        personService.update(person);
        verify(personDao).update(person);
    }

}
