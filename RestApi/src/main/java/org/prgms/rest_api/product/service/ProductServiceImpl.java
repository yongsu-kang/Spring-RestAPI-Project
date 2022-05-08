package org.prgms.rest_api.product.service;

import org.prgms.rest_api.product.dto.CreateProductRequest;
import org.prgms.rest_api.product.dto.ProductDto;
import org.prgms.rest_api.product.model.Category;
import org.prgms.rest_api.product.model.Product;
import org.prgms.rest_api.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Long createProduct(CreateProductRequest createProductRequest) {
        return productRepository.save(new Product.ProductBuilder(null, LocalDateTime.now())
                .productName(createProductRequest.getProductName())
                .price(createProductRequest.getPrice())
                .category(createProductRequest.getCategory())
                .description(createProductRequest.getDescription())
                .modifiedAt(LocalDateTime.now())
                .build());
    }

    @Override
    public List<ProductDto> getProductAll() {
        List<ProductDto> productDtoList = new ArrayList<>();
        productRepository.findAll().forEach(product -> productDtoList.add(entityToDto(product)));
        return productDtoList;
    }

    @Override
    public List<ProductDto> getProductByCategory(Category category) {
        List<ProductDto> productDtoList = new ArrayList<>();
        productRepository.findByCategory(category).forEach(product -> productDtoList.add(entityToDto(product)));
        return productDtoList;
    }

    @Override
    public ProductDto getProductById(Long productId) {
        return entityToDto(productRepository.findById(productId).orElse(null));
    }

    @Override
    public void deleteByProductId(Long productId) {
        productRepository.deleteById(productId);
    }
}
