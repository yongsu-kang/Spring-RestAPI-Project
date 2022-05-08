package org.prgms.rest_api.order.controller;

import org.prgms.rest_api.order.dto.CartOrderRequest;
import org.prgms.rest_api.order.dto.OrderDto;
import org.prgms.rest_api.order.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{customerId}")
    public String getOrders(@PathVariable Long customerId, Model model) {
        List<OrderDto> orders = orderService.getOrderByCustomer(customerId);
        model.addAttribute("customerId", customerId);
        model.addAttribute("orders", orders);
        return "order/list";
    }

    @PostMapping("/cart/{customerId}")
    public String createCartOrder(@PathVariable Long customerId) {
        orderService.orderInCart(customerId);
        return "redirect:/products/" + customerId;
    }

    @GetMapping("/delete/{customerId}/{orderId}")
    public String deleteOrder(@PathVariable Long customerId, @PathVariable Long orderId) {
        orderService.cancelOrderById(orderId);
        return "redirect:/order/" + customerId;
    }
}
