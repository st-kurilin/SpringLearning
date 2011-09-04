package com.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author Stanislav Kurilin
 */
public class StreamCloser {
    public static void close(Closeable stream) {
        if (stream == null) {
            return;
        }
        try {
            stream.close();
        } catch (IOException ignore) {
        }
    }

    private StreamCloser() {
    }
}
