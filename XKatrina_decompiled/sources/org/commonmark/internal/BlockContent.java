package org.commonmark.internal;
/* loaded from: classes2.dex */
class BlockContent {
    private int lineCount;
    private final StringBuilder sb;

    public BlockContent() {
        this.lineCount = 0;
        this.sb = new StringBuilder();
    }

    public BlockContent(String content) {
        this.lineCount = 0;
        this.sb = new StringBuilder(content);
    }

    public void add(CharSequence line) {
        if (this.lineCount != 0) {
            this.sb.append('\n');
        }
        this.sb.append(line);
        this.lineCount++;
    }

    public String getString() {
        return this.sb.toString();
    }
}
