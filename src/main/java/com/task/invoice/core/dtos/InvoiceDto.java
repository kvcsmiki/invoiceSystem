package com.task.invoice.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class InvoiceDto {

    private int id;
    private String customer;
    private LocalDate postDate;
    private LocalDate deadline;
    private String itemNo;
    private String comment;
    private int price;
}
