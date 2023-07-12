package com.rafaelom.tamias.invoice;

import com.rafaelom.tamias.exception.ApiRequestException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/invoice")
@AllArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;
    @GetMapping
    public List<Invoice> getAllInvoices(){
        //throw new ApiRequestException("Error", HttpStatus.OK);
        return invoiceService.getAllInvoices();
    }
    @GetMapping("/{invoiceNumber}")
    public Invoice getByInvoiceNumber(@PathVariable Long invoiceNumber){
        Invoice invoice = invoiceService.getByInvoiceNumber(invoiceNumber);
        if (Objects.isNull(invoice)) {
            throw new ApiRequestException(String.format("The invoice %s does not exists",invoiceNumber), HttpStatus.NOT_FOUND);
        }else{
            return invoice;
        }
    }
    @GetMapping("byIssuerCompanyNif/{nif}")
    public List<Invoice> getAllInvoicesByIssuerCompanyNif(@PathVariable  String nif){
        return invoiceService.getAllInvoicesByIssuerCompanyNif(nif);
    }
    @GetMapping("byRecipientCompanyNif/{nif}")
    public List<Invoice> getAllInvoicesByRecipientCompany(@PathVariable  String nif){
        return invoiceService.getAllInvoicesByRecipientCompanyNif(nif);
    }

}
