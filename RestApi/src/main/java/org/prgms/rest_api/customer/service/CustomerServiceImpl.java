package org.prgms.rest_api.customer.service;

import org.prgms.rest_api.customer.dto.CreateCustomerRequest;
import org.prgms.rest_api.customer.dto.CustomerDto;
import org.prgms.rest_api.customer.model.Customer;
import org.prgms.rest_api.customer.repository.CustomerRepository;
import org.prgms.rest_api.vo.Address;
import org.prgms.rest_api.vo.Email;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Long createCustomer(CreateCustomerRequest createCustomerRequest) {
        return customerRepository.save(getCustomerFromCreateDto(createCustomerRequest));
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

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    private Customer getCustomerFromCreateDto(CreateCustomerRequest createCustomerRequest) {
        return new Customer.CustomerBuilder(null, LocalDateTime.now())
                .name(createCustomerRequest.getName())
                .email(createCustomerRequest.getEmail())
                .address(new Address(createCustomerRequest.getAddress(), createCustomerRequest.getPostCode()))
                .modifiedAt(LocalDateTime.now())
                .build();
    }
}
