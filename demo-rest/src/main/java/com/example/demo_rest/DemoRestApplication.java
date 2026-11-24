package com.example.demo_rest;

import com.example.demo_rest.customer.domain.Customer;
import com.example.demo_rest.customer.domain.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoRestApplication implements CommandLineRunner {

	@Autowired
	CustomerRepository custRepo;

	public static void main(String[] args) {
		SpringApplication.run(DemoRestApplication.class, args);
	}

	@Override
	public void run(String... args) {
		long customerCount = custRepo.count();
		if (customerCount == 0) {
			Customer initialCustomer = new Customer("Aaron", "Reed");
			Customer savedCustomer = custRepo.save(initialCustomer);
			System.out.printf("saved customer: %s %s", savedCustomer.getFirstName(), savedCustomer.getLastName());
		}
	}

}
