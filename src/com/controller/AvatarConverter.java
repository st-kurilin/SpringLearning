package com.controller;

import com.domain.customer.Avatar;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Stanislav Kurilin
 */
public class AvatarConverter implements ConverterFactory<MultipartFile, Avatar> {
    //, Converter<MultipartFile, Avatar> {
    /*@Override
    public Avatar convert(MultipartFile source) {
        if (source.isEmpty()) {
            return null;
        }
        try {
            return new Avatar(source.getBytes(), source.getContentType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    } */

    @Override
    public Converter<MultipartFile, Avatar> getConverter(Class targetType) {
        if (targetType.isAssignableFrom(MultipartFile.class)) {
            System.out.println("Catch " + targetType);
            return new Converter<MultipartFile, Avatar>() {
                @Override
                public Avatar convert
                        (MultipartFile
                                 source) {
                    if (source.isEmpty()) {
                        return null;
                    }
                    try {
                        return new Avatar(source.getBytes(), source.getContentType());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            };
        }
        System.out.println("dismiss " + targetType);
        return null;
    }
}
