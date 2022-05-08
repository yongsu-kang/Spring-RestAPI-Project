package org.prgms.rest_api.order.model;

import org.prgms.rest_api.product.model.Category;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

public class OrderItem {
    private final Long orderItemId;
    private Long orderId;
    @NotNull
    private Long productId;
    private Category category;
    @PositiveOrZero
    private Long price;
    @Positive
    private int quantity;
    private final LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private OrderItem(OrderItemBuilder builder) {
        checkPrice(builder.price);
        checkQuantity(builder.quantity);

        this.orderItemId = builder.orderItemId;
        this.orderId = builder.orderId;
        this.productId = builder.productId;
        this.category = builder.category;
        this.price = builder.price;
        this.quantity = builder.quantity;
        this.createdAt = builder.createdAt;
        this.modifiedAt = builder.modifiedAt;
    }

    private void checkQuantity(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException();
    }

    private void checkPrice(Long price) {
        if (price < 0) throw new IllegalArgumentException();
    }

    public Long getProductId() {
        return productId;
    }

    public Category getCategory() {
        return category;
    }

    public Long getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public static class OrderItemBuilder{
        private final Long orderItemId;
        private Long orderId;
        private Long productId;
        private Category category;
        private Long price;
        private int quantity;
        private final LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public OrderItemBuilder(Long orderItemId, LocalDateTime createdAt) {
            this.orderItemId = orderItemId;
            this.createdAt = createdAt;
        }

        public OrderItemBuilder orderId(Long orderId) {
            this.orderId = orderId;
            return this;
        }

        public OrderItemBuilder productId(Long productId) {
            this.productId = productId;
            return this;
        }

        public OrderItemBuilder category(Category category) {
            this.category = category;
            return this;
        }

        public OrderItemBuilder price(Long price) {
            this.price = price;
            return this;
        }

        public OrderItemBuilder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public OrderItemBuilder modifiedAt(LocalDateTime modifiedAt) {
            this.modifiedAt = modifiedAt;
            return this;
        }

        public OrderItem build(){
            return new OrderItem(this);
        }
    }
}
