package com.task.invoice.core.services;

import java.util.Date;

public interface InvoiceService {

    void createInvoice(String customer, Date postDate, Date deadline, String itemNo, String comment, int price);


}
