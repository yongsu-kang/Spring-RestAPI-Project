package org.prgms.rest_api.customer.model;

import org.prgms.rest_api.vo.Address;
import org.prgms.rest_api.order.model.Order;
import org.prgms.rest_api.vo.Email;

import java.time.LocalDateTime;
import java.util.List;

public class Customer {
    private final Long customerId;
    private String name;
    private Email email;
    private Address address;
    private final LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<Order> orders;

    private Customer(CustomerBuilder builder) {
        this.customerId = builder.customerId;
        this.name = builder.name;
        this.email = builder.email;
        this.address = builder.address;
        this.createdAt = builder.createdAt;
        this.modifiedAt = builder.modifiedAt;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public static class CustomerBuilder{
        private final Long customerId;
        private String name;
        private Email email;
        private Address address;
        private final LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private List<Order> orders;

        public CustomerBuilder(Long customerId, LocalDateTime createdAt) {
            this.customerId = customerId;
            this.createdAt = createdAt;
        }

        public CustomerBuilder name(String name){
            this.name = name;
            return this;
        }
        public CustomerBuilder email(Email email){
            this.email = email;
            return this;
        }
        public CustomerBuilder address(Address address){
            this.address = address;
            return this;
        }

        public CustomerBuilder modifiedAt(LocalDateTime modifiedAt){
            this.modifiedAt = modifiedAt;
            return this;
        }

        public CustomerBuilder orders(List<Order> orders) {
            this.orders = orders;
            return this;
        }

        public Customer build(){
            return new Customer(this);
        }
    }
}
