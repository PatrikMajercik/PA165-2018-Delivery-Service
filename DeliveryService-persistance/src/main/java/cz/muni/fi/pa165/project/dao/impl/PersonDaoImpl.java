package cz.muni.fi.pa165.project.dao.impl;

import cz.muni.fi.pa165.project.dao.PersonDao;
import cz.muni.fi.pa165.project.entity.Person;
import org.hibernate.validator.constraints.NotBlank;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Tomas Terem
 */
@Repository
public class PersonDaoImpl implements PersonDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(@NotNull Person person) throws IllegalArgumentException, ValidationException {
        if (person == null) {
            throw new IllegalArgumentException("person is null");
        }
        if (person.getId() != null) {
            throw new ValidationException("person id cannot be set before creation");
        }
        entityManager.persist(person);
    }

    @Override
    public void update(@NotNull Person person) throws IllegalArgumentException, ValidationException {
        if (person == null) {
            throw new IllegalArgumentException("person is null");
        }
        if (person.getId() == null) {
            throw new ValidationException("person id null");
        }
        entityManager.merge(person);
    }

    @Override
    public void delete(@NotNull Person person) throws IllegalArgumentException, ValidationException {
        if (person == null) {
            throw new IllegalArgumentException("person is null");
        }
        if (person.getId() == null) {
            throw new ValidationException("person id null");
        }
        entityManager.remove(findById(person.getId()));
    }

    @Override
    public Person findById(@NotNull Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        return entityManager.find(Person.class, id);
    }

    @Override
    public List<Person> findPersonByName(@NotBlank @NotNull String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("name is null");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("name is empty");
        }
        return entityManager.createQuery("SELECT p FROM Person p WHERE name=:name",
                Person.class).setParameter("name", name).getResultList();

    }

    @Override
    public Person findPersonByEmail(@NotBlank @NotNull String email) throws IllegalArgumentException {
        if (email == null) {
            throw new IllegalArgumentException("email is null");
        }
        if (email.isEmpty()) {
            throw new IllegalArgumentException("email is empty");
        }
        return entityManager.createQuery("SELECT p FROM Person p WHERE email=:email",
                Person.class).setParameter("email", email).getSingleResult();
    }

    @Override
    public List<Person> findAll() {
        return entityManager.createQuery("SELECT p FROM Person p", Person.class)
                .getResultList();
    }
}
