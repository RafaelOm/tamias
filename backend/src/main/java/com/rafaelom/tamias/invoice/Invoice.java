package com.rafaelom.tamias.invoice;

import com.rafaelom.tamias.company.Company;
import com.rafaelom.tamias.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Document
@CompoundIndex(def = "{'invoiceNumber': 1, 'issuerCompany': 1}", unique = true)
public class Invoice {
    @Id
    private String id;
    @Indexed
    private Long invoiceNumber;
    private Company issuerCompany;
    private Company recipientCompany;
    private List<Product> products;
    private LocalDateTime createdDate;
    private Double totalPriceNoFee;
    private Double totalPriceWithFee;

    public Invoice(Long invoiceNumber, Company issuerCompany, Company recipientCompany, List<Product> products) {
        this.invoiceNumber = invoiceNumber;
        this.issuerCompany = issuerCompany;
        this.recipientCompany = recipientCompany;
        this.products = products;
        this.createdDate= LocalDateTime.now();
        calculateTotalPriceNoFee();
        calculateTotalPriceWithFee();
    }

    private void calculateTotalPriceNoFee(){
        Double totalPrice=0.0;
        for(Product p: this.products ){
            totalPrice+= p.getTotalPriceNoFee();
        }
        this.totalPriceNoFee=totalPrice;
    }
    private void calculateTotalPriceWithFee(){
        Double totalPrice=0.0;
        for(Product p: this.products ){
            totalPrice+= p.getTotalPriceWithFee();
        }
        this.totalPriceWithFee=totalPrice;
    }
}
