package cm.uni2grow.digitalInvocing.manageInvoice.metier;

import java.util.List;

import cm.uni2grow.digitalInvocing.manageInvoice.models.dao.Invoice;
import cm.uni2grow.digitalInvocing.manageInvoice.models.dto.InvoiceDto;

public interface InvoiceMetier {
    InvoiceDto save(Invoice invoice);

    List<InvoiceDto> getAll();

    InvoiceDto getOne(Long id);

    InvoiceDto update(Long id, InvoiceDto invoiceDto);

    String remove(Long id);
}
