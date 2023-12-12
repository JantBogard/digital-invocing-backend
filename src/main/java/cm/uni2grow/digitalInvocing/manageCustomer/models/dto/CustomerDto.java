package cm.uni2grow.digitalInvocing.manageCustomer.models.dto;

import cm.uni2grow.digitalInvocing.manageAddress.model.dao.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Address address;
}
