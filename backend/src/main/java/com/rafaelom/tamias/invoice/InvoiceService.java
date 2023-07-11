package com.rafaelom.tamias.invoice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    public List<Invoice> getAllInvoices(){
        return invoiceRepository.findAll();
    }
}
