package com.domain.commerce;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author Stanislav Kurilin
 */

@Embeddable
public class Money implements Serializable {
    @Column(name = "money")
    private long value;

    public Money(long value) {
        this.value = value;
    }

    public Money() {
    }

    public long getValue() {
        return value;
    }


}
