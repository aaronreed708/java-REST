package com.example.demo_rest.order.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

//public interface OrderRepository extends CrudRepository<Order, Integer> {
//}
public interface OrderRepository extends CrudRepository<Order, UUID> {
}
