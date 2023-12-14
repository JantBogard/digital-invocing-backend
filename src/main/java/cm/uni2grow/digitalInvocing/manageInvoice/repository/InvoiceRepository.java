package cm.uni2grow.digitalInvocing.manageInvoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cm.uni2grow.digitalInvocing.manageInvoice.models.dao.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
