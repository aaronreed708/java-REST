package com.example.demo_rest.customer.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

//public interface OrderRepository extends CrudRepository<Order, Integer> {
//}
public interface CustomerRepository extends CrudRepository<Customer, UUID> {
}
