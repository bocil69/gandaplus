package org.commonmark.internal.inline;

import org.commonmark.node.Emphasis;
import org.commonmark.node.Node;
import org.commonmark.node.Nodes;
import org.commonmark.node.SourceSpans;
import org.commonmark.node.StrongEmphasis;
import org.commonmark.node.Text;
import org.commonmark.parser.delimiter.DelimiterProcessor;
import org.commonmark.parser.delimiter.DelimiterRun;
/* loaded from: classes2.dex */
public abstract class EmphasisDelimiterProcessor implements DelimiterProcessor {
    private final char delimiterChar;

    /* JADX INFO: Access modifiers changed from: protected */
    public EmphasisDelimiterProcessor(char delimiterChar) {
        this.delimiterChar = delimiterChar;
    }

    @Override // org.commonmark.parser.delimiter.DelimiterProcessor
    public char getOpeningCharacter() {
        return this.delimiterChar;
    }

    @Override // org.commonmark.parser.delimiter.DelimiterProcessor
    public char getClosingCharacter() {
        return this.delimiterChar;
    }

    @Override // org.commonmark.parser.delimiter.DelimiterProcessor
    public int getMinLength() {
        return 1;
    }

    @Override // org.commonmark.parser.delimiter.DelimiterProcessor
    public int process(DelimiterRun openingRun, DelimiterRun closingRun) {
        int usedDelimiters;
        Node emphasis;
        if ((openingRun.canClose() || closingRun.canOpen()) && closingRun.originalLength() % 3 != 0 && (openingRun.originalLength() + closingRun.originalLength()) % 3 == 0) {
            return 0;
        }
        if (openingRun.length() >= 2 && closingRun.length() >= 2) {
            usedDelimiters = 2;
            emphasis = new StrongEmphasis(String.valueOf(this.delimiterChar) + this.delimiterChar);
        } else {
            usedDelimiters = 1;
            emphasis = new Emphasis(String.valueOf(this.delimiterChar));
        }
        SourceSpans sourceSpans = SourceSpans.empty();
        sourceSpans.addAllFrom(openingRun.getOpeners(usedDelimiters));
        Text opener = openingRun.getOpener();
        for (Node node : Nodes.between(opener, closingRun.getCloser())) {
            emphasis.appendChild(node);
            sourceSpans.addAll(node.getSourceSpans());
        }
        sourceSpans.addAllFrom(closingRun.getClosers(usedDelimiters));
        emphasis.setSourceSpans(sourceSpans.getSourceSpans());
        opener.insertAfter(emphasis);
        return usedDelimiters;
    }
}
