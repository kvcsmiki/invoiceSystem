package com.task.invoice.core.repositories;

import com.task.invoice.core.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    Optional<Invoice> findByCustomerAndPostDateAndDeadlineAndItemNoAndCommentAndPrice(String customer,
                                                                                      LocalDate postDate,
                                                                                      LocalDate deadline,
                                                                                      String itemNo, String comment,
                                                                                      int price);

    Optional<Invoice> findById(int id);
}
