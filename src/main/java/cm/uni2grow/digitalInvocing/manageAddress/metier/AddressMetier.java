package cm.uni2grow.digitalInvocing.manageAddress.metier;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springdoc.core.converters.models.Pageable;

import cm.uni2grow.digitalInvocing.manageAddress.model.dao.Address;
import cm.uni2grow.digitalInvocing.manageAddress.model.dto.AddressDto;

public interface AddressMetier {
    AddressDto save(Address address);

    List<AddressDto> getAll();

    // Page<Address> getAllPageable(Pageable pageable);

    AddressDto getOne(Long id);

    AddressDto update(Long id, AddressDto address);

    String remove(Long id);
}
