package com.domain.commerce;

import com.domain.customer.User;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Stanislav Kurilin
 */
@Entity
@Table(name = "TRANSACTION")
public class Transaction extends AbstractPersistable<Long> {
    @ManyToOne
    private User from;
    @ManyToOne
    private User to;
    private Money amount;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private String comment;

    @Transient
    private Boolean editable;

    public Transaction() {
    }

    @PostLoad
    public void normalize() {
        final Calendar calendar = GregorianCalendar.getInstance();
        calendar.roll(1, Calendar.WEEK_OF_YEAR);
        final Date weekAgo = calendar.getTime();
        editable = date.after(weekAgo);
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean isEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }
}

