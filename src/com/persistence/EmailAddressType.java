package com.persistence;

import com.domain.customer.EmailAddress;
import org.hibernate.HibernateException;
import org.hibernate.type.StringType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Stanislav Kurilin
 */
//org.hibernate.type.Type
public class EmailAddressType implements UserType {
    Class wrapperClass = EmailAddress.class;
    Class valueClass = String.class;
    Class valueTypeClass = StringType.class;
    @Override
    public int[] sqlTypes() {
        return new int[]{
                StringType.INSTANCE.sqlType()
        };
    }

    @Override
    public Class returnedClass() {
        return valueClass;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        try {
            return x.equals(y);
        } catch (Exception e) {
            throw new HibernateException(e);
        }
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        try {
            return x.hashCode();
        } catch (Exception e) {
            throw new HibernateException(e);
        }
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
        final String label = names[0];
        final String value = rs.getString(label);
        return new EmailAddress(value);
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
        System.out.println(st);
        System.out.println(value);
        st.setString(index, ((EmailAddress)value).getValue());
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
    public Serializable disassemble(Object value) throws HibernateException {
        throw new UnsupportedOperationException("Shouldn't be executed");
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        throw new UnsupportedOperationException("Shouldn't be executed");
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }
}
