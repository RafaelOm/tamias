package com.rafaelom.tamias.invoice;

import com.rafaelom.tamias.company.CompanyRepository;
import com.rafaelom.tamias.company.CompanyService;
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
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class InvoiceService {
    @Autowired
    private MongoTemplate mongoTemplate;
    private final InvoiceRepository invoiceRepository;
    private final CompanyService companyService;
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

    public Invoice createInvoice(InvoiceDTO invoicedto){
        Long newInvoiceNumber= getLastInvoiceNumber(invoicedto.getIssuerCompanyNif()) + 1;
        Invoice invoice = new Invoice(
                newInvoiceNumber,
                companyService.getCompanyByNif(invoicedto.getIssuerCompanyNif()),
                companyService.getCompanyByNif(invoicedto.getRecipientCompanyNif()),
                invoicedto.getProducts());

        Invoice createdInvoice= invoiceRepository.insert(invoice);
        return createdInvoice;
    }


    private Long getLastInvoiceNumber(String issuerCompanyNif ){
        Query query = new Query();
        query.limit(1).with( Sort.by("invoiceNumber").descending()).addCriteria(Criteria.where("issuerCompany_nif").is(issuerCompanyNif));
        Invoice lastInvoice = mongoTemplate.findOne(query, Invoice.class);
        Long lastInvoiceNumber= 0L;
        if (Objects.nonNull(lastInvoice)) {
           lastInvoiceNumber = lastInvoice.getInvoiceNumber();
        }
        return lastInvoiceNumber;
    }
}
