package org.prgms.rest_api.order.repository;

import org.prgms.rest_api.vo.Address;
import org.prgms.rest_api.order.model.Order;
import org.prgms.rest_api.order.model.OrderItem;
import org.prgms.rest_api.order.model.OrderStatus;
import org.prgms.rest_api.product.model.Category;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.prgms.rest_api.util.JdbcRepositoryUtils.*;

@Repository
@Transactional(readOnly = true)
public class JdbcOrderRepository implements OrderRepository{

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcOrderRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Long save(Order order) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update("INSERT INTO orders(order_id,customer_id,address,postcode,order_status,created_at,modified_at) " +
                "VALUES (:orderId, :customerId, :address, :postcode, :orderStatus, :createdAt, :modifiedAt)",
                getOrderSqlParam(order),
                keyHolder);
        order.getOrderItems().forEach(item ->
            jdbcTemplate.update("INSERT INTO order_item(order_item_id, order_id, product_id, category, price, quantity,created_at,modified_at) " +
                            "VALUES (:orderItemId, :orderId, :productId, :category,:price,:quantity,:createdAt,:modifiedAt)",
                    getOrderItemParamMap(keyHolder.getKey().longValue(), item))
        );

        return keyHolder.getKey().longValue();
    }



    @Override
    @Transactional
    public Order update(Order order) {
        return null;
    }

    @Override
    public Optional<Order> findById(Long orderId) {
        List<OrderItem> orderItems = jdbcTemplate.query("select * from order_item where order_id = :orderId",
                Collections.singletonMap("orderId", orderId),
                orderItemRowMapper);
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("select * from orders where order_id =:orderId",
                    Collections.singletonMap("orderId", orderId),
                    orderRowMapper(orderItems)));
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    @Override
    public List<Order> findByCustomerId(Long customerId) {
        return jdbcTemplate.query("select * from orders where customer_id =:customerId",
                Collections.singletonMap("customerId", customerId),
                orderRowMapper(null));
    }

    @Override
    public List<Order> findByOrderStatus(OrderStatus orderStatus) {
        return jdbcTemplate.query("select * from orders where order_status =:orderStatus",
                Collections.singletonMap("orderStatus", orderStatus),
                orderRowMapper(null));
    }

    @Override
    @Transactional
    public void deleteById(Long orderId) {
        jdbcTemplate.update("delete from orders where order_id = :orderId", Collections.singletonMap("orderId", orderId));
        jdbcTemplate.update("delete from order_item where order_id = :orderId", Collections.singletonMap("orderId", orderId));
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("delete from orders", Collections.emptyMap());
        jdbcTemplate.update("delete from order_item", Collections.emptyMap());
    }


    private final RowMapper<Order> orderRowMapper(List<OrderItem> orderItems) {
         return (result, rowNum) ->{
            return new Order.OrderBuilder(result.getLong("order_id"), result.getLong("customer_id"), orderItems, toLocalDateTime(result.getTimestamp("created_at")))
                    .address(new Address(result.getString("address"), result.getLong("postcode")))
                    .orderStatus(OrderStatus.valueOf(result.getString("order_status")))
                    .modifiedAt(toLocalDateTime(result.getTimestamp("modified_at")))
                    .build();
        };
    }

    private final RowMapper<OrderItem> orderItemRowMapper = (resultSet, rowNum) -> {
        return new OrderItem.OrderItemBuilder(resultSet.getLong("order_item_id"), toLocalDateTime(resultSet.getTimestamp("created_at")))
                .orderId(resultSet.getLong("order_id"))
                .productId(resultSet.getLong("product_id"))
                .category(Category.valueOf(resultSet.getString("category")))
                .price(resultSet.getLong("price"))
                .quantity(Integer.parseInt(resultSet.getString("quantity")))
                .modifiedAt(toLocalDateTime(resultSet.getTimestamp("modified_at")))
                .build();
    };

    private Map<String, Object> getOrderParamMap(Order order) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("orderId",order.getOrderId());
        paramMap.put("customerId", order.getCustomerId());
        paramMap.put("address", order.getAddress().getAddress());
        paramMap.put("postcode", order.getAddress().getPostcode());
        paramMap.put("orderStatus", order.getOrderStatus().toString());
        paramMap.put("createdAt", order.getCreatedAt());
        paramMap.put("modifiedAt", order.getModifiedAt());
        return paramMap;
    }

    private SqlParameterSource getOrderSqlParam(Order order) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("orderId",order.getOrderId());
        sqlParameterSource.addValue("customerId", order.getCustomerId());
        sqlParameterSource.addValue("address", order.getAddress().getAddress());
        sqlParameterSource.addValue("postcode", order.getAddress().getPostcode());
        sqlParameterSource.addValue("orderStatus", order.getOrderStatus().toString());
        sqlParameterSource.addValue("createdAt", order.getCreatedAt());
        sqlParameterSource.addValue("modifiedAt", order.getModifiedAt());
        return sqlParameterSource;
    }

    private Map<String, Object> getOrderItemParamMap(Long orderId,OrderItem orderItem) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("orderItemId", orderItem.getOrderItemId());
        paramMap.put("orderId", orderId);
        paramMap.put("productId", orderItem.getProductId());
        paramMap.put("category", orderItem.getCategory().toString());
        paramMap.put("price", orderItem.getPrice());
        paramMap.put("quantity", orderItem.getQuantity());
        paramMap.put("createdAt", orderItem.getCreatedAt());
        paramMap.put("modifiedAt", orderItem.getModifiedAt());
        return paramMap;
    }
}
