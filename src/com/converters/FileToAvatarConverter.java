package com.converters;

import com.domain.customer.Avatar;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author: Andrey Loboda
 * @date : 02.09.11
 */
public class FileToAvatarConverter implements Converter<CommonsMultipartFile,Avatar>{
    @Override
    public Avatar convert(CommonsMultipartFile file){
        if (file!=null){
            return null;
        }
        return new Avatar(file.getBytes(),file.getContentType());
    }
}
