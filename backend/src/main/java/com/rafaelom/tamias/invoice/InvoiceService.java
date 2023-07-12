package com.rafaelom.tamias.invoice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    public List<Invoice> getAllInvoices(){
        return invoiceRepository.findAll();
    }
    public List<Invoice> getAllInvoicesByIssuerCompanyNif(String nif){
        List<Invoice> invoicesByNif= invoiceRepository.findAllByIssuerCompany_nif(nif).get();

        return invoicesByNif;
    }

    public List<Invoice> getAllInvoicesByRecipientCompanyNif(String nif){
        List<Invoice> invoicesByNif= invoiceRepository.findAllByRecipientCompany_nif(nif).get();

        return invoicesByNif;
    }
    public Invoice getByInvoiceNumber(Long invoiceNumber){
        Optional<Invoice> invoice = invoiceRepository.findByInvoiceNumber(invoiceNumber);
        return invoice.isPresent() ? invoice.get() : null ;
    }

}
