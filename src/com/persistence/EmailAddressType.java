package com.persistence;

import com.domain.customer.EmailAddress;
import com.util.hibernate.StringValueWrapper;
import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
