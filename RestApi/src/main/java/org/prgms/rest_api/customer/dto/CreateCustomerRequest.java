package org.prgms.rest_api.customer.dto;

import org.prgms.rest_api.vo.Address;
import org.prgms.rest_api.vo.Email;

import javax.validation.constraints.NotNull;

public class CreateCustomerRequest {
    @NotNull
    private String name;
    @NotNull
    private Email email;
    @NotNull
    private String address;
    private Long postcode;

    public CreateCustomerRequest(String name, Email email, String address, Long postcode) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.postcode = postcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPostCode() {
        return postcode;
    }

    public void setPostCode(Long postcode) {
        this.postcode = postcode;
    }
}
