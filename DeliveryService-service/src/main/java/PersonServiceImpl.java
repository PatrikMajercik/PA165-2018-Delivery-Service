import cz.muni.fi.pa165.project.dao.PersonDao;
import cz.muni.fi.pa165.project.entity.Person;

import javax.inject.Inject;
import java.util.List;

public class PersonServiceImpl implements PersonService {

    @Inject
    private PersonDao personDao;

    public void create(Person person) {
        personDao.create(person);
    }

    public void update(Person person) {
        personDao.update(person);
    }

    public void delete(Person person) {
        personDao.delete(person);
    }

    public Person findById(Long id) {
        return personDao.findById(id);
    }

    public List<Person> findPersonByName(String name) {
        return personDao.findPersonByName(name);
    }

    public List<Person> findPersonByEmail(String email) {
        return personDao.findPersonByEmail(email);
    }

    public List<Person> findAll() {
        return personDao.findAll();
    }
}
