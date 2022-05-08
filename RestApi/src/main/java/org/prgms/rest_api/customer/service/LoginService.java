package org.prgms.rest_api.customer.service;

import org.prgms.rest_api.customer.dto.CustomerDto;
import org.prgms.rest_api.vo.Email;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final CustomerService customerService;

    public LoginService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public CustomerDto login(Email email) {
        return customerService.getCustomerByEmail(email);
    }
}
