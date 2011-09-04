package com.util.hibernate;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.java.LongTypeDescriptor;
import org.hibernate.type.descriptor.sql.NumericTypeDescriptor;

/**
 * @author Stanislav Kurilin
 */
public abstract class LongValueWrapper extends AbstractSingleColumnStandardBasicType<Long> {
    private final String columnName;

    public LongValueWrapper(String columnName) {
        super(new NumericTypeDescriptor(), new LongTypeDescriptor());
        this.columnName = columnName;
    }

    @Override
    public String getName() {
        return columnName;
    }
}

