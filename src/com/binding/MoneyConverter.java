package com.binding;

import com.domain.commerce.Money;
import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;

/**
 * @author: Andrey Loboda
 * @date : 13.09.11
 */
public class MoneyConverter implements Converter<String, Money> {

    @Override
    public Money convert(String text) {
        BigDecimal decimal;
        if (text.isEmpty()) {
            decimal = null;
        } else {
            decimal = new BigDecimal(text);
        }
        return new Money(decimal);
    }
}

