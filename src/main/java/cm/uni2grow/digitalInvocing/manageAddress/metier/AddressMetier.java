package cm.uni2grow.digitalInvocing.manageAddress.metier;

import java.util.List;

import cm.uni2grow.digitalInvocing.manageAddress.model.dao.Address;
import cm.uni2grow.digitalInvocing.manageAddress.model.dto.AddressDto;

public interface AddressMetier {
    AddressDto save(Address address);

    List<AddressDto> getAll();

    AddressDto getOne(Long id);

    AddressDto update(Long id, AddressDto address);

    String remove(Long id);
}
