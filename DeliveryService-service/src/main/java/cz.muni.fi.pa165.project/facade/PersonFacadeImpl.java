package cz.muni.fi.pa165.project.facade;

import cz.muni.fi.pa165.project.AddressService;
import cz.muni.fi.pa165.project.BeanMappingService;
import cz.muni.fi.pa165.project.PersonService;
import cz.muni.fi.pa165.project.dto.PersonAuthenticateDTO;
import cz.muni.fi.pa165.project.dto.PersonEditDTO;
import cz.muni.fi.pa165.project.dto.PersonDTO;
import cz.muni.fi.pa165.project.entity.Person;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * PersonFacade implementation
 *
 * @author Tomas Terem
 */
@Service
@Transactional
public class PersonFacadeImpl implements PersonFacade {

    @Inject
    private PersonService personService;

    @Inject
    private AddressService addressService;

    @Inject
    private BeanMappingService beanMappingService;

    public void create(PersonDTO personDTO) {
        Person mappedAddress = beanMappingService.mapTo(personDTO, Person.class);
        personService.create(mappedAddress);
    }

    @Override
    public void create(PersonEditDTO personEditDTO) {
        Person person = new Person();
        person.setAddress(addressService.findById(personEditDTO.getAddressId()));
        person.setName(personEditDTO.getName());
        person.setEmail(personEditDTO.getEmail());
        person.setPhoneNumber(personEditDTO.getPhoneNumber());
        person.setAdmin(personEditDTO.getAdmin());
        personService.createWithPass(person, personEditDTO.getPassword());
        //personService.create(person);
    }

    public void update(PersonDTO personDTO) {
        Person mappedAddress = beanMappingService.mapTo(personDTO, Person.class);
        personService.update(mappedAddress);
    }

    public void update(PersonEditDTO personEditDTO) {
        Person person = new Person();
        person.setAddress(addressService.findById(personEditDTO.getAddressId()));
        person.setName(personEditDTO.getName());
        person.setEmail(personEditDTO.getEmail());
        person.setPhoneNumber(personEditDTO.getPhoneNumber());
        person.setId(personEditDTO.getId());
        person.setAdmin(personEditDTO.getAdmin());
        personService.updateWithPass(person, personEditDTO.getPassword());
    }

    public void delete(PersonDTO personDTO) {
        Person mappedAddress = beanMappingService.mapTo(personDTO, Person.class);
        personService.delete(mappedAddress);
    }

    public PersonDTO findById(Long id) {
        return beanMappingService.mapTo(personService.findById(id), PersonDTO.class);
    }

    public List<PersonDTO> findPersonByName(String name) {
        return beanMappingService.mapTo(personService.findPersonByName(name), PersonDTO.class);
    }

    public PersonDTO findPersonByEmail(String email) {
        return beanMappingService.mapTo(personService.findPersonByEmail(email), PersonDTO.class);
    }

    public List<PersonDTO> findAll() {
        return beanMappingService.mapTo(personService.findAll(), PersonDTO.class);
    }
    
    
    @Override
    public boolean authenticate(PersonAuthenticateDTO u) {
        return personService.authenticate(
                personService.findById(u.getPersonId()), u.getPassword());
    }

    @Override
    public boolean isAdmin(PersonDTO u) {
        return personService.isAdmin(beanMappingService.mapTo(u, Person.class));
    }
    
}
