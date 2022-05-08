package org.prgms.rest_api.order.service;

import org.prgms.rest_api.cart.service.CartService;
import org.prgms.rest_api.customer.dto.CustomerDto;
import org.prgms.rest_api.customer.service.CustomerService;
import org.prgms.rest_api.order.dto.CreateOrderRequest;
import org.prgms.rest_api.order.dto.OrderDto;
import org.prgms.rest_api.order.model.Order;
import org.prgms.rest_api.order.model.OrderItem;
import org.prgms.rest_api.order.model.OrderStatus;
import org.prgms.rest_api.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final CartService cartService;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerService customerService, CartService cartService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.cartService = cartService;
    }

    @Override
    public Long order(OrderDto orderDto) {
        return orderRepository.save(dtoToEntity(orderDto));
    }

    @Override
    public Long createOrderByCustomer(Long customerId, CreateOrderRequest createOrderRequest) {
        if (customerService.getCustomerById(customerId) != null)
            return orderRepository.save(
                    new Order.OrderBuilder(null, customerId, createOrderRequest.getOrderItems(), LocalDateTime.now())
                            .address(createOrderRequest.getAddress())
                            .orderStatus(OrderStatus.ACCEPTED)
                            .modifiedAt(LocalDateTime.now())
                            .build());
        return null;
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
        return entityToDto(orderRepository.findById(orderId).orElse(null));
    }

    @Override
    public List<OrderDto> getOrderByCustomer(Long customerId) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        if (customerService.getCustomerById(customerId) != null)
            orderRepository.findByCustomerId(customerId).forEach(order -> orderDtoList.add(entityToDto(order)));
        return orderDtoList;
    }

    @Override
    public void cancelOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public void orderInCart(Long customerId) {
        List<OrderItem> items = new ArrayList<>();
        CustomerDto customer = customerService.getCustomerById(customerId);
        cartService.getCartByCustomer(customerId)
                .forEach(cartProduct -> {
                    items.add(new OrderItem.OrderItemBuilder(null, LocalDateTime.now())
                            .productId(cartProduct.getProductId())
                            .category(cartProduct.getCategory())
                            .price(cartProduct.getPrice())
                            .quantity(cartProduct.getQuantity())
                            .modifiedAt(LocalDateTime.now())
                            .build());
                    cartService.deleteCartById(cartProduct.getCartId());
                });

        orderRepository.save(new Order.OrderBuilder(null, customerId, items, LocalDateTime.now())
                .address(customer.getAddress())
                .orderStatus(OrderStatus.ACCEPTED)
                .modifiedAt(LocalDateTime.now())
                .build());
    }
}
