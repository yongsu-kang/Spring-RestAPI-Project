package org.prgms.rest_api.cart.controller;

import org.prgms.rest_api.cart.model.CartProductDto;
import org.prgms.rest_api.cart.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
public class ApiCartController {

    private final CartService cartService;

    public ApiCartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<CartProductDto>> getCart(@PathVariable Long customerId) {
        return ResponseEntity.ok().body(cartService.getCartByCustomer(customerId));
    }

    @PostMapping("/{customerId}")
    public ResponseEntity addCart(@PathVariable Long customerId, @RequestParam Long productId, @RequestParam int quantity) {
        cartService.addCart(customerId, productId, quantity);
        return ResponseEntity.ok().build();
    }
}
