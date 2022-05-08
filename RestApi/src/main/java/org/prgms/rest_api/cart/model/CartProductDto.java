package org.prgms.rest_api.cart.model;

import org.prgms.rest_api.product.model.Category;

public class CartProductDto {
    private Long cartId;
    private Long productId;
    private String productName;
    private Category category;
    private Long price;
    private String description;
    private int quantity;

    public CartProductDto(Long productId, String productName, Category category, Long price, String description, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public CartProductDto(Long cartId, Long productId, String productName, Category category, Long price, String description, int quantity) {
        this.cartId = cartId;
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
