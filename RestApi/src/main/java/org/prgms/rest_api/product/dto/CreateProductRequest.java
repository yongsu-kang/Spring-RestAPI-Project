package org.prgms.rest_api.product.dto;

import org.prgms.rest_api.product.model.Category;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class CreateProductRequest {
    @NotEmpty
    private String productName;
    @NotEmpty
    @PositiveOrZero
    private Long price;
    @NotEmpty
    private Category category;
    private String description;

    public CreateProductRequest(String productName, Long price, Category category, String description) {
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.description = description;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
