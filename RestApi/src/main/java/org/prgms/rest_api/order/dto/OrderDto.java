package org.prgms.rest_api.order.dto;

import org.prgms.rest_api.order.model.Order;
import org.prgms.rest_api.order.model.OrderItem;
import org.prgms.rest_api.order.model.OrderStatus;
import org.prgms.rest_api.vo.Address;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDto {
    private Long orderId;
    private Long customerId;
    private Address address;
    @NotNull
    private List<OrderItem> orderItems;
    private OrderStatus orderStatus;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private OrderDto(OrderDtoBuilder builder) {
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

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public static class OrderDtoBuilder{
        private Long orderId;
        private Long customerId;
        private Address address;
        private List<OrderItem> orderItems;
        private OrderStatus orderStatus;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public OrderDtoBuilder(Long orderId, Long customerId, List<OrderItem> orderItems, LocalDateTime createdAt) {
            this.orderId = orderId;
            this.customerId = customerId;
            this.orderItems = orderItems;
            this.createdAt = createdAt;
        }

        public OrderDtoBuilder address(Address address) {
            this.address = address;
            return this;
        }

        public OrderDtoBuilder orderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public OrderDtoBuilder modifiedAt(LocalDateTime modifiedAt) {
            this.modifiedAt = modifiedAt;
            return this;
        }

        public OrderDto build(){
            return new OrderDto(this);
        }
    }
}
