package org.prgms.rest_api.product.service;

import org.prgms.rest_api.product.dto.ProductDto;
import org.prgms.rest_api.product.model.Category;
import org.prgms.rest_api.product.model.Product;
import org.prgms.rest_api.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    Long createProduct(ProductDto productDto);

    List<Product> getProductAll();

    List<Product> getProductByCategory(Category category);

    void deleteByProductId(ProductDto productDto);


    default ProductDto entityToDto(Product product) {
        return new ProductDto.ProductDtoBuilder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .category(product.getCategory())
                .price(product.getPrice())
                .description(product.getDescription())
                .createdAt(product.getCreatedAt())
                .modifiedAt(product.getModifiedAt())
                .build();
    }

    default Product dtoToEntity(ProductDto productDto) {
        return new Product.ProductBuilder(productDto.getProductId() == null ? null : productDto.getProductId(), productDto.getCreatedAt())
                .productName(productDto.getProductName())
                .category(productDto.getCategory())
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .modifiedAt(productDto.getModifiedAt())
                .build();
    }
}
