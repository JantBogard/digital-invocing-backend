package cm.uni2grow.digitalInvocing.manageCustomer.restController;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cm.uni2grow.digitalInvocing.manageCustomer.metier.CustomerMetier;
import cm.uni2grow.digitalInvocing.manageCustomer.models.dao.Customer;
import cm.uni2grow.digitalInvocing.manageCustomer.models.dto.CustomerDto;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {
    private CustomerMetier customerMetier;

    CustomerRestController(CustomerMetier theCustomerMetier) {
        customerMetier = theCustomerMetier;
    }

    @GetMapping("")
    public List<CustomerDto> getAll() {
        return customerMetier.getAll();
    }

    @GetMapping("/{idCustomer}")
    public CustomerDto getById(@PathVariable Long idCustomer) {
        return customerMetier.getOne(idCustomer);
    }

    @PostMapping("")
    public CustomerDto addCustomer(@RequestBody Customer customer) {
        return customerMetier.save(customer);
    }

    @PutMapping("/{idCustomer}")
    public CustomerDto updateCustomer(@PathVariable Long idCustomer,
            @RequestBody CustomerDto CustomerDto) {
        return customerMetier.update(idCustomer, CustomerDto);
    }

    @DeleteMapping("/{idCustomer}")
    public String deleteCustomer(@PathVariable Long idCustomer) {
        return customerMetier.remove(idCustomer);
    }

}
