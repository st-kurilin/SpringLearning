package com.domain.customer;

/**
 * @author Stanislav Kurilin
 */
public class Avatar {
    private final byte[] content;

    public Avatar(byte[] content) {
        this.content = content;
    }

    public byte[] getContent() {
        return content;
    }
}
