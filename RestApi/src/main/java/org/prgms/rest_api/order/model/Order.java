package org.prgms.rest_api.order.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private final Long orderId;
    private final Long customerId;
    private Address address;
    private final List<OrderItem> orderItems;
    private OrderStatus orderStatus;
    private final LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private Order(OrderBuilder builder) {
        this.orderId = builder.orderId;
        this.customerId = builder.customerId;
        this.address = builder.address;
        this.orderItems = builder.orderItems;
        this.orderStatus = builder.orderStatus;
        this.createdAt = builder.createdAt;
        this.modifiedAt = builder.modifiedAt;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Address getAddress() {
        return address;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public static class OrderBuilder{
        private final Long orderId;
        private final Long customerId;
        private Address address;
        private final List<OrderItem> orderItems;
        private OrderStatus orderStatus;
        private final LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public OrderBuilder(Long orderId, Long customerId, List<OrderItem> orderItems, LocalDateTime createdAt) {
            this.orderId = orderId;
            this.customerId = customerId;
            this.orderItems = orderItems;
            this.createdAt = createdAt;
        }

        public OrderBuilder address(Address address) {
            this.address = address;
            return this;
        }
        public OrderBuilder orderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }
        public OrderBuilder modifiedAt(LocalDateTime modifiedAt) {
            this.modifiedAt = modifiedAt;
            return this;
        }
        public Order build(){
            return new Order(this);
        }
    }
}
