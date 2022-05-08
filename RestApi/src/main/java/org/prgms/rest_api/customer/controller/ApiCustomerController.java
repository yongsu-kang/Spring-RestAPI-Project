package org.prgms.rest_api.customer.controller;

import org.prgms.rest_api.customer.dto.CreateCustomerRequest;
import org.prgms.rest_api.customer.dto.CustomerDto;
import org.prgms.rest_api.customer.service.CustomerService;
import org.prgms.rest_api.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class ApiCustomerController {
    private final CustomerService customerService;

    public ApiCustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getCustomers(){
        return ResponseEntity.ok().body(customerService.getCustomers());
    }

    @PostMapping
    public ResponseEntity<Long> createCustomer(final @Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
        Long customer = customerService.createCustomer(createCustomerRequest);
        return ResponseEntity.ok().body(customer);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long customerId) {
        return ResponseEntity.ok().body(customerService.getCustomerById(customerId));
    }
}