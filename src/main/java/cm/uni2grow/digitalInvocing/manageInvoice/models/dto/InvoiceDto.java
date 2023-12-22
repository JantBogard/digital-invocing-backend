package cm.uni2grow.digitalInvocing.manageInvoice.models.dto;

import java.util.List;

import cm.uni2grow.digitalInvocing.manageAddress.model.dao.Address;
import cm.uni2grow.digitalInvocing.manageCustomer.models.dao.Customer;
import cm.uni2grow.digitalInvocing.manageInvoice.models.dao.InvoiceItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {
    private Long id;
    private String invoiceNumber;
    private Customer customer;
    private List<InvoiceItem> items;
    private Address billingAddress;
    private double totalAmount;
}
