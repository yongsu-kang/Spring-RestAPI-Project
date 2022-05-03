package org.prgms.rest_api.order.model;

import java.util.Objects;

public class Address {
    private String address;
    private Long postcode;

    public Address(String address, Long postcode) {
        this.address = address;
        this.postcode = postcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(address, address1.address) && Objects.equals(postcode, address1.postcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, postcode);
    }

    public String getAddress() {
        return address;
    }

    public Long getPostcode() {
        return postcode;
    }
}
