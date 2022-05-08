package org.prgms.rest_api.customer.repository;

import org.prgms.rest_api.customer.model.Customer;
import org.prgms.rest_api.vo.Email;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    Long save(Customer customer);

    Optional<Customer> findByEmail(Email email);

    Optional<Customer> findById(Long customerId);

    List<Customer> findAll();

    void deleteAll();

    void deleteById(Long customerId);
}
