package cm.uni2grow.digitalInvocing.manageInvoiceItem.models.dao;

import cm.uni2grow.digitalInvocing.manageInvoice.models.dao.Invoice;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InvoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private int quantite;

    private double price;

    private double total;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice owningInvoice;
}
