package org.prgms.rest_api.order.dto;

import org.prgms.rest_api.order.model.OrderItem;
import org.prgms.rest_api.vo.Address;

import javax.validation.constraints.NotNull;
import java.util.List;

public class CreateOrderRequest {
    @NotNull
    private Address address;
    @NotNull
    private List<OrderItem> orderItems;

    public CreateOrderRequest(Address address, List<OrderItem> orderItems) {
        this.address = address;
        this.orderItems = orderItems;
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

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
