package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.project.dao.AddressDao;
import cz.muni.fi.pa165.project.entity.Address;

import javax.inject.Inject;
import java.util.List;

public class AddressServiceImpl implements AddressService {

    @Inject
    private AddressDao addressDao;

    public void create(Address address) {
        addressDao.create(address);
    }

    public void update(Address address) {
        addressDao.update(address);
    }

    public void delete(Address address) {
        addressDao.delete(address);
    }

    public Address findById(Long id) {
        return addressDao.findById(id);
    }

    public List<Address> findAll() {
        return addressDao.findAll();
    }
}
