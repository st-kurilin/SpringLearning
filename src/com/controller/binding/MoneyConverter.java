package com.controller.binding;

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
        if (text.isEmpty()) {
            return new Money();
        }
        return new Money(new BigDecimal(text));
    }
}

