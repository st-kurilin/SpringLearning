package com.persistence;

import com.domain.customer.EmailAddress;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.java.StringTypeDescriptor;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

/**
 * @author Stanislav Kurilin
 */
public class EmailType extends AbstractSingleColumnStandardBasicType {
    public EmailType() {
        super(VarcharTypeDescriptor.INSTANCE, StringTypeDescriptor.INSTANCE);
    }

    @Override
    public String getName() {
        return EmailAddress.class.getName();
    }
}
