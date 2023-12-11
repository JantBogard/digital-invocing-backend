package cm.uni2grow.digitalInvocing.address.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import cm.uni2grow.digitalInvocing.address.metier.AddressMetier;
import cm.uni2grow.digitalInvocing.address.model.dao.Address;
import cm.uni2grow.digitalInvocing.address.model.dto.AddressDto;
import cm.uni2grow.digitalInvocing.address.repository.AddressRepository;

public class AddressService implements AddressMetier {

    private AddressRepository addressRepository;

    AddressService(AddressRepository theAddressRepository) {
        this.addressRepository = theAddressRepository;
    }

    @Override
    public AddressDto save(AddressDto address) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public List<AddressDto> getAllAddress() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllAddress'");
    }

    @Override
    public AddressDto getAddress(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAddress'");
    }

    @Override
    public AddressDto update(Long id, AddressDto address) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    static AddressDto permutAddressToAddressDto(Address address) {
        AddressDto addressDto = new AddressDto();

        BeanUtils.copyProperties(address, addressDto);
        return addressDto;
    }

    static List<AddressDto> permutListAddressesToListAddressDtos(List<Address> addresses) {
        return addresses.stream().map(address -> permutAddressToAddressDto(address)).collect(Collectors.toList());
    }

}
