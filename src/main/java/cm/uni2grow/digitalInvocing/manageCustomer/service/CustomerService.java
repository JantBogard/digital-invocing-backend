package cm.uni2grow.digitalInvocing.manageCustomer.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import cm.uni2grow.digitalInvocing.config.manageError.ErrorMessages;
import cm.uni2grow.digitalInvocing.manageCustomer.metier.CustomerMetier;
import cm.uni2grow.digitalInvocing.manageCustomer.models.dao.Customer;
import cm.uni2grow.digitalInvocing.manageCustomer.models.dto.CustomerDto;
import cm.uni2grow.digitalInvocing.manageCustomer.repository.CustomerRepository;

@Service
public class CustomerService implements CustomerMetier {
    private CustomerRepository customerRepository;

    CustomerService(CustomerRepository theCustomerRepository) {
        customerRepository = theCustomerRepository;
    }

    @Override
    public CustomerDto save(Customer customer) {
        Customer dbCustomer = customerRepository.save(customer);

        if (dbCustomer == null) {
            throw new ErrorMessages("We have an error when we try to create this customer",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return permutCustomerToCustomerDto(dbCustomer);
    }

    @Override
    public List<CustomerDto> getAll() {
        return permutListCustomersToListCustomerDtos(customerRepository.findAll());
    }

    @Override
    public CustomerDto getOne(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isEmpty()) {
            throw new ErrorMessages("Customer not found", HttpStatus.NOT_FOUND);
        }

        return permutCustomerToCustomerDto(optionalCustomer.get());
    }

    @Override
    public CustomerDto update(Long id, CustomerDto customerDto) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isEmpty()) {
            throw new ErrorMessages("InvoiceItem not found", HttpStatus.NOT_FOUND);
        }

        Customer dbCustomer = optionalCustomer.get();

        dbCustomer.setName(customerDto.getName());
        dbCustomer.setEmail(customerDto.getEmail());
        dbCustomer.setAddress(customerDto.getAddress());
        dbCustomer.setPhone(customerDto.getPhone());

        dbCustomer = customerRepository.save(dbCustomer);

        return permutCustomerToCustomerDto(dbCustomer);
    }

    @Override
    public String remove(Long id) {
        customerRepository.deleteById(id);

        return "Customer deleted successfully !";
    }

    public static CustomerDto permutCustomerToCustomerDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();

        BeanUtils.copyProperties(customer, customerDto);
        return customerDto;
    }

    public static List<CustomerDto> permutListCustomersToListCustomerDtos(List<Customer> customers) {
        return customers.stream().map(customer -> permutCustomerToCustomerDto(customer)).collect(Collectors.toList());
    }

}
