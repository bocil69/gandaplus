package org.commonmark.parser.block;

import org.commonmark.node.Block;
import org.commonmark.node.SourceSpan;
import org.commonmark.parser.InlineParser;
import org.commonmark.parser.SourceLine;
/* loaded from: classes2.dex */
public abstract class AbstractBlockParser implements BlockParser {
    @Override // org.commonmark.parser.block.BlockParser
    public boolean isContainer() {
        return false;
    }

    @Override // org.commonmark.parser.block.BlockParser
    public boolean canHaveLazyContinuationLines() {
        return false;
    }

    @Override // org.commonmark.parser.block.BlockParser
    public boolean canContain(Block childBlock) {
        return false;
    }

    @Override // org.commonmark.parser.block.BlockParser
    public void addLine(SourceLine line) {
    }

    @Override // org.commonmark.parser.block.BlockParser
    public void addSourceSpan(SourceSpan sourceSpan) {
        getBlock().addSourceSpan(sourceSpan);
    }

    @Override // org.commonmark.parser.block.BlockParser
    public void closeBlock() {
    }

    @Override // org.commonmark.parser.block.BlockParser
    public void parseInlines(InlineParser inlineParser) {
    }
}
