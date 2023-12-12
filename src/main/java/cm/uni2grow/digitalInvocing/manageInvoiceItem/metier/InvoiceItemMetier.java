package cm.uni2grow.digitalInvocing.manageInvoiceItem.metier;

import java.util.List;

import cm.uni2grow.digitalInvocing.manageInvoiceItem.models.dao.InvoiceItem;
import cm.uni2grow.digitalInvocing.manageInvoiceItem.models.dto.InvoiceItemDto;

public interface InvoiceItemMetier {
    InvoiceItemDto save(InvoiceItem invoiceItem);

    List<InvoiceItemDto> getAll();

    InvoiceItemDto getOne(Long id);

    InvoiceItemDto update(Long id, InvoiceItemDto invoiceItemDto);

    String remove(Long id);
}
