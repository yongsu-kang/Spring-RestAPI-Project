package org.prgms.rest_api.customer.service;

import org.prgms.rest_api.customer.dto.CustomerDto;
import org.prgms.rest_api.customer.repository.CustomerRepository;
import org.prgms.rest_api.vo.Email;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Long createCustomer(CustomerDto customerDto) {
        return customerRepository.save(dtoToEntity(customerDto));
    }

    @Override
    public CustomerDto getCustomerById(Long customerId) {
        return entityToDto(customerRepository.findById(customerId).orElse(null));
    }

    @Override
    public CustomerDto getCustomerByEmail(Email email) {
        return entityToDto(customerRepository.findByEmail(email).orElse(null));
    }

    @Override
    public List<CustomerDto> getCustomers() {
        List<CustomerDto> customerDtoList = new ArrayList<>();
        customerRepository.findAll().forEach(entity -> customerDtoList.add(entityToDto(entity)));
        return customerDtoList;
    }
}
