package com.domain.customer;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Stanislav Kurilin
 */
public class Avatar {
    private final byte[] content;
    private final String mimeType;
    private static final List<String> imageTypes;

    static {
        List<String> templist = new LinkedList<String>();
        templist.add("image/bmp");
        templist.add("image/gif");
        templist.add("image/jpeg");
        templist.add("image/png");
        imageTypes = Collections.unmodifiableList(templist);
    }

    public Avatar(MultipartFile file) {
        String temp = file.getContentType();
        if (imageTypes.contains(temp)) {
            try {
                content = file.getBytes();
            } catch (IOException e) {
                throw new RuntimeException("Wrong file");
            }
            mimeType = temp;
        } else
            throw new RuntimeException("Only images allowed, not " + temp);
    }

    public byte[] getContent() {
        return content;
    }

    public String getMimeType() {
        return mimeType;
    }
}
