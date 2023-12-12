package cm.uni2grow.digitalInvocing.manageCustomer.metier;

import java.util.List;

import cm.uni2grow.digitalInvocing.manageCustomer.models.dao.Customer;
import cm.uni2grow.digitalInvocing.manageCustomer.models.dto.CustomerDto;

public interface CustomerMetier {
    CustomerDto save(Customer customer);

    List<CustomerDto> getAll();

    CustomerDto getOne(Long id);

    CustomerDto update(Long id, CustomerDto customerDto);

    String remove(Long id);
}
