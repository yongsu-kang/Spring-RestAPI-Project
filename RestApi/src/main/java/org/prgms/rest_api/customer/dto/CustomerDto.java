package org.prgms.rest_api.customer.dto;

import org.prgms.rest_api.order.model.Order;
import org.prgms.rest_api.vo.Address;
import org.prgms.rest_api.vo.Email;

import java.time.LocalDateTime;
import java.util.List;

public class CustomerDto {
    private final Long customerId;
    private String name;
    private Email email;
    private Address address;
    private final LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<Order> orders;

    private CustomerDto(CustomerDtoBuilder builder) {
        this.customerId = builder.customerId;
        this.name = builder.name;
        this.email = builder.email;
        this.address = builder.address;
        this.createdAt = builder.createdAt;
        this.modifiedAt = builder.modifiedAt;
        this.orders = builder.orders;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
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

    public static class CustomerDtoBuilder {
        private final Long customerId;
        private String name;
        private Email email;
        private Address address;
        private final LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private List<Order> orders;

        public CustomerDtoBuilder(Long customerId, LocalDateTime createdAt) {
            this.customerId = customerId;
            this.createdAt = createdAt;
        }

        public CustomerDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CustomerDtoBuilder email(Email email) {
            this.email = email;
            return this;
        }

        public CustomerDtoBuilder address(Address address) {
            this.address = address;
            return this;
        }

        public CustomerDtoBuilder modifiedAt(LocalDateTime modifiedAt) {
            this.modifiedAt = modifiedAt;
            return this;
        }

        public CustomerDtoBuilder orders(List<Order> orders) {
            this.orders = orders;
            return this;
        }

        public CustomerDto build(){
            return new CustomerDto(this);
        }
    }
}
