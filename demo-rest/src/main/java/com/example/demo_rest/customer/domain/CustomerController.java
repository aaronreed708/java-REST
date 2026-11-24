package com.example.demo_rest.customer.domain;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Get the current list of customers, regardless of status
     * @return an iterable of the customers
     */
    @GetMapping("/customers")
    public Iterable<Customer> findAllCustomers() {
        return this.customerRepository.findAll();
    }

    /**
     * Store the given customer as a new entry in the Customers DB
     * @param customer The customer to store
     * @return the newly created customer
     */
    @PostMapping("/customers")
    public Customer addOneCustomer(@RequestBody Customer customer) {
        return this.customerRepository.save(customer);
    }
}
