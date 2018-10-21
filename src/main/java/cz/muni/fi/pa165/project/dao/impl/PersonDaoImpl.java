package cz.muni.fi.pa165.project.dao.impl;

import cz.muni.fi.pa165.project.dao.PersonDao;
import cz.muni.fi.pa165.project.entity.Person;
import org.hibernate.validator.constraints.NotBlank;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public void create(@NotNull Person person) {
        entityManager.persist(person);
    }

    @Override
    public void update(@NotNull Person person) {
        entityManager.merge(person);
    }

    @Override
    public void delete(@NotNull Person person) {
        entityManager.remove(findById(person.getId()));
    }

    @Override
    public Person findById(@NotNull Long id) {
        return entityManager.find(Person.class, id);
    }

    @Override
    public List<Person> findPersonByName(@NotBlank String name) {
        return entityManager.createQuery("SELECT p FROM Person p WHERE name=:name",
                Person.class).setParameter("name", name).getResultList();

    }

    @Override
    public List<Person> findPersonByEmail(@NotBlank String email) {
        return entityManager.createQuery("SELECT p FROM Person p WHERE email=:email",
                Person.class).setParameter("email", email).getResultList();
    }

    @Override
    public List<Person> findAll() {
        return entityManager.createQuery("SELECT p FROM Person p", Person.class)
                .getResultList();
    }
}
