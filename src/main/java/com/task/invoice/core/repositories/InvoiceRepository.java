package com.task.invoice.core.repositories;

import com.task.invoice.core.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    @Query(value = "SELECT i FROM Invoices i WHERE i.customer = ?1 AND postDate = ?2 " +
            "AND deadline = ?3 AND itemNo = ?4 AND comment = ?5 AND price = ?6", nativeQuery = true)
    Optional<Invoice> findInvoice(String customer, Date postDate, Date deadline,
                                       String itemNo, String comment, int price);
}
