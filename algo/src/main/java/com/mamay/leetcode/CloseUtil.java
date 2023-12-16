package com.mamay.leetcode;

import java.io.Closeable;
import java.io.IOException;

class CloseUtil {
    public void close(Closeable closeable) throws IOException {
        try {
            closeable.close();
        } catch (IOException e) {
            closeable.close();
        }
    }
}
