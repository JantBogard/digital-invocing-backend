package cm.uni2grow.digitalInvocing.address.metier;

import java.util.List;

import cm.uni2grow.digitalInvocing.address.model.dao.Address;
import cm.uni2grow.digitalInvocing.address.model.dto.AddressDto;

public interface AddressMetier {
    AddressDto save(Address address);

    List<AddressDto> getAllAddress();

    AddressDto getAddress(Long id);

    AddressDto update(Long id, AddressDto address);
}
