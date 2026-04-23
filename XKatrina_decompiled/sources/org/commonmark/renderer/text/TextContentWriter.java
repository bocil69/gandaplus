package org.commonmark.renderer.text;

import java.io.IOException;
/* loaded from: classes2.dex */
public class TextContentWriter {
    private final Appendable buffer;
    private char lastChar;

    public TextContentWriter(Appendable out) {
        this.buffer = out;
    }

    public void whitespace() {
        if (this.lastChar != 0 && this.lastChar != ' ') {
            append(' ');
        }
    }

    public void colon() {
        if (this.lastChar != 0 && this.lastChar != ':') {
            append(':');
        }
    }

    public void line() {
        if (this.lastChar != 0 && this.lastChar != '\n') {
            append('\n');
        }
    }

    public void writeStripped(String s) {
        append(s.replaceAll("[\\r\\n\\s]+", " "));
    }

    public void write(String s) {
        append(s);
    }

    public void write(char c) {
        append(c);
    }

    private void append(String s) {
        try {
            this.buffer.append(s);
            int length = s.length();
            if (length != 0) {
                this.lastChar = s.charAt(length - 1);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void append(char c) {
        try {
            this.buffer.append(c);
            this.lastChar = c;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
