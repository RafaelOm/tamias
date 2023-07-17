package com.rafaelom.tamias.company;

import com.rafaelom.tamias.invoice.Invoice;
import com.rafaelom.tamias.invoice.InvoiceDTO;
import com.rafaelom.tamias.invoice.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company getCompanyByNif (String nif){
        return companyRepository.findByNif(nif).get();
    }

}
