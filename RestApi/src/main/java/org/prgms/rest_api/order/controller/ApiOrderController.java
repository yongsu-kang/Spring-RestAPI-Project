package org.prgms.rest_api.order.controller;

import org.prgms.rest_api.order.dto.CreateOrderRequest;
import org.prgms.rest_api.order.dto.OrderDto;
import org.prgms.rest_api.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class ApiOrderController {

    private final OrderService orderService;

    public ApiOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{customerId}")
    public List<OrderDto> getOrderByCustomer(@PathVariable Long customerId) {
        return orderService.getOrderByCustomer(customerId);
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<Long> createOrderByCustomer(@PathVariable Long customerId, final @Valid @RequestBody CreateOrderRequest createOrderRequest) {
        Long createOrder = orderService.createOrderByCustomer(customerId, createOrderRequest);
        return ResponseEntity.ok().body(createOrder);
    }
}
