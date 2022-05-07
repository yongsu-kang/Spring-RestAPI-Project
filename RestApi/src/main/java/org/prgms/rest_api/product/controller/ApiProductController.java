package org.prgms.rest_api.product.controller;

import org.prgms.rest_api.product.dto.CreateProductRequest;
import org.prgms.rest_api.product.dto.ProductDto;
import org.prgms.rest_api.product.model.Category;
import org.prgms.rest_api.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ApiProductController {
    private final ProductService productService;

    public ApiProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getProducts(@RequestParam Category category) {
        return category != null ? productService.getProductByCategory(category) : productService.getProductAll();
    }

    @PostMapping
    public Long createProduct(@RequestBody CreateProductRequest createProductRequest) {
        return productService.createProduct(createProductRequest);
    }

    @GetMapping("/{productId}")
    public ProductDto getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteByProductId(productId);
    }
}
