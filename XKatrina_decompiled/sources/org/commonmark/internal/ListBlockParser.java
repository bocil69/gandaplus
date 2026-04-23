package org.commonmark.internal;

import org.commonmark.internal.util.Parsing;
import org.commonmark.node.Block;
import org.commonmark.node.BulletList;
import org.commonmark.node.ListBlock;
import org.commonmark.node.ListItem;
import org.commonmark.node.OrderedList;
import org.commonmark.parser.block.AbstractBlockParser;
import org.commonmark.parser.block.AbstractBlockParserFactory;
import org.commonmark.parser.block.BlockContinue;
import org.commonmark.parser.block.BlockParser;
import org.commonmark.parser.block.BlockStart;
import org.commonmark.parser.block.MatchedBlockParser;
import org.commonmark.parser.block.ParserState;
/* loaded from: classes2.dex */
public class ListBlockParser extends AbstractBlockParser {
    private final ListBlock block;
    private boolean hadBlankLine;
    private int linesAfterBlank;

    public ListBlockParser(ListBlock block) {
        this.block = block;
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public boolean isContainer() {
        return true;
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public boolean canContain(Block childBlock) {
        if (childBlock instanceof ListItem) {
            if (this.hadBlankLine && this.linesAfterBlank == 1) {
                this.block.setTight(false);
                this.hadBlankLine = false;
                return true;
            }
            return true;
        }
        return false;
    }

    @Override // org.commonmark.parser.block.BlockParser
    public Block getBlock() {
        return this.block;
    }

    @Override // org.commonmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState state) {
        if (state.isBlank()) {
            this.hadBlankLine = true;
            this.linesAfterBlank = 0;
        } else if (this.hadBlankLine) {
            this.linesAfterBlank++;
        }
        return BlockContinue.atIndex(state.getIndex());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ListData parseList(CharSequence line, int markerIndex, int markerColumn, boolean inParagraph) {
        ListMarkerData listMarker = parseListMarker(line, markerIndex);
        if (listMarker == null) {
            return null;
        }
        ListBlock listBlock = listMarker.listBlock;
        int indexAfterMarker = listMarker.indexAfterMarker;
        int markerLength = indexAfterMarker - markerIndex;
        int columnAfterMarker = markerColumn + markerLength;
        int contentColumn = columnAfterMarker;
        boolean hasContent = false;
        int length = line.length();
        int i = indexAfterMarker;
        while (true) {
            if (i >= length) {
                break;
            }
            char c = line.charAt(i);
            if (c == '\t') {
                contentColumn += Parsing.columnsToNextTabStop(contentColumn);
            } else if (c == ' ') {
                contentColumn++;
            } else {
                hasContent = true;
                break;
            }
            i++;
        }
        if (inParagraph && (((listBlock instanceof OrderedList) && ((OrderedList) listBlock).getStartNumber() != 1) || !hasContent)) {
            return null;
        }
        if (!hasContent || contentColumn - columnAfterMarker > Parsing.CODE_BLOCK_INDENT) {
            contentColumn = columnAfterMarker + 1;
        }
        return new ListData(listBlock, contentColumn);
    }

    private static ListMarkerData parseListMarker(CharSequence line, int index) {
        char c = line.charAt(index);
        switch (c) {
            case '*':
            case '+':
            case '-':
                if (isSpaceTabOrEnd(line, index + 1)) {
                    BulletList bulletList = new BulletList();
                    bulletList.setBulletMarker(c);
                    return new ListMarkerData(bulletList, index + 1);
                }
                return null;
            case ',':
            default:
                return parseOrderedList(line, index);
        }
    }

    private static ListMarkerData parseOrderedList(CharSequence line, int index) {
        int digits = 0;
        int length = line.length();
        for (int i = index; i < length; i++) {
            char c = line.charAt(i);
            switch (c) {
                case ')':
                case '.':
                    if (digits < 1 || !isSpaceTabOrEnd(line, i + 1)) {
                        return null;
                    }
                    String number = line.subSequence(index, i).toString();
                    OrderedList orderedList = new OrderedList();
                    orderedList.setStartNumber(Integer.parseInt(number));
                    orderedList.setDelimiter(c);
                    return new ListMarkerData(orderedList, i + 1);
                case '*':
                case '+':
                case ',':
                case '-':
                case '/':
                default:
                    return null;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    digits++;
                    if (digits > 9) {
                        return null;
                    }
            }
        }
        return null;
    }

    private static boolean isSpaceTabOrEnd(CharSequence line, int index) {
        if (index < line.length()) {
            switch (line.charAt(index)) {
                case '\t':
                case ' ':
                    return true;
                default:
                    return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean listsMatch(ListBlock a, ListBlock b) {
        if ((a instanceof BulletList) && (b instanceof BulletList)) {
            return equals(Character.valueOf(((BulletList) a).getBulletMarker()), Character.valueOf(((BulletList) b).getBulletMarker()));
        }
        if ((a instanceof OrderedList) && (b instanceof OrderedList)) {
            return equals(Character.valueOf(((OrderedList) a).getDelimiter()), Character.valueOf(((OrderedList) b).getDelimiter()));
        }
        return false;
    }

    private static boolean equals(Object a, Object b) {
        return a == null ? b == null : a.equals(b);
    }

    /* loaded from: classes2.dex */
    public static class Factory extends AbstractBlockParserFactory {
        @Override // org.commonmark.parser.block.BlockParserFactory
        public BlockStart tryStart(ParserState state, MatchedBlockParser matchedBlockParser) {
            BlockParser matched = matchedBlockParser.getMatchedBlockParser();
            if (state.getIndent() >= Parsing.CODE_BLOCK_INDENT) {
                return BlockStart.none();
            }
            int markerIndex = state.getNextNonSpaceIndex();
            int markerColumn = state.getColumn() + state.getIndent();
            boolean inParagraph = !matchedBlockParser.getParagraphLines().isEmpty();
            ListData listData = ListBlockParser.parseList(state.getLine().getContent(), markerIndex, markerColumn, inParagraph);
            if (listData == null) {
                return BlockStart.none();
            }
            int newColumn = listData.contentColumn;
            ListItemParser listItemParser = new ListItemParser(newColumn - state.getColumn());
            if ((matched instanceof ListBlockParser) && ListBlockParser.listsMatch((ListBlock) matched.getBlock(), listData.listBlock)) {
                return BlockStart.of(listItemParser).atColumn(newColumn);
            }
            ListBlockParser listBlockParser = new ListBlockParser(listData.listBlock);
            listData.listBlock.setTight(true);
            return BlockStart.of(listBlockParser, listItemParser).atColumn(newColumn);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ListData {
        final int contentColumn;
        final ListBlock listBlock;

        ListData(ListBlock listBlock, int contentColumn) {
            this.listBlock = listBlock;
            this.contentColumn = contentColumn;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ListMarkerData {
        final int indexAfterMarker;
        final ListBlock listBlock;

        ListMarkerData(ListBlock listBlock, int indexAfterMarker) {
            this.listBlock = listBlock;
            this.indexAfterMarker = indexAfterMarker;
        }
    }
}
