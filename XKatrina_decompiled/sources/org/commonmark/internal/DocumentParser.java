package org.commonmark.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.commonmark.internal.BlockQuoteParser;
import org.commonmark.internal.FencedCodeBlockParser;
import org.commonmark.internal.HeadingParser;
import org.commonmark.internal.HtmlBlockParser;
import org.commonmark.internal.IndentedCodeBlockParser;
import org.commonmark.internal.ListBlockParser;
import org.commonmark.internal.ThematicBreakParser;
import org.commonmark.internal.util.Parsing;
import org.commonmark.node.Block;
import org.commonmark.node.BlockQuote;
import org.commonmark.node.Document;
import org.commonmark.node.FencedCodeBlock;
import org.commonmark.node.Heading;
import org.commonmark.node.HtmlBlock;
import org.commonmark.node.IndentedCodeBlock;
import org.commonmark.node.LinkReferenceDefinition;
import org.commonmark.node.ListBlock;
import org.commonmark.node.Paragraph;
import org.commonmark.node.SourceSpan;
import org.commonmark.node.ThematicBreak;
import org.commonmark.parser.IncludeSourceSpans;
import org.commonmark.parser.InlineParser;
import org.commonmark.parser.InlineParserFactory;
import org.commonmark.parser.SourceLine;
import org.commonmark.parser.SourceLines;
import org.commonmark.parser.block.BlockContinue;
import org.commonmark.parser.block.BlockParser;
import org.commonmark.parser.block.BlockParserFactory;
import org.commonmark.parser.block.BlockStart;
import org.commonmark.parser.block.MatchedBlockParser;
import org.commonmark.parser.block.ParserState;
import org.commonmark.parser.delimiter.DelimiterProcessor;
/* loaded from: classes2.dex */
public class DocumentParser implements ParserState {
    private static final Set<Class<? extends Block>> CORE_FACTORY_TYPES = new LinkedHashSet(Arrays.asList(BlockQuote.class, Heading.class, FencedCodeBlock.class, HtmlBlock.class, ThematicBreak.class, ListBlock.class, IndentedCodeBlock.class));
    private static final Map<Class<? extends Block>, BlockParserFactory> NODES_TO_CORE_FACTORIES;
    private boolean blank;
    private final List<BlockParserFactory> blockParserFactories;
    private boolean columnIsInTab;
    private final List<DelimiterProcessor> delimiterProcessors;
    private final IncludeSourceSpans includeSourceSpans;
    private final InlineParserFactory inlineParserFactory;
    private SourceLine line;
    private int lineIndex = -1;
    private int index = 0;
    private int column = 0;
    private int nextNonSpace = 0;
    private int nextNonSpaceColumn = 0;
    private int indent = 0;
    private final LinkReferenceDefinitions definitions = new LinkReferenceDefinitions();
    private final List<OpenBlockParser> openBlockParsers = new ArrayList();
    private final List<BlockParser> allBlockParsers = new ArrayList();
    private final DocumentBlockParser documentBlockParser = new DocumentBlockParser();

    static {
        Map<Class<? extends Block>, BlockParserFactory> map = new HashMap<>();
        map.put(BlockQuote.class, new BlockQuoteParser.Factory());
        map.put(Heading.class, new HeadingParser.Factory());
        map.put(FencedCodeBlock.class, new FencedCodeBlockParser.Factory());
        map.put(HtmlBlock.class, new HtmlBlockParser.Factory());
        map.put(ThematicBreak.class, new ThematicBreakParser.Factory());
        map.put(ListBlock.class, new ListBlockParser.Factory());
        map.put(IndentedCodeBlock.class, new IndentedCodeBlockParser.Factory());
        NODES_TO_CORE_FACTORIES = Collections.unmodifiableMap(map);
    }

    public DocumentParser(List<BlockParserFactory> blockParserFactories, InlineParserFactory inlineParserFactory, List<DelimiterProcessor> delimiterProcessors, IncludeSourceSpans includeSourceSpans) {
        this.blockParserFactories = blockParserFactories;
        this.inlineParserFactory = inlineParserFactory;
        this.delimiterProcessors = delimiterProcessors;
        this.includeSourceSpans = includeSourceSpans;
        activateBlockParser(new OpenBlockParser(this.documentBlockParser, 0));
    }

    public static Set<Class<? extends Block>> getDefaultBlockParserTypes() {
        return CORE_FACTORY_TYPES;
    }

