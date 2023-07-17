package com.rafaelom.tamias.invoice;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rafaelom.tamias.company.Company;
import com.rafaelom.tamias.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class InvoiceDTO {

    private String issuerCompanyNif;
    private String recipientCompanyNif;
    private List<Product> products;
    private LocalDateTime createdDate;
    private Double totalPriceNoFee;
    private Double totalPriceWithFee;

    @JsonCreator
    public InvoiceDTO(@JsonProperty("issuerCompanyNif") String issuerCompanyNif, @JsonProperty("recipientCompanyNif") String recipientCompanyNif, @JsonProperty("products") List<Product> products) {
        this.issuerCompanyNif = issuerCompanyNif;
        this.recipientCompanyNif = recipientCompanyNif;
        this.products = products;
    }
}
