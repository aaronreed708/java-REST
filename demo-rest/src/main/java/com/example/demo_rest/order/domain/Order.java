package com.example.demo_rest.order.domain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

/**
 * Employee POJO (Plain Old Java Object) class that Hibernate
 * will use to populate "employees" DB table
 */
@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

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

    // Only keep the ID of the customer rather than mapping the whole Customer entity
    @Column(name = "customer_id", nullable = false)
    private UUID customerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status = OrderStatus.PENDING;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount = BigDecimal.ZERO;

    // Composition: An order owns its line items entirely (CascadeType.ALL + orphanRemoval)
    /**
     * Set up association from Order to OrderLineItem entities
     * Cascade persistence (save, update, delete) from Order to OrderLineItem
     * Remove OrderListItems from their table when removed from associated Order
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLineItem> items = new ArrayList<>();

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

    // Helper method to keep bidirectional relationship in sync and update total
    public void addItem(UUID productId, int quantity, BigDecimal price) {
        OrderLineItem item = new OrderLineItem(this, productId, quantity, price);
        this.items.add(item);
        calculateTotal();
    }

    /**
     * Map the OrderLineItem to the subtotal amount and then
     * aggregate that into a sum, starting with ZERO
     */
    private void calculateTotal() {
        this.totalAmount = this.items.stream()
                .map(OrderLineItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Hibernate expects entities to have a no-arg constructor
    public Order() {}
}
