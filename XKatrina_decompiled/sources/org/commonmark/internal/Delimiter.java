package org.commonmark.internal;

import java.util.List;
import org.commonmark.node.Text;
import org.commonmark.parser.delimiter.DelimiterRun;
/* loaded from: classes2.dex */
public class Delimiter implements DelimiterRun {
    private final boolean canClose;
    private final boolean canOpen;
    public final List<Text> characters;
    public final char delimiterChar;
    public Delimiter next;
    private final int originalLength;
    public Delimiter previous;

    public Delimiter(List<Text> characters, char delimiterChar, boolean canOpen, boolean canClose, Delimiter previous) {
        this.characters = characters;
        this.delimiterChar = delimiterChar;
        this.canOpen = canOpen;
        this.canClose = canClose;
        this.previous = previous;
        this.originalLength = characters.size();
    }

    @Override // org.commonmark.parser.delimiter.DelimiterRun
    public boolean canOpen() {
        return this.canOpen;
    }

    @Override // org.commonmark.parser.delimiter.DelimiterRun
    public boolean canClose() {
        return this.canClose;
    }

    @Override // org.commonmark.parser.delimiter.DelimiterRun
    public int length() {
        return this.characters.size();
    }

    @Override // org.commonmark.parser.delimiter.DelimiterRun
    public int originalLength() {
        return this.originalLength;
    }

    @Override // org.commonmark.parser.delimiter.DelimiterRun
    public Text getOpener() {
        return this.characters.get(this.characters.size() - 1);
    }

    @Override // org.commonmark.parser.delimiter.DelimiterRun
    public Text getCloser() {
        return this.characters.get(0);
    }

    @Override // org.commonmark.parser.delimiter.DelimiterRun
    public Iterable<Text> getOpeners(int length) {
        if (length < 1 || length > length()) {
            throw new IllegalArgumentException("length must be between 1 and " + length() + ", was " + length);
        }
        return this.characters.subList(this.characters.size() - length, this.characters.size());
    }

    @Override // org.commonmark.parser.delimiter.DelimiterRun
    public Iterable<Text> getClosers(int length) {
        if (length < 1 || length > length()) {
            throw new IllegalArgumentException("length must be between 1 and " + length() + ", was " + length);
        }
        return this.characters.subList(0, length);
    }
}
