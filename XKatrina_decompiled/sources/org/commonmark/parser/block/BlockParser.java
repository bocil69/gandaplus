package org.commonmark.parser.block;

import org.commonmark.node.Block;
import org.commonmark.node.SourceSpan;
import org.commonmark.parser.InlineParser;
import org.commonmark.parser.SourceLine;
/* loaded from: classes2.dex */
public interface BlockParser {
    void addLine(SourceLine sourceLine);

    void addSourceSpan(SourceSpan sourceSpan);

    boolean canContain(Block block);

    boolean canHaveLazyContinuationLines();

    void closeBlock();

    Block getBlock();

    boolean isContainer();

    void parseInlines(InlineParser inlineParser);

    BlockContinue tryContinue(ParserState parserState);
}
