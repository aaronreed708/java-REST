package com.example.demo_rest.order.domain;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Get the current list of orders, regardless of status
     * @return an iterable of the orders
     */
    @GetMapping("/orders")
    public Iterable<Order> findAllOrders() {
        return this.orderRepository.findAll();
    }

    /**
     * Store the given order as a new entry in the Orders DB
     * @param order The order to store
     * @return the newly created order
     */
    @PostMapping("/orders")
    public Order addOneOrder(@RequestBody Order order) {
        return this.orderRepository.save(order);
    }
}
