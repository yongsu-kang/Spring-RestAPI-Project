package org.prgms.rest_api.order.repository;

import org.prgms.rest_api.order.model.Order;
import org.prgms.rest_api.order.model.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    Long save(Order order);

    Order update(Order order);

    Optional<Order> findById(Long orderId);

    List<Order> findByCustomerId(Long customerId);

    List<Order> findByOrderStatus(OrderStatus orderStatus);

    void deleteById(Long orderId);

    void deleteAll();
}
