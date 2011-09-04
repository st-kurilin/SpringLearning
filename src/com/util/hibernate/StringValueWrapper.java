package com.util.hibernate;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.java.StringTypeDescriptor;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

/**
 * @author Stanislav Kurilin
 */
public abstract class StringValueWrapper extends AbstractSingleColumnStandardBasicType<String> {
    private final String columnName;

    public StringValueWrapper(String columnName) {
        super(new VarcharTypeDescriptor(), new StringTypeDescriptor());
        this.columnName = columnName;
    }

    @Override
    public String getName() {
        return columnName;
    }
}
