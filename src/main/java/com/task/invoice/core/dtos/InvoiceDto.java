package com.task.invoice.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class InvoiceDto {

    private String customer;
    private Date postDate;
    private Date deadline;
    private String itemNo;
    private String comment;
    private int price;
}