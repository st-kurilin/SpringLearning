package com.domain.customer;

import com.domain.Role;
import com.google.common.collect.ImmutableSet;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;


/**
 * @author Stanislav Kurilin
 */
@Entity
@Table(name = "USERS")
@TypeDef(name = "com.persistence.EmailAddressType", typeClass = com.persistence.EmailAddressType.class)
public class User extends AbstractPersistable<Long> {
    @Length(min = 2, max = 14)
    private String name;

    @NotBlank
    private String password;

    @Type(type = "com.persistence.EmailAddressType")
    @Valid
    private EmailAddress email;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    @Past
    private Date birthday;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Enumerated(EnumType.STRING)
    @NotNull
    //TODO: [stas] there are should be case with unspecified gender. BTW, I think it should be radiobuttons on UI
    private Gender gender;


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


    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = EnumSet.copyOf(roles);
    }

    @Override
    public String toString() {
        return "User{" +
                ", name='" + name + '\'' +
                ", email=" + email +
                ", birthday=" + birthday +
                ", gender=" + gender +
                '}';
    }
}
