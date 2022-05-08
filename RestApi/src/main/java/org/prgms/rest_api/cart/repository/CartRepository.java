package org.prgms.rest_api.cart.repository;

import org.prgms.rest_api.cart.model.Cart;

import java.util.List;

public interface CartRepository {

    Long save(Cart cart);

    List<Cart> findProductIdByCustomerIdInCart(Long customerId);

    Cart findById(Long cartId);

    void delete(Long cartId);
}
