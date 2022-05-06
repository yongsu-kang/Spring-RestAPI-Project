package org.prgms.rest_api.vo;

import java.util.Objects;
import java.util.regex.Pattern;

public class Email {
    private String email;

    public Email(String email) {
        if (!isCorrectEmail(email)) throw new IllegalArgumentException();
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    private boolean isCorrectEmail(String email) {
        return Pattern.matches("\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b", email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
