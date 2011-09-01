package com.domain.customer;

import net.sf.jmimemagic.*;

import java.util.Collection;
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
    static{
        List<String> templist=new LinkedList<String>();
        templist.add("image/bmp");
        templist.add("image/gif");
        templist.add("image/jpeg");
        templist.add("image/png");
        imageTypes=Collections.unmodifiableList(templist);
    }
    public Avatar(byte[] content)throws UnsupportedTypeException{

        try {
            String temp=Magic.getMagicMatch(content, false).getMimeType();
            if (imageTypes.contains(temp))
                mimeType=temp;
            else
                throw new UnsupportedTypeException("Only images allowed, not "+ temp);
        } catch (Exception e) {
            throw new UnsupportedTypeException(e.getMessage());
        }

        this.content = content;
    }

    public byte[] getContent() {
        return content;
    }
    public String getMimeType() {
        return mimeType;
    }
}
