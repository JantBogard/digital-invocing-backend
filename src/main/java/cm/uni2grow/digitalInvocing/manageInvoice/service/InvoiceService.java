package cm.uni2grow.digitalInvocing.manageInvoice.service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import cm.uni2grow.digitalInvocing.config.manageError.ErrorMessages;
import cm.uni2grow.digitalInvocing.manageInvoice.metier.InvoiceMetier;
import cm.uni2grow.digitalInvocing.manageInvoice.models.dao.Invoice;
import cm.uni2grow.digitalInvocing.manageInvoice.models.dao.InvoiceItem;
import cm.uni2grow.digitalInvocing.manageInvoice.models.dto.InvoiceDto;
import cm.uni2grow.digitalInvocing.manageInvoice.repository.InvoiceItemRepository;
import cm.uni2grow.digitalInvocing.manageInvoice.repository.InvoiceRepository;

@Service
public class InvoiceService implements InvoiceMetier {
    private InvoiceRepository invoiceRepository;
    private InvoiceItemRepository invoiceItemRepository;

    InvoiceService(InvoiceRepository theInvoiceRepository, InvoiceItemRepository theInvoiceItemRepository) {
        invoiceRepository = theInvoiceRepository;
        invoiceItemRepository = theInvoiceItemRepository;
    }

    @Override
    public InvoiceDto save(Invoice invoice) {
        invoice.setInvoiceNumber(generateRandomString());
        Invoice dbInvoice = invoiceRepository.save(invoice);
        List<InvoiceItem> dbInvoiceItem = new ArrayList<>();

        if (dbInvoice == null) {
            throw new ErrorMessages("We have an error when we try to create this invoice",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } else {

            dbInvoice.getItems().forEach(item -> {
                item.setOwningInvoice(dbInvoice);
                dbInvoiceItem.add(invoiceItemRepository.save(item));
            });

            dbInvoice.setItems(dbInvoiceItem);
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

    public static String generateRandomString() {
        // Définir les caractères autorisés dans la chaîne générée
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // Longueur de la chaîne générée
        int length = 8;

        // Créer un objet StringBuilder pour construire la chaîne
        StringBuilder randomString = new StringBuilder(length);

        // Créer un générateur de nombres aléatoires sécurisé
        SecureRandom random = new SecureRandom();

        // Générer la chaîne de caractères
        for (int i = 0; i < length; i++) {
            // Obtenir un index aléatoire à partir des caractères autorisés
            int randomIndex = random.nextInt(characters.length());

            // Ajouter le caractère correspondant à l'index à la chaîne générée
            randomString.append(characters.charAt(randomIndex));
        }

        // Convertir StringBuilder en String et retourner la chaîne générée
        return randomString.toString();
    }

}
