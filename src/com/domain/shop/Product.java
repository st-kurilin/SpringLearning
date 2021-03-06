package com.domain.shop;

import com.domain.commerce.Money;
import com.domain.customer.User;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;

/**
 * @author Stanislav Kurilin
 */
@Entity
@Table(name = "PRODUCTS")
public class Product {
    @NotBlank
    @Id
    private String title;

    @Valid
    private Money price;

    private String description;

    @ManyToOne
    private User seller;

    public Product() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User user) {
        this.seller = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //TODO: [stas] should contain  list of photo.
    //photo should be uploaded through ajax
    //first title should be used as id (you can edit title, but can't change id)
    //price should be placed in separate immutable class
    /*
    * show products only of this user
    * Add on market
    * filter
    * */
}
