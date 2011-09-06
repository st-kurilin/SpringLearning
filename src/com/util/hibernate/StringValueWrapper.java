package com.util.hibernate;

import com.domain.customer.EmailAddress;
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
public abstract class StringValueWrapper<T extends Serializable> implements CompositeUserType {

    @Override
    public String[] getPropertyNames() {
        return new String[]{columnName()};
    }

    protected abstract String columnName();

    @Override
    public Type[] getPropertyTypes() {
        return new Type[]{StringType.INSTANCE};
    }

    @Override
    @SuppressWarnings("unchecked") //can't generalize hibernate class
    public Object getPropertyValue(Object component, int property) throws HibernateException {
        if (property != 0) {
            throw new IllegalArgumentException("0 expected but " + property + "passed");
        }
        return retrieveValue((T) component);
    }

    protected abstract String retrieveValue(T component);

    @Override
    public void setPropertyValue(Object component, int property, Object value) throws HibernateException {
        if (property != 0) {
            throw new IllegalArgumentException("0 expected but " + property + "passed");
        }
        throw new UnsupportedOperationException("its immutable");
    }

    @Override
    public Class returnedClass() {
        return EmailAddress.class;
        //return this.getClass().getGenericInterfaces()[0].getClass();
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return x.equals(y);
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
        final String label = names[0];
        final String value = rs.getString(label);
        return compile(value);
    }

    protected abstract T compile(String value);

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
        st.setString(index, ((EmailAddress) value).getValue());
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    @SuppressWarnings("unchecked") //can't generalize hibernate class
    public Serializable disassemble(Object value, SessionImplementor session) throws HibernateException {
        return (T) value;
    }

    @Override
    public Object assemble(Serializable cached, SessionImplementor session, Object owner) throws HibernateException {
        return cached;
    }

    @Override
    public Object replace(Object original, Object target, SessionImplementor session, Object owner) throws HibernateException {
        return original;
    }
}
