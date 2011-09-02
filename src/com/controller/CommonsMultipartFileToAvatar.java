package com.controller;

import com.domain.customer.Avatar;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author Stanislav Kurilin
 */
@Component
public class CommonsMultipartFileToAvatar implements Converter<CommonsMultipartFile, Avatar> {
    @Override
    public Avatar convert(CommonsMultipartFile source) {
        return new Avatar(source.getBytes(), source.getContentType());
    }
}
