package org.prgms.rest_api.product.repository;

import org.prgms.rest_api.product.model.Category;
import org.prgms.rest_api.product.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Long save(Product product);

    Product update(Product product);

    List<Product> findAll();

    Optional<Product> findById(Long productId);

    List<Product> findByCategory(Category category);

    void deleteAll();

    void deleteById(Long productId);
}
