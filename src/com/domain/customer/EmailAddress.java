package com.domain.customer;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * @author Stanislav Kurilin
 */

public final class EmailAddress implements Serializable {
    @Column(name = "email")
    @Email
    @NotBlank
    private final String value;

    public EmailAddress(String value) {
        this.value = value;
    }

    public EmailAddress() {
        this("");
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    //equalsIgnoreCase since emails case insensitive
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailAddress that = (EmailAddress) o;
        return value.equalsIgnoreCase(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
