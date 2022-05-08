package org.prgms.rest_api.cart.controller;

import org.prgms.rest_api.cart.model.CartProductDto;
import org.prgms.rest_api.cart.service.CartService;
import org.prgms.rest_api.customer.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;
    private final CustomerService customerService;

    public CartController(CartService cartService, CustomerService customerService) {
        this.cartService = cartService;
        this.customerService = customerService;
    }

    @GetMapping("{customerId}")
    public String getCarts(@PathVariable Long customerId, Model model) {
        customerService.getCustomerById(customerId);
        List<CartProductDto> products = cartService.getCartByCustomer(customerId);
        model.addAttribute("customerId", customerId);
        model.addAttribute("products", products);
        return "/cart/list";
    }

    @PostMapping("/add/{customerId}")
    public String addCart(@PathVariable Long customerId, @RequestParam Long productId, int quantity) {
        cartService.addCart(customerId, productId, quantity);
        return "redirect:/products/" + customerId;
    }

    @GetMapping("/delete/{customerId}/{cartId}")
    public String deleteCartItem(@PathVariable Long cartId, @PathVariable Long customerId) {
        cartService.deleteCartById(cartId);
        return "redirect:/carts/" + customerId;
    }
}
