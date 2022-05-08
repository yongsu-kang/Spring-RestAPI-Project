package org.prgms.rest_api.cart.repository;

import org.prgms.rest_api.cart.model.Cart;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Repository
public class JdbcCartRepository implements CartRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcCartRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Long save(Cart cart) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update("INSERT INTO cart(cart_id,customer_id, product_id, quantity) " +
                        "values (:cartId,:customerId,:productId,:quantity)",
                getCartSqlParam(cart),
                keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public List<Cart> findProductIdByCustomerIdInCart(Long customerId) {
        return jdbcTemplate.query("select * from cart where customer_id =:customerId",
                Collections.singletonMap("customerId", customerId),
                cartRowMapper);
    }

    @Override
    public Cart findById(Long cartId) {
        try {
            return jdbcTemplate.queryForObject("select * from cart where cart_id = :cartId", Collections.singletonMap("cartId", cartId), cartRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Long cartId) {
        jdbcTemplate.update("delete from cart where cart_id = :cartId", Collections.singletonMap("cartId", cartId));
    }

    private final RowMapper<Cart> cartRowMapper = (resultSet, i) -> {
        return new Cart(
                resultSet.getLong("cart_id"),
                resultSet.getLong("customer_id"),
                resultSet.getLong("product_id"),
                resultSet.getInt("quantity")
        );
    };

    private SqlParameterSource getCartSqlParam(Cart cart) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("cartId", cart.getCartId());
        param.addValue("customerId", cart.getCustomerId());
        param.addValue("productId", cart.getProductId());
        param.addValue("quantity", cart.getQuantity());
        return param;
    }
}
