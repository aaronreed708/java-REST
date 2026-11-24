package com.example.demo_rest.customer.domain;

import com.example.demo_rest.order.domain.OrderLineItem;
import com.example.demo_rest.order.domain.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Employee POJO (Plain Old Java Object) class that Hibernate
 * will use to populate "employees" DB table
 */
@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer {

    /**
     * '@Id' designated field will be the primary key of each
     * entity in the table.
     *
     * '@GeneratedValue' designates that it is up to the DB
     * to generate the id value
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name", nullable = false)
    private String firstName = "";

    @Column(name = "last_name", nullable = false)
    private String lastName = "";

    // Auditing fields
    /**
     * Keep track of when order was created.  Value determined
     * automatically when Order instantiated.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    /**
     * Keep track of when order was created
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Hibernate expects entities to have a no-arg constructor
    protected Customer () {};

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
