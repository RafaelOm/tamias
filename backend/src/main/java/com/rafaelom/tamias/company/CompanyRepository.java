package com.rafaelom.tamias.company;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends MongoRepository<Company, String> {
    Optional<List<Company>> findByCompanyName (String companyName);
    Optional<Company> findByNif (String nif);


}
