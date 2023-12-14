package cm.uni2grow.digitalInvocing.manageAddress.model.dto;

import cm.uni2grow.digitalInvocing.manageCustomer.models.dao.Customer;
import cm.uni2grow.digitalInvocing.manageInvoice.models.dao.Invoice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private Invoice invoice;
    private Customer customer;
}
