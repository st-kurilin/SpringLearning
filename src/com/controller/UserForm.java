package com.controller;

import com.domain.customer.Avatar;
import com.domain.customer.User;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * @author Stanislav Kurilin
 */
public class UserForm {
    @Valid
    private User user;
    @Valid
    private Avatar avatar;

    public UserForm() {
    }

    public UserForm(User user, Avatar avatar) {
        this.user = user;
        this.avatar = avatar;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
