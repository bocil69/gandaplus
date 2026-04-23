package org.commonmark.parser;

import org.commonmark.node.SourceSpan;
/* loaded from: classes2.dex */
public class SourceLine {
    private final CharSequence content;
    private final SourceSpan sourceSpan;

    public static SourceLine of(CharSequence content, SourceSpan sourceSpan) {
        return new SourceLine(content, sourceSpan);
    }

    private SourceLine(CharSequence content, SourceSpan sourceSpan) {
        if (content == null) {
            throw new NullPointerException("content must not be null");
        }
        this.content = content;
        this.sourceSpan = sourceSpan;
    }

    public CharSequence getContent() {
        return this.content;
    }

    public SourceSpan getSourceSpan() {
        return this.sourceSpan;
    }

    public SourceLine substring(int beginIndex, int endIndex) {
        CharSequence newContent = this.content.subSequence(beginIndex, endIndex);
        SourceSpan newSourceSpan = null;
        if (this.sourceSpan != null) {
            int columnIndex = this.sourceSpan.getColumnIndex() + beginIndex;
            int length = endIndex - beginIndex;
            if (length != 0) {
                newSourceSpan = SourceSpan.of(this.sourceSpan.getLineIndex(), columnIndex, length);
            }
        }
        return of(newContent, newSourceSpan);
    }
}
