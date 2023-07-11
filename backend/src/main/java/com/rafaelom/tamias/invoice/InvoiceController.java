package com.rafaelom.tamias.invoice;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/invoice")
@AllArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;
    @GetMapping
    public List<Invoice> getAllInvoices(){
        return invoiceService.getAllInvoices();
    }
}
