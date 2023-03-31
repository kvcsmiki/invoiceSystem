package com.task.invoice.core.services;

import com.task.invoice.core.dtos.InvoiceDto;
import com.task.invoice.core.entities.Invoice;
import com.task.invoice.core.repositories.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Override
    public void createInvoice(String customer, Date postDate, Date deadline, String itemNo, String comment, int price) {
        Invoice invoice = Invoice.builder()
                .customer(customer)
                .postDate(postDate)
                .deadline(deadline)
                .itemNo(itemNo)
                .comment(comment)
                .build();
        invoiceRepository.save(invoice);
    }
}
