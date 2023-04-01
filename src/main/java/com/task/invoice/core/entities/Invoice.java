package com.task.invoice.core.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "Invoices")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {

    @Id
    @GeneratedValue
    private Integer id;
    private String customer;
    private LocalDate postDate;
    private LocalDate deadline;
    private String itemNo;
    private String comment;
    private int price;

    public Invoice(String customer, LocalDate postDate, LocalDate deadline, String itemNo, String comment, int price) {
        this.customer = customer;
        this.postDate = postDate;
        this.deadline = deadline;
        this.itemNo = itemNo;
        this.comment = comment;
        this.price = price;
    }
}
