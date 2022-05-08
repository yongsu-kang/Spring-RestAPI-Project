package org.prgms.rest_api.product.controller;

import org.prgms.rest_api.customer.service.CustomerService;
import org.prgms.rest_api.product.dto.CreateProductRequest;
import org.prgms.rest_api.product.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CustomerService customerService;

    public ProductController(ProductService productService, CustomerService customerService) {
        this.productService = productService;
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public String getProductsByLogin(@PathVariable Long customerId, Model model) {
        model.addAttribute("customerId",customerService.getCustomerById(customerId).getCustomerId());
        model.addAttribute("products", productService.getProductAll());
        return "/product/list";
    }

    @GetMapping("/new")
    public String createProduct() {
        return "/product/new";
    }

    @PostMapping("/new")
    public String createProduct(@Valid CreateProductRequest createProductRequest) {
        productService.createProduct(createProductRequest);
        return "redirect:/login";
    }
}
