package org.prgms.rest_api.product.repository;

import org.prgms.rest_api.product.model.Category;
import org.prgms.rest_api.product.model.Product;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

import static org.prgms.rest_api.util.JdbcRepositoryUtils.*;

@Repository
@Transactional(readOnly = true)
public class JdbcProductRepository implements ProductRepository{

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcProductRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Long save(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int save = jdbcTemplate.update(
                "INSERT INTO product(product_id, product_name, category, price, description, created_at, modified_at)" +
                        "values (:productId, :productName, :category, :price, :description, :createdAt, :modifiedAt)",
                getProductSqlParam(product), keyHolder);
        if (save != 1) {
            throw new RuntimeException("Nothing was saved");
        }
        return keyHolder.getKey().longValue();
    }

    @Override
    @Transactional
    public Product update(Product product) {
        int save = jdbcTemplate.update(
                "update product set product_name = :productName, category = :category, price = :price, description = :description, modified_at = :modifiedAt where product_id = :productId",
                toProductParamMap(product));
        if (save != 1) {
            throw new RuntimeException("Nothing was updated");
        }
        return product;
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("select * from product", productRowMapper);
    }

    @Override
    public Optional<Product> findById(Long productId) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("select * from product where product_id = :productId",
                    Collections.singletonMap("productId", productId),
                    productRowMapper));
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return jdbcTemplate.query("select * from product where category = :category",
                Collections.singletonMap("category", category),
                productRowMapper);
    }

    @Override
    @Transactional
    public void deleteAll() {
        jdbcTemplate.update("delete from product", Collections.emptyMap());
    }

    @Override
    @Transactional
    public void deleteById(Long productId) {
        jdbcTemplate.update("delete from product where product_id = :productId", Collections.singletonMap("productId", productId));
    }

    private final RowMapper<Product> productRowMapper = (resultSet, rowNum) -> {
        return new Product.ProductBuilder(resultSet.getLong("product_id"), toLocalDateTime(resultSet.getTimestamp("created_at")))
                .productName(resultSet.getString("product_name"))
                .category(Category.valueOf(resultSet.getString("category")))
                .price(resultSet.getLong("price"))
                .description(resultSet.getString("description"))
                .modifiedAt(toLocalDateTime(resultSet.getTimestamp("modified_at")))
                .build();
    };

    private SqlParameterSource getProductSqlParam(Product product) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("productId", product.getProductId());
        sqlParameterSource.addValue("productName", product.getProductName());
        sqlParameterSource.addValue("category", product.getCategory().toString());
        sqlParameterSource.addValue("price", product.getPrice());
        sqlParameterSource.addValue("description", product.getDescription());
        sqlParameterSource.addValue("createdAt", product.getCreatedAt());
        sqlParameterSource.addValue("modifiedAt", product.getModifiedAt());
        return sqlParameterSource;
    }

    private Map<String, Object> toProductParamMap(Product product) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("productId", product.getProductId());
        paramMap.put("productName", product.getProductName());
        paramMap.put("category", product.getCategory().toString());
        paramMap.put("price", product.getPrice());
        paramMap.put("description", product.getDescription());
        paramMap.put("createdAt", product.getCreatedAt());
        paramMap.put("modifiedAt", product.getModifiedAt());
        return paramMap;
    }
}
