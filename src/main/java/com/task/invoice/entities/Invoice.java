package com.task.invoice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "Invoices")
public class Invoice {

    @Id
    @GeneratedValue
    private Integer id;
    private String customer;
    private Date postDate;
    private Date deadline;
    private String itemNo;
    private String comment;
    private Integer price;

    public Invoice(String customer, Date postDate, Date deadline, String itemNo, String comment, Integer price) {
        this.customer = customer;
        this.postDate = postDate;
        this.deadline = deadline;
        this.itemNo = itemNo;
        this.comment = comment;
        this.price = price;
    }
}
