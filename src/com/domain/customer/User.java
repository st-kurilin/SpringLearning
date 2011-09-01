package com.domain.customer;

import com.persistence.EmailAddressType2;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
<<<<<<< HEAD
import javax.validation.constraints.Pattern;
=======
>>>>>>> d162b1a... Validation.
import java.util.Date;


/**
 * @author Stanislav Kurilin
 */
@Entity
@Table(name = "USERS")
@TypeDef(name = "com.domain.customer.EmailAddress", typeClass = EmailAddressType2.class)
public class User extends AbstractPersistable<Long> {

    @Length(min = 6, max = 14, message = "Length should be from 6 to 14")
    private String name;

    @Type(type = "com.domain.customer.EmailAddress")
    @Valid
    private EmailAddress email;

    @Temporal(TemporalType.DATE)
    @Valid
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Birthday field can't be null")
    @Past(message = "Date should be in past time")
    private Date birthday;


    @Enumerated(EnumType.STRING)
    @NotNull(message = "Please, select your sex")
    private Sex sex;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmailAddress getEmail() {
        return email;
    }

    public void setEmail(EmailAddress email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setId(Long id) {
        super.setId(id);
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

}
