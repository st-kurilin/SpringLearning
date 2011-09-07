package com.domain.shop;

import com.domain.customer.User;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Stanislav Kurilin
 */
@Entity
public class Product extends AbstractPersistable<Long> {
    @NotBlank
    private String title;

    @NotNull
    private BigDecimal price;

    private String description;

    @ManyToOne
    private User user;

    public Product() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //TODO: [stas] should contain title, seller, description, list of photo, price.
    //photo should be uploaded through ajax
    //first title should be used as id (you can edit title, but can't change id)
    //price should be placed in separate immutable class
    /*
    * show products only of this user
    * paginator
    * filter
    * */
}
