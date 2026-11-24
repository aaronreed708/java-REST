package com.example.demo_rest.order.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * An item that appears in an associated order.  It is a
 * snapshot of the order item at the time of the order (e.g.
 * price may be different in a different order)
 */
@Entity
@Table(name = "order_line_items")
@Getter
@Setter
@NoArgsConstructor
public class OrderLineItem {

    /**
     * Automatically generated ID for the record
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Order that this line item is associated with
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    /**
     * Product id.  Assigned, not generated.
     */
    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal priceAtPurchase;

    public OrderLineItem(Order order, UUID productId, Integer quantity, BigDecimal priceAtPurchase) {
        this.order = order;
        this.productId = productId;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
    }

    /**
     * @return The cost for all items of this type in this order
     */
    public BigDecimal getSubtotal() {
        return priceAtPurchase.multiply(BigDecimal.valueOf(quantity));
    }
}

