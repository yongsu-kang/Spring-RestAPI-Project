package org.prgms.rest_api.customer.controller;

import org.prgms.rest_api.customer.dto.CreateCustomerRequest;
import org.prgms.rest_api.customer.dto.CustomerDto;
import org.prgms.rest_api.customer.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String getCustomers(Model model) {
        model.addAttribute("customers", customerService.getCustomers());
        return "/customer/list";
    }

    @GetMapping("/new")
    public String createCustomer() {
        return "/customer/new";
    }

    @PostMapping("/new")
    public String createCustomer(@Valid CreateCustomerRequest createCustomerRequest) {
        Long customerId = customerService.createCustomer(createCustomerRequest);
        return "redirect:/login";
    }

    @GetMapping("/{customerId}")
    public String getDetailCustomer(@PathVariable Long customerId, Model model) {
        CustomerDto customer = customerService.getCustomerById(customerId);
        model.addAttribute("customer", customer);
        return "/customer/detail";
    }

    @GetMapping("/delete/{customerId}")
    public String deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
        return "/customer/list";
    }
}