    public static List<BlockParserFactory> calculateBlockParserFactories(List<BlockParserFactory> customBlockParserFactories, Set<Class<? extends Block>> enabledBlockTypes) {
        List<BlockParserFactory> list = new ArrayList<>();
        list.addAll(customBlockParserFactories);
        for (Class<? extends Block> blockType : enabledBlockTypes) {
            list.add(NODES_TO_CORE_FACTORIES.get(blockType));
        }
        return list;
    }

    public Document parse(String input) {
        int lineStart = 0;
        while (true) {
            int lineBreak = Parsing.findLineBreak(input, lineStart);
            if (lineBreak == -1) {
                break;
            }
            String line = input.substring(lineStart, lineBreak);
            parseLine(line);
            if (lineBreak + 1 < input.length() && input.charAt(lineBreak) == '\r' && input.charAt(lineBreak + 1) == '\n') {
                lineStart = lineBreak + 2;
            } else {
                lineStart = lineBreak + 1;
            }
        }
        if (input.length() > 0 && (lineStart == 0 || lineStart < input.length())) {
            String line2 = input.substring(lineStart);
            parseLine(line2);
        }
        return finalizeAndProcess();
    }

    public Document parse(Reader input) throws IOException {
        BufferedReader bufferedReader;
        if (input instanceof BufferedReader) {
            bufferedReader = (BufferedReader) input;
        } else {
            bufferedReader = new BufferedReader(input);
        }
        while (true) {
            String line = bufferedReader.readLine();
            if (line != null) {
                parseLine(line);
            } else {
                return finalizeAndProcess();
            }
        }
    }

    @Override // org.commonmark.parser.block.ParserState
    public SourceLine getLine() {
        return this.line;
    }

    @Override // org.commonmark.parser.block.ParserState
    public int getIndex() {
        return this.index;
    }

    @Override // org.commonmark.parser.block.ParserState
    public int getNextNonSpaceIndex() {
        return this.nextNonSpace;
    }

    @Override // org.commonmark.parser.block.ParserState
    public int getColumn() {
        return this.column;
    }

    @Override // org.commonmark.parser.block.ParserState
    public int getIndent() {
        return this.indent;
    }

    @Override // org.commonmark.parser.block.ParserState
    public boolean isBlank() {
        return this.blank;
    }

    @Override // org.commonmark.parser.block.ParserState
    public BlockParser getActiveBlockParser() {
        return this.openBlockParsers.get(this.openBlockParsers.size() - 1).blockParser;
    }

    private void parseLine(CharSequence ln) {
        BlockParser[] blockParsers;
        setLine(ln);
        int matches = 1;
        for (int i = 1; i < this.openBlockParsers.size(); i++) {
            OpenBlockParser openBlockParser = this.openBlockParsers.get(i);
            BlockParser blockParser = openBlockParser.blockParser;
            findNextNonSpace();
            BlockContinue result = blockParser.tryContinue(this);
            if (!(result instanceof BlockContinueImpl)) {
                break;
            }
            BlockContinueImpl blockContinue = (BlockContinueImpl) result;
            openBlockParser.sourceIndex = getIndex();
            if (blockContinue.isFinalize()) {
                addSourceSpans();
                closeBlockParsers(this.openBlockParsers.size() - i);
                return;
            }
            if (blockContinue.getNewIndex() != -1) {
                setNewIndex(blockContinue.getNewIndex());
            } else if (blockContinue.getNewColumn() != -1) {
                setNewColumn(blockContinue.getNewColumn());
            }
            matches++;
        }
        int unmatchedBlocks = this.openBlockParsers.size() - matches;
        BlockParser blockParser2 = this.openBlockParsers.get(matches - 1).blockParser;
        boolean startedNewBlock = false;
        int lastIndex = this.index;
        boolean tryBlockStarts = (blockParser2.getBlock() instanceof Paragraph) || blockParser2.isContainer();
        while (true) {
            if (!tryBlockStarts) {
                break;
            }
            lastIndex = this.index;
            findNextNonSpace();
            if (isBlank() || (this.indent < Parsing.CODE_BLOCK_INDENT && Parsing.isLetter(this.line.getContent(), this.nextNonSpace))) {
                break;
            }
            BlockStartImpl blockStart = findBlockStart(blockParser2);
            if (blockStart == null) {
                setNewIndex(this.nextNonSpace);
                break;
            }
            startedNewBlock = true;
            int sourceIndex = getIndex();
            if (unmatchedBlocks > 0) {
                closeBlockParsers(unmatchedBlocks);
                unmatchedBlocks = 0;
            }
            if (blockStart.getNewIndex() != -1) {
                setNewIndex(blockStart.getNewIndex());
            } else if (blockStart.getNewColumn() != -1) {
                setNewColumn(blockStart.getNewColumn());
            }
            List<SourceSpan> replacedSourceSpans = null;
            if (blockStart.isReplaceActiveBlockParser()) {
                Block replacedBlock = prepareActiveBlockParserForReplacement();
                replacedSourceSpans = replacedBlock.getSourceSpans();
            }
            for (BlockParser newBlockParser : blockStart.getBlockParsers()) {
                addChild(new OpenBlockParser(newBlockParser, sourceIndex));
                if (replacedSourceSpans != null) {
                    newBlockParser.getBlock().setSourceSpans(replacedSourceSpans);
                }
                blockParser2 = newBlockParser;
                tryBlockStarts = newBlockParser.isContainer();
            }
        }
        setNewIndex(this.nextNonSpace);
        if (startedNewBlock || isBlank() || !getActiveBlockParser().canHaveLazyContinuationLines()) {
            if (unmatchedBlocks > 0) {
                closeBlockParsers(unmatchedBlocks);
            }
            if (!blockParser2.isContainer()) {
                addLine();
                return;
            } else if (!isBlank()) {
                ParagraphParser paragraphParser = new ParagraphParser();
                addChild(new OpenBlockParser(paragraphParser, lastIndex));
                addLine();
                return;
            } else {
                addSourceSpans();
                return;
            }
        }
        this.openBlockParsers.get(this.openBlockParsers.size() - 1).sourceIndex = lastIndex;
        addLine();
    }

