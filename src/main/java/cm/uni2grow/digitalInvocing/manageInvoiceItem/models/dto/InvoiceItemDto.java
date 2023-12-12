package cm.uni2grow.digitalInvocing.manageInvoiceItem.models.dto;

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
}
