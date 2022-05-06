package org.prgms.rest_api.product.service;

import org.prgms.rest_api.product.dto.ProductDto;
import org.prgms.rest_api.product.model.Category;
import org.prgms.rest_api.product.model.Product;
import org.prgms.rest_api.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Long createProduct(ProductDto productDto) {
        return productRepository.save(dtoToEntity(productDto));
    }

    @Override
    public List<Product> getProductAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public void deleteByProductId(ProductDto productDto) {
        productRepository.deleteById(productDto.getProductId());
    }
}
