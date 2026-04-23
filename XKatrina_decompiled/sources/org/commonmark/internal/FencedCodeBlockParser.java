package org.commonmark.internal;

import org.commonmark.internal.util.Escaping;
import org.commonmark.internal.util.Parsing;
import org.commonmark.node.Block;
import org.commonmark.node.FencedCodeBlock;
import org.commonmark.parser.SourceLine;
import org.commonmark.parser.block.AbstractBlockParser;
import org.commonmark.parser.block.AbstractBlockParserFactory;
import org.commonmark.parser.block.BlockContinue;
import org.commonmark.parser.block.BlockStart;
import org.commonmark.parser.block.MatchedBlockParser;
import org.commonmark.parser.block.ParserState;
/* loaded from: classes2.dex */
public class FencedCodeBlockParser extends AbstractBlockParser {
    private String firstLine;
    private final FencedCodeBlock block = new FencedCodeBlock();
    private StringBuilder otherLines = new StringBuilder();

    public FencedCodeBlockParser(char fenceChar, int fenceLength, int fenceIndent) {
        this.block.setFenceChar(fenceChar);
        this.block.setFenceLength(fenceLength);
        this.block.setFenceIndent(fenceIndent);
    }

    @Override // org.commonmark.parser.block.BlockParser
    public Block getBlock() {
        return this.block;
    }

    @Override // org.commonmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState state) {
        int nextNonSpace = state.getNextNonSpaceIndex();
        int newIndex = state.getIndex();
        CharSequence line = state.getLine().getContent();
        if (state.getIndent() < Parsing.CODE_BLOCK_INDENT && nextNonSpace < line.length() && line.charAt(nextNonSpace) == this.block.getFenceChar() && isClosing(line, nextNonSpace)) {
            return BlockContinue.finished();
        }
        int length = line.length();
        for (int i = this.block.getFenceIndent(); i > 0 && newIndex < length && line.charAt(newIndex) == ' '; i--) {
            newIndex++;
        }
        return BlockContinue.atIndex(newIndex);
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public void addLine(SourceLine line) {
        if (this.firstLine == null) {
            this.firstLine = line.getContent().toString();
            return;
        }
        this.otherLines.append(line.getContent());
        this.otherLines.append('\n');
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public void closeBlock() {
        this.block.setInfo(Escaping.unescapeString(this.firstLine.trim()));
        this.block.setLiteral(this.otherLines.toString());
    }

    /* loaded from: classes2.dex */
    public static class Factory extends AbstractBlockParserFactory {
        @Override // org.commonmark.parser.block.BlockParserFactory
        public BlockStart tryStart(ParserState state, MatchedBlockParser matchedBlockParser) {
            int indent = state.getIndent();
            if (indent >= Parsing.CODE_BLOCK_INDENT) {
                return BlockStart.none();
            }
            int nextNonSpace = state.getNextNonSpaceIndex();
            FencedCodeBlockParser blockParser = FencedCodeBlockParser.checkOpener(state.getLine().getContent(), nextNonSpace, indent);
            if (blockParser != null) {
                return BlockStart.of(blockParser).atIndex(blockParser.block.getFenceLength() + nextNonSpace);
            }
            return BlockStart.none();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static FencedCodeBlockParser checkOpener(CharSequence line, int index, int indent) {
        int backticks = 0;
        int tildes = 0;
        int length = line.length();
        for (int i = index; i < length; i++) {
            switch (line.charAt(i)) {
                case '`':
                    backticks++;
                    break;
                case '~':
                    tildes++;
                    break;
                default:
                    if (backticks < 3 && tildes == 0) {
                        if (Parsing.find('`', line, index + backticks) != -1) {
                            return null;
                        } else {
                            return new FencedCodeBlockParser('`', backticks, indent);
                        }
                    }
                    if (tildes < 3 && backticks == 0) {
                        return new FencedCodeBlockParser('~', tildes, indent);
                    }
            }
        }
        if (backticks < 3) {
        }
        return tildes < 3 ? null : null;
    }

    private boolean isClosing(CharSequence line, int index) {
        char fenceChar = this.block.getFenceChar();
        int fenceLength = this.block.getFenceLength();
        int fences = Parsing.skip(fenceChar, line, index, line.length()) - index;
        if (fences < fenceLength) {
            return false;
        }
        int after = Parsing.skipSpaceTab(line, index + fences, line.length());
        return after == line.length();
    }
}
