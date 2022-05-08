package org.prgms.rest_api.cart.model;

public class Cart {
    private final Long cartId;
    private Long customerId;
    private Long productId;
    private int quantity;

    public Cart(Long cartId, Long customerId, Long productId, int quantity) {
        this.cartId = cartId;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getCartId() {
        return cartId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
