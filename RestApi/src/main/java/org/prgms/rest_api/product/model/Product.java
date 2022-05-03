package org.prgms.rest_api.product.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Product {
    private final Long productId;
    private String productName;
    private Category category;
    private Long price;
    private String description;
    private final LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private Product(ProductBuilder builder) {
        checkPrice(builder.price);

        this.productId = builder.productId;
        this.createdAt = builder.createdAt;
        this.productName = builder.productName;
        this.category = builder.category;
        this.price = builder.price;
        this.description = builder.description;
        this.modifiedAt = builder.modifiedAt;
    }

    private void checkPrice(Long price) {
        if (price < 0) throw new IllegalArgumentException("price should be positive");
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Category getCategory() {
        return category;
    }

    public Long getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void changePrice(Long price) {
        this.price = price;
        this.modifiedAt = LocalDateTime.now();
    }

    public void changeDescription(String description) {
        this.description = description;
        this.modifiedAt = LocalDateTime.now();
    }

    public void changeCategory(Category category) {
        this.category = category;
        this.modifiedAt = LocalDateTime.now();
    }

    public void changeProductName(String productName) {
        this.productName = productName;
        this.modifiedAt = LocalDateTime.now();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId) && Objects.equals(productName, product.productName) && category == product.category && Objects.equals(price, product.price) && Objects.equals(description, product.description) && Objects.equals(createdAt, product.createdAt) && Objects.equals(modifiedAt, product.modifiedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, category, price, description, createdAt, modifiedAt);
    }

    public static class ProductBuilder{
        private final Long productId;
        private String productName;
        private Category category;
        private Long price;
        private String description;
        private final LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public ProductBuilder(Long productId, LocalDateTime createdAt) {
            this.productId = productId;
            this.createdAt = createdAt;
        }

        public ProductBuilder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public ProductBuilder category(Category category) {
            this.category = category;
            return this;
        }
        public ProductBuilder price(Long price) {
            this.price = price;
            return this;
        }
        public ProductBuilder description(String description) {
            this.description = description;
            return this;
        }
        public ProductBuilder modifiedAt(LocalDateTime modifiedAt) {
            this.modifiedAt = modifiedAt;
            return this;
        }
        public Product build() {
            return new Product(this);
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }
}
