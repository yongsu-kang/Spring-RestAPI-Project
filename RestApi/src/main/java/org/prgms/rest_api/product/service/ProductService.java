package org.prgms.rest_api.product.service;

import org.prgms.rest_api.product.dto.CreateProductRequest;
import org.prgms.rest_api.product.dto.ProductDto;
import org.prgms.rest_api.product.model.Category;
import org.prgms.rest_api.product.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    Long createProduct(CreateProductRequest createProductRequest);

    List<ProductDto> getProductAll();

    List<ProductDto> getProductByCategory(Category category);

    ProductDto getProductById(Long productId);

    void deleteByProductId(Long productDto);

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
