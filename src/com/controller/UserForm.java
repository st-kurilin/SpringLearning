package com.controller;

import com.domain.customer.Avatar;
import com.domain.customer.User;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.Valid;

/**
 * @author Stanislav Kurilin
 */
public class UserForm {
    @Valid
    private User user;
    @Valid
    private CommonsMultipartFile avatarFile;

    public UserForm() {
    }

    public UserForm(User user, CommonsMultipartFile avatarFile) {
        this.user = user;
        this.avatarFile = avatarFile;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CommonsMultipartFile getAvatarFile() {
        return avatarFile;
    }

    public void setAvatarFile(CommonsMultipartFile avatarFile) {
        this.avatarFile = avatarFile;
    }

    public Avatar getAvatar() {
        if (avatarFile.isEmpty()) {
            return null;
        }
        return new Avatar(avatarFile.getBytes(), avatarFile.getContentType());
    }

}
