package com.rafaelom.tamias;

import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoWriteException;
import com.rafaelom.tamias.address.Address;
import com.rafaelom.tamias.company.Company;
import com.rafaelom.tamias.company.CompanyRepository;
import com.rafaelom.tamias.invoice.Invoice;
import com.rafaelom.tamias.invoice.InvoiceRepository;
import com.rafaelom.tamias.product.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TamiasInvoiceGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TamiasInvoiceGeneratorApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(InvoiceRepository repository){
		return args -> {
			Company company = new Company(
					"Empresa de malaga",
					"1111111",
						new Address("paco de la torre",
								"15 bajo",
								"spain",
								"malaga",
								"29001"),
					true
			);
			Company company2 = new Company(
					"Empresa de sevilla",
					"1111111",
					new Address("cuevas bajas",
							"15 bajo",
							"spain",
							"sevilla",
							"28001"),
					true
			);
			Product p= new Product("Seguro de vida",1,50.0, 21.0);

			List<Product> products = new ArrayList<>();
			products.add(p);
			Invoice invoice = new Invoice(2L,
									company,
									company2,
									products
								);
			try{
				repository.insert(invoice);
			}catch (org.springframework.dao.DuplicateKeyException e){
				System.out.printf("Error while writing in mongoDB %s", e.getMessage());
			}

		};
	}

}
