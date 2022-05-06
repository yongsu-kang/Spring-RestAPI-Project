package org.prgms.rest_api.product.dto;

import org.prgms.rest_api.product.model.Category;

import java.time.LocalDateTime;

public class ProductDto {
    private Long productId;
    private String productName;
    private Category category;
    private Long price;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private ProductDto(ProductDtoBuilder builder) {
        checkPrice(builder.price);

        this.productId = builder.productId;
        this.productName = builder.productName;
        this.category = builder.category;
        this.price = builder.price;
        this.description = builder.description;
        this.createdAt = builder.createdAt;
        this.modifiedAt = builder.modifiedAt;
    }

    private void checkPrice(Long price) {
        if (price < 0) throw new IllegalArgumentException("price should be positive");
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }

    public static class ProductDtoBuilder {
        private Long productId;
        private String productName;
        private Category category;
        private Long price;
        private String description;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public ProductDtoBuilder() {
        }

        public ProductDtoBuilder productId(Long productId) {
            this.productId = productId;
            return this;
        }

        public ProductDtoBuilder productName(String productName) {
            this.productName = productName;
            return this;
        }
        public ProductDtoBuilder category(Category category) {
            this.category = category;
            return this;
        }
        public ProductDtoBuilder price(Long price) {
            this.price = price;
            return this;
        }
        public ProductDtoBuilder description(String description) {
            this.description = description;
            return this;
        }
        public ProductDtoBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }
        public ProductDtoBuilder modifiedAt(LocalDateTime modifiedAt) {
            this.modifiedAt = modifiedAt;
            return this;
        }
        public ProductDto build(){
            return new ProductDto(this);
        }
    }
}
