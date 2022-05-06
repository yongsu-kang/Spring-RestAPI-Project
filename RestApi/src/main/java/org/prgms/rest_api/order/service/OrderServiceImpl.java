package org.prgms.rest_api.order.service;

import org.prgms.rest_api.order.dto.OrderDto;
import org.prgms.rest_api.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Long order(OrderDto orderDto) {
        return orderRepository.save(dtoToEntity(orderDto));
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
        return entityToDto(orderRepository.findById(orderId).orElse(null));
    }

    @Override
    public void cancelOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
