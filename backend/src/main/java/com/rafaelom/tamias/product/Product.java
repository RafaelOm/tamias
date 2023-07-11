package com.rafaelom.tamias.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Product {
    private String name;
    private Integer quantity;
    private Double unitaryPriceNoFee;
    private Double unitaryPriceWithFee;
    private Double feePercentage;
    private Double totalPriceNoFee;
    private Double appliedFee;
    private Double totalPriceWithFee;
    //PersistanceConstructor is added to retrieve the object invoice that contains a product correctly
    //This is the constructor that creates the object from mongodb
    @PersistenceConstructor
    public Product(String name, Integer quantity, Double unitaryPriceNoFee, Double unitaryPriceWithFee, Double feePercentage, Double totalPriceNoFee, Double appliedFee, Double totalPriceWithFee) {
        this.name = name;
        this.quantity = quantity;
        this.unitaryPriceNoFee = unitaryPriceNoFee;
        this.unitaryPriceWithFee = unitaryPriceWithFee;
        this.feePercentage = feePercentage;
        this.totalPriceNoFee = totalPriceNoFee;
        this.appliedFee = appliedFee;
        this.totalPriceWithFee = totalPriceWithFee;
    }

    public Product(String name, Integer quantity, Double unitaryPriceNoFee, Double feePercentage) {
        this.name = name;
        this.quantity = quantity;
        this.unitaryPriceNoFee = unitaryPriceNoFee;
        this.feePercentage = feePercentage;
        setAppliedFee();
        setUnitaryPriceWithFee();
        setTotalPriceWithFee();
        setTotalPriceNoFee();
    }

    private void setUnitaryPriceWithFee() {
        this.unitaryPriceWithFee = this.unitaryPriceNoFee + this.appliedFee;
    }
    private void setAppliedFee (){
        this.appliedFee=this.unitaryPriceNoFee * (this.feePercentage / 100);

    }
    private void setTotalPriceWithFee(){
       this.totalPriceWithFee=this.unitaryPriceWithFee * quantity;
    }
    private void setTotalPriceNoFee(){
        this.totalPriceNoFee=this.unitaryPriceNoFee * quantity;
    }
}
