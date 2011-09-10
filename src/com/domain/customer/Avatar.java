package com.domain.customer;

import java.io.Serializable;

/**
 * @author Stanislav Kurilin
 */
public class Avatar implements Serializable {
    private final byte[] content;
    private final String contentType;

    public Avatar(byte[] content, String contentType) {
        this.content = content;
        this.contentType = contentType;
    }

    public byte[] getContent() {
        return content;
    }

    public String getContentType() {
        return contentType;
    }
}
