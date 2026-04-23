package org.commonmark.internal;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import org.commonmark.parser.delimiter.DelimiterProcessor;
import org.commonmark.parser.delimiter.DelimiterRun;
/* loaded from: classes2.dex */
class StaggeredDelimiterProcessor implements DelimiterProcessor {
    private final char delim;
    private int minLength = 0;
    private LinkedList<DelimiterProcessor> processors = new LinkedList<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public StaggeredDelimiterProcessor(char delim) {
        this.delim = delim;
    }

    @Override // org.commonmark.parser.delimiter.DelimiterProcessor
    public char getOpeningCharacter() {
        return this.delim;
    }

    @Override // org.commonmark.parser.delimiter.DelimiterProcessor
    public char getClosingCharacter() {
        return this.delim;
    }

    @Override // org.commonmark.parser.delimiter.DelimiterProcessor
    public int getMinLength() {
        return this.minLength;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void add(DelimiterProcessor dp) {
        int pLen;
        int len = dp.getMinLength();
        ListIterator<DelimiterProcessor> it = this.processors.listIterator();
        boolean added = false;
        do {
            if (it.hasNext()) {
                DelimiterProcessor p = it.next();
                pLen = p.getMinLength();
                if (len > pLen) {
                    it.previous();
                    it.add(dp);
                    added = true;
                }
            }
            if (!added) {
                this.processors.add(dp);
                this.minLength = len;
                return;
            }
            return;
        } while (len != pLen);
        throw new IllegalArgumentException("Cannot add two delimiter processors for char '" + this.delim + "' and minimum length " + len);
    }

    private DelimiterProcessor findProcessor(int len) {
        Iterator<DelimiterProcessor> it = this.processors.iterator();
        while (it.hasNext()) {
            DelimiterProcessor p = it.next();
            if (p.getMinLength() <= len) {
                return p;
            }
        }
        return this.processors.getFirst();
    }

    @Override // org.commonmark.parser.delimiter.DelimiterProcessor
    public int process(DelimiterRun openingRun, DelimiterRun closingRun) {
        return findProcessor(openingRun.length()).process(openingRun, closingRun);
    }
}
