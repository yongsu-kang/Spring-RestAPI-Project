package org.prgms.rest_api.order.dto;

import org.prgms.rest_api.order.model.OrderItem;
import org.prgms.rest_api.vo.Address;

import java.util.List;

public class OrderResponse {

    private final Long customerId;
    private Address address;
    private final List<OrderItem> orderItems;

    public OrderResponse(Long customerId, Address address, List<OrderItem> orderItems) {
        this.customerId = customerId;
        this.address = address;
        this.orderItems = orderItems;
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
}
