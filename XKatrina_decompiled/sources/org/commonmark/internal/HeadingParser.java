package org.commonmark.internal;

import org.commonmark.internal.inline.Position;
import org.commonmark.internal.inline.Scanner;
import org.commonmark.internal.util.Parsing;
import org.commonmark.node.Block;
import org.commonmark.node.Heading;
import org.commonmark.parser.InlineParser;
import org.commonmark.parser.SourceLine;
import org.commonmark.parser.SourceLines;
import org.commonmark.parser.block.AbstractBlockParser;
import org.commonmark.parser.block.AbstractBlockParserFactory;
import org.commonmark.parser.block.BlockContinue;
import org.commonmark.parser.block.BlockStart;
import org.commonmark.parser.block.MatchedBlockParser;
import org.commonmark.parser.block.ParserState;
/* loaded from: classes2.dex */
public class HeadingParser extends AbstractBlockParser {
    private final Heading block = new Heading();
    private final SourceLines content;

    public HeadingParser(int level, SourceLines content) {
        this.block.setLevel(level);
        this.content = content;
    }

    @Override // org.commonmark.parser.block.BlockParser
    public Block getBlock() {
        return this.block;
    }

    @Override // org.commonmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState parserState) {
        return BlockContinue.none();
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public void parseInlines(InlineParser inlineParser) {
        inlineParser.parse(this.content, this.block);
    }

    /* loaded from: classes2.dex */
    public static class Factory extends AbstractBlockParserFactory {
        @Override // org.commonmark.parser.block.BlockParserFactory
        public BlockStart tryStart(ParserState state, MatchedBlockParser matchedBlockParser) {
            HeadingParser atxHeading;
            if (state.getIndent() >= Parsing.CODE_BLOCK_INDENT) {
                return BlockStart.none();
            }
            SourceLine line = state.getLine();
            int nextNonSpace = state.getNextNonSpaceIndex();
            if (line.getContent().charAt(nextNonSpace) != '#' || (atxHeading = HeadingParser.getAtxHeading(line.substring(nextNonSpace, line.getContent().length()))) == null) {
                int setextHeadingLevel = HeadingParser.getSetextHeadingLevel(line.getContent(), nextNonSpace);
                if (setextHeadingLevel > 0) {
                    SourceLines paragraph = matchedBlockParser.getParagraphLines();
                    if (!paragraph.isEmpty()) {
                        return BlockStart.of(new HeadingParser(setextHeadingLevel, paragraph)).atIndex(line.getContent().length()).replaceActiveBlockParser();
                    }
                }
                return BlockStart.none();
            }
            return BlockStart.of(atxHeading).atIndex(line.getContent().length());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static HeadingParser getAtxHeading(SourceLine line) {
        Scanner scanner = Scanner.of(SourceLines.of(line));
        int level = scanner.matchMultiple('#');
        if (level == 0 || level > 6) {
            return null;
        }
        if (!scanner.hasNext()) {
            return new HeadingParser(level, SourceLines.empty());
        }
        char next = scanner.peek();
        if (next == ' ' || next == '\t') {
            scanner.whitespace();
            Position start = scanner.position();
            Position end = start;
            boolean hashCanEnd = true;
            while (scanner.hasNext()) {
                char c = scanner.peek();
                switch (c) {
                    case '\t':
                    case ' ':
                        hashCanEnd = true;
                        scanner.next();
                        break;
                    case '#':
                        if (hashCanEnd) {
                            scanner.matchMultiple('#');
                            int whitespace = scanner.whitespace();
                            if (scanner.hasNext()) {
                                end = scanner.position();
                            }
                            if (whitespace <= 0) {
                                hashCanEnd = false;
                                break;
                            } else {
                                hashCanEnd = true;
                                break;
                            }
                        } else {
                            scanner.next();
                            end = scanner.position();
                            break;
                        }
                    default:
                        hashCanEnd = false;
                        scanner.next();
                        end = scanner.position();
                        break;
                }
            }
            SourceLines source = scanner.getSource(start, end);
            String content = source.getContent();
            if (content.isEmpty()) {
                return new HeadingParser(level, SourceLines.empty());
            }
            return new HeadingParser(level, source);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x001f A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:4:0x0007 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int getSetextHeadingLevel(java.lang.CharSequence r2, int r3) {
        /*
            char r0 = r2.charAt(r3)
            switch(r0) {
                case 45: goto L15;
                case 61: goto L9;
                default: goto L7;
            }
        L7:
            r0 = 0
        L8:
            return r0
        L9:
            int r0 = r3 + 1
            r1 = 61
            boolean r0 = isSetextHeadingRest(r2, r0, r1)
            if (r0 == 0) goto L15
            r0 = 1
            goto L8
        L15:
            int r0 = r3 + 1
            r1 = 45
            boolean r0 = isSetextHeadingRest(r2, r0, r1)
            if (r0 == 0) goto L7
            r0 = 2
            goto L8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.commonmark.internal.HeadingParser.getSetextHeadingLevel(java.lang.CharSequence, int):int");
    }

    private static boolean isSetextHeadingRest(CharSequence line, int index, char marker) {
        int afterMarker = Parsing.skip(marker, line, index, line.length());
        int afterSpace = Parsing.skipSpaceTab(line, afterMarker, line.length());
        return afterSpace >= line.length();
    }
}