    private void setLine(CharSequence ln) {
        this.lineIndex++;
        this.index = 0;
        this.column = 0;
        this.columnIsInTab = false;
        CharSequence lineContent = Parsing.prepareLine(ln);
        SourceSpan sourceSpan = null;
        if (this.includeSourceSpans != IncludeSourceSpans.NONE) {
            sourceSpan = SourceSpan.of(this.lineIndex, 0, lineContent.length());
        }
        this.line = SourceLine.of(lineContent, sourceSpan);
    }

    private void findNextNonSpace() {
        int i = this.index;
        int cols = this.column;
        this.blank = true;
        int length = this.line.getContent().length();
        while (true) {
            if (i < length) {
                char c = this.line.getContent().charAt(i);
                switch (c) {
                    case '\t':
                        i++;
                        cols += 4 - (cols % 4);
                        break;
                    case ' ':
                        i++;
                        cols++;
                        break;
                    default:
                        this.blank = false;
                        break;
                }
            }
        }
        this.nextNonSpace = i;
        this.nextNonSpaceColumn = cols;
        this.indent = this.nextNonSpaceColumn - this.column;
    }

    private void setNewIndex(int newIndex) {
        if (newIndex >= this.nextNonSpace) {
            this.index = this.nextNonSpace;
            this.column = this.nextNonSpaceColumn;
        }
        int length = this.line.getContent().length();
        while (this.index < newIndex && this.index != length) {
            advance();
        }
        this.columnIsInTab = false;
    }

    private void setNewColumn(int newColumn) {
        if (newColumn >= this.nextNonSpaceColumn) {
            this.index = this.nextNonSpace;
            this.column = this.nextNonSpaceColumn;
        }
        int length = this.line.getContent().length();
        while (this.column < newColumn && this.index != length) {
            advance();
        }
        if (this.column > newColumn) {
            this.index--;
            this.column = newColumn;
            this.columnIsInTab = true;
            return;
        }
        this.columnIsInTab = false;
    }

    private void advance() {
        char c = this.line.getContent().charAt(this.index);
        this.index++;
        if (c == '\t') {
            this.column += Parsing.columnsToNextTabStop(this.column);
        } else {
            this.column++;
        }
    }

    private void addLine() {
        CharSequence content;
        if (this.columnIsInTab) {
            int afterTab = this.index + 1;
            CharSequence rest = this.line.getContent().subSequence(afterTab, this.line.getContent().length());
            int spaces = Parsing.columnsToNextTabStop(this.column);
            StringBuilder sb = new StringBuilder(rest.length() + spaces);
            for (int i = 0; i < spaces; i++) {
                sb.append(' ');
            }
            sb.append(rest);
            content = sb.toString();
        } else if (this.index == 0) {
            content = this.line.getContent();
        } else {
            content = this.line.getContent().subSequence(this.index, this.line.getContent().length());
        }
        SourceSpan sourceSpan = null;
        if (this.includeSourceSpans == IncludeSourceSpans.BLOCKS_AND_INLINES) {
            sourceSpan = SourceSpan.of(this.lineIndex, this.index, content.length());
        }
        getActiveBlockParser().addLine(SourceLine.of(content, sourceSpan));
        addSourceSpans();
    }

