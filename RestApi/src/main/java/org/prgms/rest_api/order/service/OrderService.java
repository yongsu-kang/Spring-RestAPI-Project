package org.prgms.rest_api.order.service;

import org.prgms.rest_api.order.dto.OrderDto;
import org.prgms.rest_api.order.model.Order;

import java.util.Optional;

public interface OrderService {

    Long order(OrderDto orderDto);

    OrderDto getOrderById(Long orderId);

    void cancelOrderById(Long orderId);

    default OrderDto entityToDto(Order order) {
        return new OrderDto.OrderDtoBuilder(order.getOrderId(), order.getCustomerId(), order.getOrderItems(), order.getCreatedAt())
                .address(order.getAddress())
                .orderStatus(order.getOrderStatus())
                .modifiedAt(order.getModifiedAt())
                .build();
    }

    default Order dtoToEntity(OrderDto orderDto) {
        return new Order.OrderBuilder(orderDto.getOrderId(), orderDto.getCustomerId(), orderDto.getOrderItems(), orderDto.getCreatedAt())
                .address(orderDto.getAddress())
                .orderStatus(orderDto.getOrderStatus())
                .modifiedAt(orderDto.getModifiedAt())
                .build();
    }

}
