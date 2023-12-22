package cm.uni2grow.digitalInvocing.manageInvoice.models.dto;

import cm.uni2grow.digitalInvocing.manageInvoice.models.dao.Invoice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceItemDto {
    private Long id;
    private int quantity;
    private double price;
    private double total;
    private Invoice owningInvoice;
}
