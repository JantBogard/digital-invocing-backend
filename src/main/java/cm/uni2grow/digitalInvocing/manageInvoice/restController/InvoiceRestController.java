package cm.uni2grow.digitalInvocing.manageInvoice.restController;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cm.uni2grow.digitalInvocing.manageInvoice.metier.InvoiceMetier;
import cm.uni2grow.digitalInvocing.manageInvoice.models.dao.Invoice;
import cm.uni2grow.digitalInvocing.manageInvoice.models.dto.InvoiceDto;

@RestController
@RequestMapping("/invoice")
public class InvoiceRestController {
    private InvoiceMetier invoiceMetier;

    InvoiceRestController(InvoiceMetier theInvoiceMetier) {
        invoiceMetier = theInvoiceMetier;
    }

    @GetMapping("/")
    public List<InvoiceDto> getAll() {
        return invoiceMetier.getAll();
    }

    @GetMapping("/{idInvoice}")
    public InvoiceDto getById(@PathVariable Long idInvoice) {
        return invoiceMetier.getOne(idInvoice);
    }

    @PostMapping("/")
    public InvoiceDto addInvoice(@RequestBody Invoice invoice) {
        return invoiceMetier.save(invoice);
    }

    @PutMapping("/{idInvoice}")
    public InvoiceDto updateInvoice(@PathVariable Long idInvoice,
            @RequestBody InvoiceDto InvoiceDto) {
        return invoiceMetier.update(idInvoice, InvoiceDto);
    }

    @DeleteMapping("/{idInvoice}")
    public String deleteInvoice(@PathVariable Long idInvoice) {
        return invoiceMetier.remove(idInvoice);
    }
}
