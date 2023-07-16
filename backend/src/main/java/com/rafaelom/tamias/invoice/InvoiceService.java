package com.rafaelom.tamias.invoice;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class InvoiceService {
    @Autowired
    private MongoTemplate mongoTemplate;
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

    public Invoice createInvoice(Invoice invoice){
        Long newInvoiceNumber= getLastInvoiceNumber(invoice) + 1;
        invoice.setInvoiceNumber(newInvoiceNumber);
        Invoice createdInvoice= invoiceRepository.insert(invoice);
        return createdInvoice;
    }


    private Long getLastInvoiceNumber(Invoice newInvoice){
        Query query = new Query();
        query.limit(1).with( Sort.by("invoiceNumber").descending()).addCriteria(Criteria.where("issuerCompany_nif").is(newInvoice.getIssuerCompany().getNif()));
        Invoice lastInvoice = mongoTemplate.findOne(query, Invoice.class);
        Long lastInvoiceNumber = lastInvoice.getInvoiceNumber();
        return lastInvoiceNumber;
    }
}
