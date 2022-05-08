package org.prgms.rest_api.order.dto;

import java.util.List;

public class CartOrderRequest {
    private List<Long> productId;

    public CartOrderRequest() {
    }

    public CartOrderRequest(List<Long> productId) {
        this.productId = productId;
    }

    public List<Long> getProductId() {
        return productId;
    }

    public void setProductId(List<Long> productId) {
        this.productId = productId;
    }
}
