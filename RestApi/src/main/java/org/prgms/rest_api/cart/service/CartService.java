package org.prgms.rest_api.cart.service;

import org.prgms.rest_api.cart.model.Cart;
import org.prgms.rest_api.cart.model.CartProductDto;

import java.util.List;

public interface CartService {
    Long addCart(Long customerId, Long productId, int quantity);

    List<CartProductDto> getCartByCustomer(Long customerId);

    Cart getCartById(Long cartId);

    void deleteCartById(Long cartId);
}
