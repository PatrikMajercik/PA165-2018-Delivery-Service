package cz.muni.fi.pa165.project.facade;

import cz.muni.fi.pa165.project.BeanMappingService;
import cz.muni.fi.pa165.project.PersonService;
import cz.muni.fi.pa165.project.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.project.dto.PersonDTO;
import cz.muni.fi.pa165.project.entity.Address;
import cz.muni.fi.pa165.project.entity.Person;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Tests for PersonFacade
 *
 * @author Tomas Terem
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class PersonFacadeImplTest extends AbstractTestNGSpringContextTests {

    @Mock
    private PersonService personService;

    @Spy
    @Inject
    private BeanMappingService beanMappingService;

    @InjectMocks
    private PersonFacade personFacade = new PersonFacadeImpl();

    private Address address1;
    private Address address2;

    private Person person1;
    private Person person2;

    @BeforeMethod
    public void setUp() {
        address1 = new Address();
        address1.setId(1L);
        address1.setStreet("Privet Drive");
        address1.setStreetNumber("4");
        address1.setCity("Little Whinging");
        address1.setPostalCode("62442");

        person1 = new Person();
        person1.setId(1L);
        person1.setName("Harry Potter");
        person1.setPhoneNumber("+006244262442");
        person1.setEmail("hpotter@hogwarts.com");
        person1.setAddress(address1);

        address2 = new Address();
        address2.setId(2L);
        address2.setStreet("Grimmauld Place");
        address2.setStreetNumber("12");
        address2.setCity("London");
        address2.setPostalCode("WC2N 5DU");

        person2 = new Person();
        person2.setId(2L);
        person2.setName("Sirius Black");
        person2.setPhoneNumber("+390390390390");
        person2.setEmail("padfoot@hogwarts.com");
        person2.setAddress(address2);

        MockitoAnnotations.initMocks(this);
        when(personService.findById(person1.getId())).thenReturn(person1);
        when(personService.findPersonByName(person1.getName())).thenReturn(Arrays.asList(person1));
        when(personService.findPersonByEmail(person2.getEmail())).thenReturn(Arrays.asList(person2));
        when(personService.findAll()).thenReturn(Arrays.asList(person1, person2));
    }

    @Test
    public void createPersonTest() {
        PersonDTO personDTO = beanMappingService.mapTo(person1, PersonDTO.class);
        personFacade.create(personDTO);
        verify(personService).create(any(Person.class));
    }

    @Test
    public void updatePersonTest() {
        PersonDTO personDTO = beanMappingService.mapTo(person1, PersonDTO.class);
        personDTO.setName("Severus Snape");
        personFacade.update(personDTO);
        verify(personService).update(any(Person.class));
    }

    @Test
    public void deletePersonTest() {
        PersonDTO personDTO = beanMappingService.mapTo(person1, PersonDTO.class);
        personFacade.delete(personDTO);
        verify(personService).delete(any(Person.class));
    }

    @Test
    public void findByIdTest() {
        PersonDTO personDTO = personFacade.findById(person1.getId());
        verify(personService).findById(person1.getId());
        assertEquals(person1, beanMappingService.mapTo(personDTO, Person.class));
    }

    @Test
    public void findPersonByNameTest() {
        List<PersonDTO> personDTOs = personFacade.findPersonByName(person1.getName());
        verify(personService).findPersonByName(person1.getName());
        assertEquals(1, personDTOs.size());
        List<Person> people = beanMappingService.mapTo(personDTOs, Person.class);
        assertTrue(people.contains(person1));
    }

    @Test
    public void findByEmailTest() {
        List<PersonDTO> personDTOs = personFacade.findPersonByEmail(person2.getEmail());
        verify(personService).findPersonByEmail(person2.getEmail());
        assertEquals(1, personDTOs.size());
        List<Person> people = beanMappingService.mapTo(personDTOs, Person.class);
        assertTrue(people.contains(person2));
    }

    @Test
    public void findAllTest() {
        List<PersonDTO> personDTOs = personFacade.findAll();
        verify(personService).findAll();
        assertEquals(2, personDTOs.size());
        List<Person> people = beanMappingService.mapTo(personDTOs, Person.class);
        assertTrue(people.contains(person1));
        assertTrue(people.contains(person2));
    }

}
