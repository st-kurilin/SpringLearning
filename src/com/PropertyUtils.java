package com;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * @author Stanislav Kurilin
 */
public class PropertyUtils extends PropertyPlaceholderConfigurer {

    public static String getPropertyPath() {
        return System.getProperty("applicationProperties");
    }

    public void setLocation(Resource location) {
        String path = System.getProperty("applicationProperties");
        Resource resource = new FileSystemResource(path);
        super.setLocation(resource);
    }

}