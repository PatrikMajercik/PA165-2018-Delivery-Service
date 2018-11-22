package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.project.dao.AddressDao;
import cz.muni.fi.pa165.project.entity.Address;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.*;

/**
 * AddressService implementation
 *
 * @author Tomas Terem
 */
@Service
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

    @Override
    public String findCityWithMostAddresses() {
        List<Address> addresses = addressDao.findAll();
        Map<String, Integer> cityCount = new HashMap<>();
        for (Address address : addresses) {
            String city = address.getCity();
            Integer count = cityCount.get(city);
            cityCount.put(city, count != null ? count + 1 : 1);
        }

        int max = 0;
        String result = null;
        for (Map.Entry<String, Integer> entry : cityCount.entrySet())
        {
            if (entry.getValue() > max) {
                max = entry.getValue();
                result = entry.getKey();
            }
        }
        return result;
    }
}
