package org.prgms.rest_api.vo;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class Address {
    @NotBlank
    private String address;
    @NotBlank
    private Long postcode;

    public Address(String address, Long postcode) {
        checkPostcode(postcode);

        this.address = address;
        this.postcode = postcode;
    }

    private void checkPostcode(Long postcode) {
        if (postcode < 0) {
            throw new IllegalArgumentException("wrong input postcode");
        }
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
