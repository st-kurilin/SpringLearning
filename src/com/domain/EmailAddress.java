package com.domain;

import javax.persistence.Column;

/**
 * @author Stanislav Kurilin
 */

public final class EmailAddress {
    @Column(name = "email")
    private final String value;

    public EmailAddress(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailAddress that = (EmailAddress) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
