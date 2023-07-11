package com.rafaelom.tamias.invoice;

import com.rafaelom.tamias.company.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface InvoiceRepository extends MongoRepository<Invoice, String> {

    Optional<Invoice> findByInvoiceNumber (String invoiceNumber);
}
