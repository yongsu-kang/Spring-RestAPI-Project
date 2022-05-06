package org.prgms.rest_api.customer.repository;

import org.prgms.rest_api.customer.model.Customer;
import org.prgms.rest_api.vo.Email;
import org.prgms.rest_api.vo.Address;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.prgms.rest_api.util.JdbcRepositoryUtils.*;

@Repository
public class JdbcCustomerRepository implements CustomerRepository{

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcCustomerRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(Customer customer) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int save = jdbcTemplate.update("insert into customer(customer_id,name,email,address,postcode,created_at,modified_at) " +
                        "values(:customerId,:name,:email,:address,:postcode,:createdAt,:modifiedAt) ",
                getCustomerSqlParam(customer),
                keyHolder);
        if (save != 1) {
            throw new RuntimeException("customer was not saved");
        }

        return keyHolder.getKey().longValue();
    }

    @Override
    public Optional<Customer> findByEmail(Email email) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("select * from customer where email = :email",
                    Collections.singletonMap("email", email.getEmail()),
                    getCustomerRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Customer> findById(Long customerId) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("select * from customer where customer_id = :customerId",
                    Collections.singletonMap("customerId", customerId),
                    getCustomerRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Customer> findAll() {
        return jdbcTemplate.query("select * from customer", getCustomerRowMapper());
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("delete from customer", Collections.emptyMap());
    }

    private RowMapper<Customer> getCustomerRowMapper(){
        return (resultSet, rowNum) ->{
            return new Customer.CustomerBuilder(resultSet.getLong("customer_id"), toLocalDateTime(resultSet.getTimestamp("created_at")))
                    .name("name")
                    .email(new Email(resultSet.getString("email")))
                    .address(new Address(resultSet.getString("address"), resultSet.getLong("postcode")))
                    .modifiedAt(toLocalDateTime(resultSet.getTimestamp("modified_at")))
                    .build();
        };
    }

    private SqlParameterSource getCustomerSqlParam(Customer customer) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("customerId", customer.getCustomerId());
        param.addValue("name", customer.getName());
        param.addValue("email", customer.getEmail().getEmail());
        param.addValue("address", customer.getAddress().getAddress());
        param.addValue("postcode", customer.getAddress().getPostcode());
        param.addValue("createdAt", customer.getCreatedAt());
        param.addValue("modifiedAt", customer.getModifiedAt());
        return param;
    }
}