    private void addSourceSpans() {
        if (this.includeSourceSpans != IncludeSourceSpans.NONE) {
            for (int i = 1; i < this.openBlockParsers.size(); i++) {
                OpenBlockParser openBlockParser = this.openBlockParsers.get(i);
                int blockIndex = openBlockParser.sourceIndex;
                int length = this.line.getContent().length() - blockIndex;
                if (length != 0) {
                    openBlockParser.blockParser.addSourceSpan(SourceSpan.of(this.lineIndex, blockIndex, length));
                }
            }
        }
    }

    private BlockStartImpl findBlockStart(BlockParser blockParser) {
        MatchedBlockParser matchedBlockParser = new MatchedBlockParserImpl(blockParser);
        for (BlockParserFactory blockParserFactory : this.blockParserFactories) {
            BlockStart result = blockParserFactory.tryStart(this, matchedBlockParser);
            if (result instanceof BlockStartImpl) {
                return (BlockStartImpl) result;
            }
        }
        return null;
    }

    private void finalize(BlockParser blockParser) {
        if (blockParser instanceof ParagraphParser) {
            addDefinitionsFrom((ParagraphParser) blockParser);
        }
        blockParser.closeBlock();
    }

    private void addDefinitionsFrom(ParagraphParser paragraphParser) {
        for (LinkReferenceDefinition definition : paragraphParser.getDefinitions()) {
            paragraphParser.getBlock().insertBefore(definition);
            this.definitions.add(definition);
        }
    }

    private void processInlines() {
        InlineParserContextImpl context = new InlineParserContextImpl(this.delimiterProcessors, this.definitions);
        InlineParser inlineParser = this.inlineParserFactory.create(context);
        for (BlockParser blockParser : this.allBlockParsers) {
            blockParser.parseInlines(inlineParser);
        }
    }

    private void addChild(OpenBlockParser openBlockParser) {
        while (!getActiveBlockParser().canContain(openBlockParser.blockParser.getBlock())) {
            closeBlockParsers(1);
        }
        getActiveBlockParser().getBlock().appendChild(openBlockParser.blockParser.getBlock());
        activateBlockParser(openBlockParser);
    }

    private void activateBlockParser(OpenBlockParser openBlockParser) {
        this.openBlockParsers.add(openBlockParser);
    }

    private OpenBlockParser deactivateBlockParser() {
        return this.openBlockParsers.remove(this.openBlockParsers.size() - 1);
    }

    private Block prepareActiveBlockParserForReplacement() {
        BlockParser old = deactivateBlockParser().blockParser;
        if (old instanceof ParagraphParser) {
            ParagraphParser paragraphParser = (ParagraphParser) old;
            addDefinitionsFrom(paragraphParser);
        }
        old.closeBlock();
        old.getBlock().unlink();
        return old.getBlock();
    }

    private Document finalizeAndProcess() {
        closeBlockParsers(this.openBlockParsers.size());
        processInlines();
        return this.documentBlockParser.getBlock();
    }

    private void closeBlockParsers(int count) {
        for (int i = 0; i < count; i++) {
            BlockParser blockParser = deactivateBlockParser().blockParser;
            finalize(blockParser);
            this.allBlockParsers.add(blockParser);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class MatchedBlockParserImpl implements MatchedBlockParser {
        private final BlockParser matchedBlockParser;

        public MatchedBlockParserImpl(BlockParser matchedBlockParser) {
            this.matchedBlockParser = matchedBlockParser;
        }

        @Override // org.commonmark.parser.block.MatchedBlockParser
        public BlockParser getMatchedBlockParser() {
            return this.matchedBlockParser;
        }

        @Override // org.commonmark.parser.block.MatchedBlockParser
        public SourceLines getParagraphLines() {
            if (this.matchedBlockParser instanceof ParagraphParser) {
                ParagraphParser paragraphParser = (ParagraphParser) this.matchedBlockParser;
                return paragraphParser.getParagraphLines();
            }
            return SourceLines.empty();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class OpenBlockParser {
        private final BlockParser blockParser;
        private int sourceIndex;

        OpenBlockParser(BlockParser blockParser, int sourceIndex) {
            this.blockParser = blockParser;
            this.sourceIndex = sourceIndex;
        }
    }
}
