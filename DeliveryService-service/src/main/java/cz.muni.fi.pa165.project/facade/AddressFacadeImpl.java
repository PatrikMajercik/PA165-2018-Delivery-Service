package cz.muni.fi.pa165.project.facade;

import cz.muni.fi.pa165.project.AddressService;
import cz.muni.fi.pa165.project.BeanMappingService;
import cz.muni.fi.pa165.project.dto.AddressDTO;
import cz.muni.fi.pa165.project.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AddressFacadeImpl implements AddressFacade {

    @Inject
    private AddressService addressService;

    @Autowired
    private BeanMappingService beanMappingService;

    public void create(AddressDTO addressDTO) {
        Address mappedAddress = beanMappingService.mapTo(addressDTO, Address.class);
        addressService.create(mappedAddress);
    }

    public void update(AddressDTO addressDTO) {
        Address mappedAddress = beanMappingService.mapTo(addressDTO, Address.class);
        addressService.update(mappedAddress);
    }

    public void delete(AddressDTO addressDTO) {
        Address mappedAddress = beanMappingService.mapTo(addressDTO, Address.class);
        addressService.delete(mappedAddress);
    }

    public AddressDTO findById(Long id) {
        return beanMappingService.mapTo(addressService.findById(id), AddressDTO.class);
    }

    public List<AddressDTO> findAll() {
        return beanMappingService.mapTo(addressService.findAll(), AddressDTO.class);
    }
}