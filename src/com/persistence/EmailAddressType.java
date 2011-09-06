package com.persistence;

import com.domain.customer.EmailAddress;
import com.util.hibernate.StringValueWrapper;

/**
 * @author Stanislav Kurilin
 */
public class EmailAddressType extends StringValueWrapper<EmailAddress> {

    @Override
    protected String columnName() {
        return "email";
    }

    @Override
    protected String retrieveValue(EmailAddress component) {
        return component.getValue();
    }

    @Override
    protected EmailAddress compile(String value) {
        return new EmailAddress(value);
    }
}
