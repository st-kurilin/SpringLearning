package com.domain.commerce;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Stanislav Kurilin
 */

@Embeddable
public class Money implements Serializable {
    @Column(name = "money")
    private BigDecimal value;

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
        return value.toString();
    }
}
