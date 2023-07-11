package com.rafaelom.tamias.address;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {
    private String street;
    private String number;
    private String country;
    private String city;
    private String zipCode;

}
