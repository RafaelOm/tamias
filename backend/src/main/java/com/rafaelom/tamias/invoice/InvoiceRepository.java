package com.rafaelom.tamias.invoice;

import com.rafaelom.tamias.company.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends MongoRepository<Invoice, String> {

    Optional<Invoice> findByInvoiceNumber (Long invoiceNumber);
    Optional<List<Invoice>> findAllByIssuerCompany_nif(String nif);
    Optional<List<Invoice>> findAllByRecipientCompany_nif(String nif);


}
