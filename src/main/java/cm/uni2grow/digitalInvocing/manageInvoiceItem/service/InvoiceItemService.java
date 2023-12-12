package cm.uni2grow.digitalInvocing.manageInvoiceItem.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import cm.uni2grow.digitalInvocing.config.manageError.ErrorMessages;
import cm.uni2grow.digitalInvocing.manageInvoiceItem.metier.InvoiceItemMetier;
import cm.uni2grow.digitalInvocing.manageInvoiceItem.models.dao.InvoiceItem;
import cm.uni2grow.digitalInvocing.manageInvoiceItem.models.dto.InvoiceItemDto;
import cm.uni2grow.digitalInvocing.manageInvoiceItem.repository.InvoiceItemRepository;

@Service
public class InvoiceItemService implements InvoiceItemMetier {

    private InvoiceItemRepository invoiceItemRepository;

    InvoiceItemService(InvoiceItemRepository theInvoiceItemRepository) {
        invoiceItemRepository = theInvoiceItemRepository;
    }

    @Override
    public InvoiceItemDto save(InvoiceItem invoiceItem) {
        InvoiceItem dbInvoiceItem = invoiceItemRepository.save(invoiceItem);

        if (dbInvoiceItem == null) {
            throw new ErrorMessages("We have an error when we try to create this invoiceItem",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return permutInvoiceItemToInvoiceItmeDto(dbInvoiceItem);
    }

    @Override
    public List<InvoiceItemDto> getAll() {
        return permutListInvoiceItemsToListInvoiceItemDtos(invoiceItemRepository.findAll());
    }

    @Override
    public InvoiceItemDto getOne(Long id) {
        Optional<InvoiceItem> optionalInvoiceItem = invoiceItemRepository.findById(id);

        if (optionalInvoiceItem.isEmpty()) {
            throw new ErrorMessages("InvoiceItem not found", HttpStatus.NOT_FOUND);
        }

        return permutInvoiceItemToInvoiceItmeDto(optionalInvoiceItem.get());
    }

    @Override
    public InvoiceItemDto update(Long id, InvoiceItemDto invoiceItemDto) {
        Optional<InvoiceItem> optionalInvoiceItem = invoiceItemRepository.findById(id);

        if (optionalInvoiceItem.isEmpty()) {
            throw new ErrorMessages("InvoiceItem not found", HttpStatus.NOT_FOUND);
        }

        InvoiceItem dbInvoiceItem = optionalInvoiceItem.get();

        BeanUtils.copyProperties(invoiceItemDto, dbInvoiceItem);
        dbInvoiceItem = invoiceItemRepository.save(dbInvoiceItem);

        return permutInvoiceItemToInvoiceItmeDto(dbInvoiceItem);
    }

    @Override
    public String remove(Long id) {
        invoiceItemRepository.deleteById(id);

        return "InvoiceItem deleted successfully !";
    }

    static InvoiceItemDto permutInvoiceItemToInvoiceItmeDto(InvoiceItem invoiceItem) {
        InvoiceItemDto invoiceItemDto = new InvoiceItemDto();

        BeanUtils.copyProperties(invoiceItem, invoiceItemDto);
        return invoiceItemDto;
    }

    static List<InvoiceItemDto> permutListInvoiceItemsToListInvoiceItemDtos(List<InvoiceItem> invoiceItems) {
        return invoiceItems.stream().map(invoiceItem -> permutInvoiceItemToInvoiceItmeDto(invoiceItem))
                .collect(Collectors.toList());
    }

}
