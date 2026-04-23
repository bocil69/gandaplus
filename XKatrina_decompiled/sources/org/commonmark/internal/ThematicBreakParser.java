package org.commonmark.internal;

import org.commonmark.node.Block;
import org.commonmark.node.ThematicBreak;
import org.commonmark.parser.block.AbstractBlockParser;
import org.commonmark.parser.block.AbstractBlockParserFactory;
import org.commonmark.parser.block.BlockContinue;
import org.commonmark.parser.block.BlockStart;
import org.commonmark.parser.block.MatchedBlockParser;
import org.commonmark.parser.block.ParserState;
/* loaded from: classes2.dex */
public class ThematicBreakParser extends AbstractBlockParser {
    private final ThematicBreak block = new ThematicBreak();

    @Override // org.commonmark.parser.block.BlockParser
    public Block getBlock() {
        return this.block;
    }

    @Override // org.commonmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState state) {
        return BlockContinue.none();
    }

    /* loaded from: classes2.dex */
    public static class Factory extends AbstractBlockParserFactory {
        @Override // org.commonmark.parser.block.BlockParserFactory
        public BlockStart tryStart(ParserState state, MatchedBlockParser matchedBlockParser) {
            if (state.getIndent() >= 4) {
                return BlockStart.none();
            }
            int nextNonSpace = state.getNextNonSpaceIndex();
            CharSequence line = state.getLine().getContent();
            return ThematicBreakParser.isThematicBreak(line, nextNonSpace) ? BlockStart.of(new ThematicBreakParser()).atIndex(line.length()) : BlockStart.none();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isThematicBreak(CharSequence line, int index) {
        int dashes = 0;
        int underscores = 0;
        int asterisks = 0;
        int length = line.length();
        for (int i = index; i < length; i++) {
            switch (line.charAt(i)) {
                case '\t':
                case ' ':
                    break;
                case '*':
                    asterisks++;
                    break;
                case '-':
                    dashes++;
                    break;
                case '_':
                    underscores++;
                    break;
                default:
                    return false;
            }
        }
        return (dashes >= 3 && underscores == 0 && asterisks == 0) || (underscores >= 3 && dashes == 0 && asterisks == 0) || (asterisks >= 3 && dashes == 0 && underscores == 0);
    }
}
