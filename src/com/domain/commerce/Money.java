package com.domain.commerce;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Stanislav Kurilin
 */

@Embeddable
public class Money implements Serializable {
    @Column(name = "money")
    @NotNull
    private BigDecimal value;

    public static Money valueOf(String value) {
        if (value.isEmpty()) {
            return new Money(null);
        }
        BigDecimal decimal = new BigDecimal(value);
        return new Money(decimal);
    }

    public Money(BigDecimal value) {
        this.value = value;
    }

    public Money(long value) {
        this(new BigDecimal(value));
    }

    public Money(double value) {
        this(new BigDecimal(value));
    }

    public Money() {
    }


    public BigDecimal getValue() {
        return value;
    }

    @Override
    public String toString() {
        if (value == null) {
            return "";
        }
        return value.toString();
    }
}
