package cm.uni2grow.digitalInvocing.manageAddress.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;

import cm.uni2grow.digitalInvocing.config.manageError.ErrorMessages;
import cm.uni2grow.digitalInvocing.manageAddress.metier.AddressMetier;
import cm.uni2grow.digitalInvocing.manageAddress.model.dao.Address;
import cm.uni2grow.digitalInvocing.manageAddress.model.dto.AddressDto;
import cm.uni2grow.digitalInvocing.manageAddress.repository.AddressRepository;

public class AddressService implements AddressMetier {

    private AddressRepository addressRepository;

    AddressService(AddressRepository theAddressRepository) {
        this.addressRepository = theAddressRepository;
    }

    @Override
    public AddressDto save(Address address) {
        Address dbAddress = this.addressRepository.save(address);

        if (dbAddress == null) {
            throw new ErrorMessages("We have an error when we try to create this address",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return permutAddressToAddressDto(dbAddress);
    }

    @Override
    public List<AddressDto> getAllAddress() {
        return permutListAddressesToListAddressDtos(this.addressRepository.findAll());
    }

    @Override
    public AddressDto getAddress(Long id) {
        Optional<Address> optionalAddress = this.addressRepository.findById(id);

        if (optionalAddress.isEmpty()) {
            throw new ErrorMessages("Address not found", HttpStatus.NOT_FOUND);
        }

        return permutAddressToAddressDto(optionalAddress.get());
    }

    @Override
    public AddressDto update(Long id, AddressDto address) {
        Optional<Address> optionalAddress = this.addressRepository.findById(id);

        if (optionalAddress.isEmpty()) {
            throw new ErrorMessages("Address not found", HttpStatus.NOT_FOUND);
        }

        Address dbAddress = optionalAddress.get();

        dbAddress.setCity(address.getCity());
        dbAddress.setCountry(address.getCountry());
        dbAddress.setState(address.getState());
        dbAddress.setStreet(address.getStreet());
        dbAddress.setZipCode(address.getZipCode());

        dbAddress = this.addressRepository.save(dbAddress);

        return permutAddressToAddressDto(dbAddress);

    }

    @Override
    public String remove(Long id) {
        this.addressRepository.deleteById(id);

        return "Address deleted successfully !";
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
