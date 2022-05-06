package org.prgms.rest_api.customer.service;

import org.prgms.rest_api.customer.dto.CustomerDto;
import org.prgms.rest_api.customer.model.Customer;
import org.prgms.rest_api.vo.Email;

import java.util.List;

public interface CustomerService {

    Long createCustomer(CustomerDto customerDto);

    CustomerDto getCustomerById(Long customerId);

    CustomerDto getCustomerByEmail(Email email);

    List<CustomerDto> getCustomers();

    default CustomerDto entityToDto(Customer customer) {
        return new CustomerDto.CustomerDtoBuilder(customer.getCustomerId(), customer.getCreatedAt())
                .address(customer.getAddress())
                .email(customer.getEmail())
                .name(customer.getName())
                .orders(customer.getOrders())
                .vouchers(customer.getVouchers())
                .modifiedAt(customer.getModifiedAt())
                .build();
    }

    default Customer dtoToEntity(CustomerDto customerDto) {
        return new Customer.CustomerBuilder(customerDto.getCustomerId(), customerDto.getCreatedAt())
                .address(customerDto.getAddress())
                .email(customerDto.getEmail())
                .name(customerDto.getName())
                .orders(customerDto.getOrders())
                .vouchers(customerDto.getVouchers())
                .modifiedAt(customerDto.getModifiedAt())
                .build();
    }
}
