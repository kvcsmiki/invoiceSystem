package com.task.invoice.core.services;

import com.task.invoice.core.dtos.InvoiceDto;
import com.task.invoice.core.entities.Invoice;
import com.task.invoice.core.repositories.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Override
    public void createInvoice(String customer, LocalDate postDate, LocalDate deadline, String itemNo, String comment, int price) {
        Invoice invoice = Invoice.builder()
                .customer(customer)
                .postDate(postDate)
                .deadline(deadline)
                .itemNo(itemNo)
                .comment(comment)
                .price(price)
                .build();
        invoiceRepository.save(invoice);
    }

    @Override
    public List<InvoiceDto> getAllInvoices(){
        return invoiceRepository.findAll()
                .stream()
                .map(this::convertInvoice)
                .toList();
    }

    @Override
    public Optional<InvoiceDto> getInvoiceById(int id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        if(invoice.isEmpty())
            return Optional.empty();
        return Optional.of(convertInvoice(invoice.get()));
    }

    @Override
    public Optional<InvoiceDto> getInvoice(String customer, LocalDate postDate, LocalDate deadline, String itemNo, String comment, int price) {
        Optional<Invoice> invoice = invoiceRepository.findByCustomerAndPostDateAndDeadlineAndItemNoAndCommentAndPrice(customer,postDate,deadline,itemNo,comment,price);
        if(invoice.isEmpty())
            return Optional.empty();
        return Optional.of(convertInvoice(invoice.get()));
    }

    private InvoiceDto convertInvoice(Invoice invoice){
        return InvoiceDto.builder()
                .id(invoice.getId())
                .customer(invoice.getCustomer())
                .postDate(invoice.getPostDate())
                .deadline(invoice.getDeadline())
                .itemNo(invoice.getItemNo())
                .comment(invoice.getComment())
                .price(invoice.getPrice())
                .build();
    }
}
