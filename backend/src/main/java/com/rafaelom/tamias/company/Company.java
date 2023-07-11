package com.rafaelom.tamias.company;

import com.rafaelom.tamias.address.Address;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Company {
    @Id
    private String id;
    private String companyName;
    private String nif;
    private Address address;
    private Boolean isIssuer;

    public Company(String companyName, String nif, Address address, Boolean isIssuer) {
        this.companyName = companyName;
        this.nif = nif;
        this.address = address;
        this.isIssuer= isIssuer;
    }
}
