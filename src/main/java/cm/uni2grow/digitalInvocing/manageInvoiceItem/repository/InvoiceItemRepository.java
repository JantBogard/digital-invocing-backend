package cm.uni2grow.digitalInvocing.manageInvoiceItem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cm.uni2grow.digitalInvocing.manageInvoiceItem.models.dao.InvoiceItem;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {

}
