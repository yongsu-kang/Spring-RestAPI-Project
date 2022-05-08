package org.prgms.rest_api.customer.service;

import org.prgms.rest_api.customer.dto.CreateCustomerRequest;
import org.prgms.rest_api.customer.dto.CustomerDto;
import org.prgms.rest_api.customer.model.Customer;
import org.prgms.rest_api.vo.Email;

import java.util.List;

public interface CustomerService {

    Long createCustomer(CreateCustomerRequest createCustomerRequest);

    CustomerDto getCustomerById(Long customerId);

    CustomerDto getCustomerByEmail(Email email);

    List<CustomerDto> getCustomers();

    void deleteCustomer(Long customerId);

    default CustomerDto entityToDto(Customer customer) {
        return new CustomerDto.CustomerDtoBuilder(customer.getCustomerId(), customer.getCreatedAt())
                .address(customer.getAddress())
                .email(customer.getEmail())
                .name(customer.getName())
                .orders(customer.getOrders())
                .modifiedAt(customer.getModifiedAt())
                .build();
    }

    default Customer dtoToEntity(CustomerDto customerDto) {
        return new Customer.CustomerBuilder(customerDto.getCustomerId(), customerDto.getCreatedAt())
                .address(customerDto.getAddress())
                .email(customerDto.getEmail())
                .name(customerDto.getName())
                .orders(customerDto.getOrders())
                .modifiedAt(customerDto.getModifiedAt())
                .build();
    }


}
