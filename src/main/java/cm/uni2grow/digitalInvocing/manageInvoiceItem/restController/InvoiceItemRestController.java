package cm.uni2grow.digitalInvocing.manageInvoiceItem.restController;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cm.uni2grow.digitalInvocing.manageInvoiceItem.metier.InvoiceItemMetier;
import cm.uni2grow.digitalInvocing.manageInvoiceItem.models.dao.InvoiceItem;
import cm.uni2grow.digitalInvocing.manageInvoiceItem.models.dto.InvoiceItemDto;

@RestController
@RequestMapping("/InvoiceItem")
public class InvoiceItemRestController {
    private InvoiceItemMetier invoiceItemMetier;

    InvoiceItemRestController(InvoiceItemMetier theInvoiceItemMetier) {
        invoiceItemMetier = theInvoiceItemMetier;
    }

    @GetMapping("/")
    public List<InvoiceItemDto> getAll() {
        return invoiceItemMetier.getAll();
    }

    @GetMapping("/{idInvoiceItem}")
    public InvoiceItemDto getById(@PathVariable Long idInvoiceItem) {
        return invoiceItemMetier.getOne(idInvoiceItem);
    }

    @PostMapping("/")
    public InvoiceItemDto addInvoiceItem(@RequestBody InvoiceItem invoiceItem) {
        return invoiceItemMetier.save(invoiceItem);
    }

    @PutMapping("/{idInvoiceItem}")
    public InvoiceItemDto updateInvoiceItem(@PathVariable Long idInvoiceItem,
            @RequestBody InvoiceItemDto InvoiceItemDto) {
        return invoiceItemMetier.update(idInvoiceItem, InvoiceItemDto);
    }

    @DeleteMapping("/{idInvoiceItem}")
    public String deleteInvoiceItem(@PathVariable Long idInvoiceItem) {
        return invoiceItemMetier.remove(idInvoiceItem);
    }
}
