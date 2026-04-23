package org.commonmark.internal;

import java.util.List;
import org.commonmark.node.Block;
import org.commonmark.node.LinkReferenceDefinition;
import org.commonmark.node.Paragraph;
import org.commonmark.node.SourceSpan;
import org.commonmark.parser.InlineParser;
import org.commonmark.parser.SourceLine;
import org.commonmark.parser.SourceLines;
import org.commonmark.parser.block.AbstractBlockParser;
import org.commonmark.parser.block.BlockContinue;
import org.commonmark.parser.block.ParserState;
/* loaded from: classes2.dex */
public class ParagraphParser extends AbstractBlockParser {
    private final Paragraph block = new Paragraph();
    private final LinkReferenceDefinitionParser linkReferenceDefinitionParser = new LinkReferenceDefinitionParser();

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public boolean canHaveLazyContinuationLines() {
        return true;
    }

    @Override // org.commonmark.parser.block.BlockParser
    public Block getBlock() {
        return this.block;
    }

    @Override // org.commonmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState state) {
        return !state.isBlank() ? BlockContinue.atIndex(state.getIndex()) : BlockContinue.none();
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public void addLine(SourceLine line) {
        this.linkReferenceDefinitionParser.parse(line);
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public void addSourceSpan(SourceSpan sourceSpan) {
        this.linkReferenceDefinitionParser.addSourceSpan(sourceSpan);
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public void closeBlock() {
        if (this.linkReferenceDefinitionParser.getParagraphLines().isEmpty()) {
            this.block.unlink();
        } else {
            this.block.setSourceSpans(this.linkReferenceDefinitionParser.getParagraphSourceSpans());
        }
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public void parseInlines(InlineParser inlineParser) {
        SourceLines lines = this.linkReferenceDefinitionParser.getParagraphLines();
        if (!lines.isEmpty()) {
            inlineParser.parse(lines, this.block);
        }
    }

    public SourceLines getParagraphLines() {
        return this.linkReferenceDefinitionParser.getParagraphLines();
    }

    public List<LinkReferenceDefinition> getDefinitions() {
        return this.linkReferenceDefinitionParser.getDefinitions();
    }
}
