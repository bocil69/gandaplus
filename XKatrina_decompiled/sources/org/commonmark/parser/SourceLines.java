package org.commonmark.parser;

import java.util.ArrayList;
import java.util.List;
import org.commonmark.node.SourceSpan;
/* loaded from: classes2.dex */
public class SourceLines {
    private final List<SourceLine> lines = new ArrayList();

    public static SourceLines empty() {
        return new SourceLines();
    }

    public static SourceLines of(SourceLine sourceLine) {
        SourceLines sourceLines = new SourceLines();
        sourceLines.addLine(sourceLine);
        return sourceLines;
    }

    public static SourceLines of(List<SourceLine> sourceLines) {
        SourceLines result = new SourceLines();
        result.lines.addAll(sourceLines);
        return result;
    }

    public void addLine(SourceLine sourceLine) {
        this.lines.add(sourceLine);
    }

    public List<SourceLine> getLines() {
        return this.lines;
    }

    public boolean isEmpty() {
        return this.lines.isEmpty();
    }

    public String getContent() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.lines.size(); i++) {
            if (i != 0) {
                sb.append('\n');
            }
            sb.append(this.lines.get(i).getContent());
        }
        return sb.toString();
    }

    public List<SourceSpan> getSourceSpans() {
        List<SourceSpan> sourceSpans = new ArrayList<>();
        for (SourceLine line : this.lines) {
            SourceSpan sourceSpan = line.getSourceSpan();
            if (sourceSpan != null) {
                sourceSpans.add(sourceSpan);
            }
        }
        return sourceSpans;
    }
}
