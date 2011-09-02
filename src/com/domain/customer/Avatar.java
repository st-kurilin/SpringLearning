package com.domain.customer;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Stanislav Kurilin
 */
public class Avatar implements Serializable{
    //TODO: move to binder
    private static final List<String> imageTypes;

    static {
        List<String> templist = new LinkedList<String>();
        templist.add("image/bmp");
        templist.add("image/gif");
        templist.add("image/jpeg");
        templist.add("image/png");
        imageTypes = Collections.unmodifiableList(templist);
    }

    private final byte[] content;
    private final String contentType;

    public Avatar(byte[] content, String contentType) {
        validateContentType(contentType);
        this.content = content;
        this.contentType = contentType;
    }

    private void validateContentType(String contentType) {
        if (!imageTypes.contains(contentType)) {
            throw new IllegalArgumentException("Content Type isn't supported: " + contentType);
        }
    }

    public byte[] getContent() {
        return content;
    }

    public String getContentType() {
        return contentType;
    }
}
