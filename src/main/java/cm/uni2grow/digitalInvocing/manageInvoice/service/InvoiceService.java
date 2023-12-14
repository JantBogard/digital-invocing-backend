package cm.uni2grow.digitalInvocing.manageInvoice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import cm.uni2grow.digitalInvocing.config.manageError.ErrorMessages;
import cm.uni2grow.digitalInvocing.manageInvoice.metier.InvoiceMetier;
import cm.uni2grow.digitalInvocing.manageInvoice.models.dao.Invoice;
import cm.uni2grow.digitalInvocing.manageInvoice.models.dto.InvoiceDto;
import cm.uni2grow.digitalInvocing.manageInvoice.repository.InvoiceRepository;

@Service
public class InvoiceService implements InvoiceMetier {
    private InvoiceRepository invoiceRepository;

    InvoiceService(InvoiceRepository theInvoiceRepository) {
        invoiceRepository = theInvoiceRepository;
    }

    @Override
    public InvoiceDto save(Invoice invoice) {
        Invoice dbInvoice = invoiceRepository.save(invoice);

        if (dbInvoice == null) {
            throw new ErrorMessages("We have an error when we try to create this invoice",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return permutInvoiceToInvoiceDto(dbInvoice);
    }

    @Override
    public List<InvoiceDto> getAll() {
        return permutListInvcesToListInvoiceDtos(invoiceRepository.findAll());
    }

    @Override
    public InvoiceDto getOne(Long id) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);

        if (optionalInvoice.isEmpty()) {
            throw new ErrorMessages("Invoice not found", HttpStatus.NOT_FOUND);
        }

        return permutInvoiceToInvoiceDto(optionalInvoice.get());
    }

    @Override
    public InvoiceDto update(Long id, InvoiceDto invoiceDto) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);

        if (optionalInvoice.isEmpty()) {
            throw new ErrorMessages("Invoice not found", HttpStatus.NOT_FOUND);
        }

        Invoice dbInvoice = optionalInvoice.get();

        BeanUtils.copyProperties(invoiceDto, dbInvoice);
        dbInvoice = invoiceRepository.save(dbInvoice);

        return permutInvoiceToInvoiceDto(dbInvoice);
    }

    @Override
    public String remove(Long id) {
        invoiceRepository.deleteById(id);

        return "Invoice deleted successfully !";
    }

    public static InvoiceDto permutInvoiceToInvoiceDto(Invoice invoice) {
        InvoiceDto invoiceDto = new InvoiceDto();

        BeanUtils.copyProperties(invoice, invoiceDto);
        return invoiceDto;
    }

    public static List<InvoiceDto> permutListInvcesToListInvoiceDtos(List<Invoice> invoices) {
        return invoices.stream().map(invoice -> permutInvoiceToInvoiceDto(invoice)).collect(Collectors.toList());
    }

}
