package com.task.invoice.core.services;

import com.task.invoice.core.dtos.InvoiceDto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface InvoiceService {

    void createInvoice(String customer, LocalDate postDate, LocalDate deadline, String itemNo, String comment, int price);

    List<InvoiceDto> getAllInvoices();

    Optional<InvoiceDto> getInvoiceById(int id);

    Optional<InvoiceDto> getInvoice(String customer, LocalDate postDate, LocalDate deadline,
                                    String itemNo, String comment, int price);
}
