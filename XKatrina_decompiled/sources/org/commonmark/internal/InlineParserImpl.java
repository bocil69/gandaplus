package org.commonmark.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.commonmark.internal.inline.AsteriskDelimiterProcessor;
import org.commonmark.internal.inline.AutolinkInlineParser;
import org.commonmark.internal.inline.BackslashInlineParser;
import org.commonmark.internal.inline.BackticksInlineParser;
import org.commonmark.internal.inline.EntityInlineParser;
import org.commonmark.internal.inline.HtmlInlineParser;
import org.commonmark.internal.inline.InlineContentParser;
import org.commonmark.internal.inline.InlineParserState;
import org.commonmark.internal.inline.ParsedInline;
import org.commonmark.internal.inline.ParsedInlineImpl;
import org.commonmark.internal.inline.Position;
import org.commonmark.internal.inline.Scanner;
import org.commonmark.internal.inline.UnderscoreDelimiterProcessor;
import org.commonmark.internal.util.Escaping;
import org.commonmark.internal.util.LinkScanner;
import org.commonmark.internal.util.Parsing;
import org.commonmark.node.HardLineBreak;
import org.commonmark.node.Image;
import org.commonmark.node.Link;
import org.commonmark.node.LinkReferenceDefinition;
import org.commonmark.node.Node;
import org.commonmark.node.SoftLineBreak;
import org.commonmark.node.SourceSpans;
import org.commonmark.node.Text;
import org.commonmark.parser.InlineParser;
import org.commonmark.parser.InlineParserContext;
import org.commonmark.parser.SourceLines;
import org.commonmark.parser.delimiter.DelimiterProcessor;
/* loaded from: classes2.dex */
public class InlineParserImpl implements InlineParser, InlineParserState {
    private final InlineParserContext context;
    private final Map<Character, DelimiterProcessor> delimiterProcessors;
    private boolean includeSourceSpans;
    private final Map<Character, List<InlineContentParser>> inlineParsers = new HashMap();
    private Bracket lastBracket;
    private Delimiter lastDelimiter;
    private Scanner scanner;
    private final BitSet specialCharacters;
    private int trailingSpaces;

    public InlineParserImpl(InlineParserContext inlineParserContext) {
        this.delimiterProcessors = calculateDelimiterProcessors(inlineParserContext.getCustomDelimiterProcessors());
        this.context = inlineParserContext;
        this.inlineParsers.put('\\', Collections.singletonList(new BackslashInlineParser()));
        this.inlineParsers.put('`', Collections.singletonList(new BackticksInlineParser()));
        this.inlineParsers.put('&', Collections.singletonList(new EntityInlineParser()));
        this.inlineParsers.put('<', Arrays.asList(new AutolinkInlineParser(), new HtmlInlineParser()));
        this.specialCharacters = calculateSpecialCharacters(this.delimiterProcessors.keySet(), this.inlineParsers.keySet());
    }

    public static BitSet calculateSpecialCharacters(Set<Character> delimiterCharacters, Set<Character> characters) {
        BitSet bitSet = new BitSet();
        for (Character c : delimiterCharacters) {
            bitSet.set(c.charValue());
        }
        for (Character c2 : characters) {
            bitSet.set(c2.charValue());
        }
        bitSet.set(91);
        bitSet.set(93);
        bitSet.set(33);
        bitSet.set(10);
        return bitSet;
    }

    public static Map<Character, DelimiterProcessor> calculateDelimiterProcessors(List<DelimiterProcessor> delimiterProcessors) {
        Map<Character, DelimiterProcessor> map = new HashMap<>();
        addDelimiterProcessors(Arrays.asList(new AsteriskDelimiterProcessor(), new UnderscoreDelimiterProcessor()), map);
        addDelimiterProcessors(delimiterProcessors, map);
        return map;
    }

    @Override // org.commonmark.internal.inline.InlineParserState
    public Scanner scanner() {
        return this.scanner;
    }

    private static void addDelimiterProcessors(Iterable<DelimiterProcessor> delimiterProcessors, Map<Character, DelimiterProcessor> map) {
        StaggeredDelimiterProcessor s;
        for (DelimiterProcessor delimiterProcessor : delimiterProcessors) {
            char opening = delimiterProcessor.getOpeningCharacter();
            char closing = delimiterProcessor.getClosingCharacter();
            if (opening == closing) {
                DelimiterProcessor old = map.get(Character.valueOf(opening));
                if (old != null && old.getOpeningCharacter() == old.getClosingCharacter()) {
                    if (old instanceof StaggeredDelimiterProcessor) {
                        s = (StaggeredDelimiterProcessor) old;
                    } else {
                        s = new StaggeredDelimiterProcessor(opening);
                        s.add(old);
                    }
                    s.add(delimiterProcessor);
                    map.put(Character.valueOf(opening), s);
                } else {
                    addDelimiterProcessorForChar(opening, delimiterProcessor, map);
                }
            } else {
                addDelimiterProcessorForChar(opening, delimiterProcessor, map);
                addDelimiterProcessorForChar(closing, delimiterProcessor, map);
            }
        }
    }

    private static void addDelimiterProcessorForChar(char delimiterChar, DelimiterProcessor toAdd, Map<Character, DelimiterProcessor> delimiterProcessors) {
        DelimiterProcessor existing = delimiterProcessors.put(Character.valueOf(delimiterChar), toAdd);
        if (existing != null) {
            throw new IllegalArgumentException("Delimiter processor conflict with delimiter char '" + delimiterChar + "'");
        }
    }

    @Override // org.commonmark.parser.InlineParser
    public void parse(SourceLines lines, Node block) {
        reset(lines);
        while (true) {
            List<? extends Node> nodes = parseInline();
            if (nodes != null) {
                for (Node node : nodes) {
                    block.appendChild(node);
                }
            } else {
                processDelimiters(null);
                mergeChildTextNodes(block);
                return;
            }
        }
    }

    void reset(SourceLines lines) {
        this.scanner = Scanner.of(lines);
        this.includeSourceSpans = !lines.getSourceSpans().isEmpty();
        this.trailingSpaces = 0;
        this.lastDelimiter = null;
        this.lastBracket = null;
    }

    private Text text(SourceLines sourceLines) {
        Text text = new Text(sourceLines.getContent());
        text.setSourceSpans(sourceLines.getSourceSpans());
        return text;
    }

    private List<? extends Node> parseInline() {
        List<? extends Node> nodes;
        char c = this.scanner.peek();
        switch (c) {
            case 0:
                return null;
            case '\n':
                return Collections.singletonList(parseLineBreak());
            case '!':
                return Collections.singletonList(parseBang());
            case '[':
                return Collections.singletonList(parseOpenBracket());
            case ']':
                return Collections.singletonList(parseCloseBracket());
            default:
                if (!this.specialCharacters.get(c)) {
                    return Collections.singletonList(parseText());
                }
                List<InlineContentParser> inlineParsers = this.inlineParsers.get(Character.valueOf(c));
                if (inlineParsers != null) {
                    Position position = this.scanner.position();
                    for (InlineContentParser inlineParser : inlineParsers) {
                        ParsedInline parsedInline = inlineParser.tryParse(this);
                        if (parsedInline instanceof ParsedInlineImpl) {
                            ParsedInlineImpl parsedInlineImpl = (ParsedInlineImpl) parsedInline;
                            Node node = parsedInlineImpl.getNode();
                            this.scanner.setPosition(parsedInlineImpl.getPosition());
                            if (this.includeSourceSpans && node.getSourceSpans().isEmpty()) {
                                node.setSourceSpans(this.scanner.getSource(position, this.scanner.position()).getSourceSpans());
                            }
                            return Collections.singletonList(node);
                        }
                        this.scanner.setPosition(position);
                    }
                }
                DelimiterProcessor delimiterProcessor = this.delimiterProcessors.get(Character.valueOf(c));
                return (delimiterProcessor == null || (nodes = parseDelimiters(delimiterProcessor, c)) == null) ? Collections.singletonList(parseText()) : nodes;
        }
    }

    private List<? extends Node> parseDelimiters(DelimiterProcessor delimiterProcessor, char delimiterChar) {
        DelimiterData res = scanDelimiters(delimiterProcessor, delimiterChar);
        if (res == null) {
            return null;
        }
        List<Text> characters = res.characters;
        this.lastDelimiter = new Delimiter(characters, delimiterChar, res.canOpen, res.canClose, this.lastDelimiter);
        if (this.lastDelimiter.previous != null) {
            this.lastDelimiter.previous.next = this.lastDelimiter;
            return characters;
        }
        return characters;
    }

    private Node parseOpenBracket() {
        Position start = this.scanner.position();
        this.scanner.next();
        Position contentPosition = this.scanner.position();
        Text node = text(this.scanner.getSource(start, contentPosition));
        addBracket(Bracket.link(node, start, contentPosition, this.lastBracket, this.lastDelimiter));
        return node;
    }

    private Node parseBang() {
        Position start = this.scanner.position();
        this.scanner.next();
        if (this.scanner.next('[')) {
            Position contentPosition = this.scanner.position();
            Text node = text(this.scanner.getSource(start, contentPosition));
            addBracket(Bracket.image(node, start, contentPosition, this.lastBracket, this.lastDelimiter));
            return node;
        }
        return text(this.scanner.getSource(start, this.scanner.position()));
    }

    private Node parseCloseBracket() {
        LinkReferenceDefinition definition;
        Position beforeClose = this.scanner.position();
        this.scanner.next();
        Position afterClose = this.scanner.position();
        Bracket opener = this.lastBracket;
        if (opener == null) {
            return text(this.scanner.getSource(beforeClose, afterClose));
        }
        if (!opener.allowed) {
            removeLastBracket();
            return text(this.scanner.getSource(beforeClose, afterClose));
        }
        String dest = null;
        String title = null;
        if (this.scanner.next('(')) {
            this.scanner.whitespace();
            dest = parseLinkDestination(this.scanner);
            if (dest == null) {
                this.scanner.setPosition(afterClose);
            } else {
                int whitespace = this.scanner.whitespace();
                if (whitespace >= 1) {
                    title = parseLinkTitle(this.scanner);
                    this.scanner.whitespace();
                }
                if (!this.scanner.next(')')) {
                    this.scanner.setPosition(afterClose);
                    dest = null;
                    title = null;
                }
            }
        }
        if (dest == null) {
            String ref = parseLinkLabel(this.scanner);
            if (ref == null) {
                this.scanner.setPosition(afterClose);
            }
            if ((ref == null || ref.isEmpty()) && !opener.bracketAfter) {
                ref = this.scanner.getSource(opener.contentPosition, beforeClose).getContent();
            }
            if (ref != null && (definition = this.context.getLinkReferenceDefinition(ref)) != null) {
                dest = definition.getDestination();
                title = definition.getTitle();
            }
        }
        if (dest != null) {
            Node linkOrImage = opener.image ? new Image(dest, title) : new Link(dest, title);
            Node node = opener.node.getNext();
            while (node != null) {
                Node next = node.getNext();
                linkOrImage.appendChild(node);
                node = next;
            }
            if (this.includeSourceSpans) {
                linkOrImage.setSourceSpans(this.scanner.getSource(opener.markerPosition, this.scanner.position()).getSourceSpans());
            }
            processDelimiters(opener.previousDelimiter);
            mergeChildTextNodes(linkOrImage);
            opener.node.unlink();
            removeLastBracket();
            if (!opener.image) {
                for (Bracket bracket = this.lastBracket; bracket != null; bracket = bracket.previous) {
                    if (!bracket.image) {
                        bracket.allowed = false;
                    }
                }
                return linkOrImage;
            }
            return linkOrImage;
        }
        removeLastBracket();
        this.scanner.setPosition(afterClose);
        return text(this.scanner.getSource(beforeClose, afterClose));
    }

    private void addBracket(Bracket bracket) {
        if (this.lastBracket != null) {
            this.lastBracket.bracketAfter = true;
        }
        this.lastBracket = bracket;
    }

    private void removeLastBracket() {
        this.lastBracket = this.lastBracket.previous;
    }

    private String parseLinkDestination(Scanner scanner) {
        String dest;
        char delimiter = scanner.peek();
        Position start = scanner.position();
        if (!LinkScanner.scanLinkDestination(scanner)) {
            return null;
        }
        if (delimiter == '<') {
            String rawDestination = scanner.getSource(start, scanner.position()).getContent();
            dest = rawDestination.substring(1, rawDestination.length() - 1);
        } else {
            dest = scanner.getSource(start, scanner.position()).getContent();
        }
        return Escaping.unescapeString(dest);
    }

    private String parseLinkTitle(Scanner scanner) {
        Position start = scanner.position();
        if (!LinkScanner.scanLinkTitle(scanner)) {
            return null;
        }
        String rawTitle = scanner.getSource(start, scanner.position()).getContent();
        String title = rawTitle.substring(1, rawTitle.length() - 1);
        return Escaping.unescapeString(title);
    }

    String parseLinkLabel(Scanner scanner) {
        if (scanner.next('[')) {
            Position start = scanner.position();
            if (LinkScanner.scanLinkLabelContent(scanner)) {
                Position end = scanner.position();
                if (scanner.next(']')) {
                    String content = scanner.getSource(start, end).getContent();
                    if (content.length() > 999) {
                        return null;
                    }
                    return content;
                }
                return null;
            }
            return null;
        }
        return null;
    }

    private Node parseLineBreak() {
        this.scanner.next();
        return this.trailingSpaces >= 2 ? new HardLineBreak() : new SoftLineBreak();
    }

    private Node parseText() {
        char c;
        Position start = this.scanner.position();
        this.scanner.next();
        while (true) {
            c = this.scanner.peek();
            if (c == 0 || this.specialCharacters.get(c)) {
                break;
            }
            this.scanner.next();
        }
        SourceLines source = this.scanner.getSource(start, this.scanner.position());
        String content = source.getContent();
        if (c == '\n') {
            int end = Parsing.skipBackwards(' ', content, content.length() - 1, 0) + 1;
            this.trailingSpaces = content.length() - end;
            content = content.substring(0, end);
        } else if (c == 0) {
            content = content.substring(0, Parsing.skipSpaceTabBackwards(content, content.length() - 1, 0) + 1);
        }
        Text text = new Text(content);
        text.setSourceSpans(source.getSourceSpans());
        return text;
    }

    private DelimiterData scanDelimiters(DelimiterProcessor delimiterProcessor, char delimiterChar) {
        boolean canOpen;
        boolean canClose;
        int before = this.scanner.peekPreviousCodePoint();
        Position start = this.scanner.position();
        int delimiterCount = this.scanner.matchMultiple(delimiterChar);
        if (delimiterCount < delimiterProcessor.getMinLength()) {
            this.scanner.setPosition(start);
            return null;
        }
        List<Text> delimiters = new ArrayList<>();
        this.scanner.setPosition(start);
        Position positionBefore = start;
        while (this.scanner.next(delimiterChar)) {
            delimiters.add(text(this.scanner.getSource(positionBefore, this.scanner.position())));
            positionBefore = this.scanner.position();
        }
        int after = this.scanner.peekCodePoint();
        boolean beforeIsPunctuation = before == 0 || Parsing.isPunctuationCodePoint(before);
        boolean beforeIsWhitespace = before == 0 || Parsing.isWhitespaceCodePoint(before);
        boolean afterIsPunctuation = after == 0 || Parsing.isPunctuationCodePoint(after);
        boolean afterIsWhitespace = after == 0 || Parsing.isWhitespaceCodePoint(after);
        boolean leftFlanking = !afterIsWhitespace && (!afterIsPunctuation || beforeIsWhitespace || beforeIsPunctuation);
        boolean rightFlanking = !beforeIsWhitespace && (!beforeIsPunctuation || afterIsWhitespace || afterIsPunctuation);
        if (delimiterChar == '_') {
            canOpen = leftFlanking && (!rightFlanking || beforeIsPunctuation);
            canClose = rightFlanking && (!leftFlanking || afterIsPunctuation);
        } else {
            canOpen = leftFlanking && delimiterChar == delimiterProcessor.getOpeningCharacter();
            canClose = rightFlanking && delimiterChar == delimiterProcessor.getClosingCharacter();
        }
        return new DelimiterData(delimiters, canOpen, canClose);
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0056, code lost:
        if (r10 != false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0058, code lost:
        r8.put(java.lang.Character.valueOf(r2), r0.previous);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0065, code lost:
        if (r0.canOpen() != false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0067, code lost:
        removeDelimiterKeepNode(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x006a, code lost:
        r0 = r0.next;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void processDelimiters(org.commonmark.internal.Delimiter r15) {
        /*
            r14 = this;
            java.util.HashMap r8 = new java.util.HashMap
            r8.<init>()
            org.commonmark.internal.Delimiter r0 = r14.lastDelimiter
        L7:
            if (r0 == 0) goto L10
            org.commonmark.internal.Delimiter r12 = r0.previous
            if (r12 == r15) goto L10
            org.commonmark.internal.Delimiter r0 = r0.previous
            goto L7
        L10:
            if (r0 == 0) goto Lb5
            char r2 = r0.delimiterChar
            java.util.Map<java.lang.Character, org.commonmark.parser.delimiter.DelimiterProcessor> r12 = r14.delimiterProcessors
            java.lang.Character r13 = java.lang.Character.valueOf(r2)
            java.lang.Object r3 = r12.get(r13)
            org.commonmark.parser.delimiter.DelimiterProcessor r3 = (org.commonmark.parser.delimiter.DelimiterProcessor) r3
            boolean r12 = r0.canClose()
            if (r12 == 0) goto L28
            if (r3 != 0) goto L2b
        L28:
            org.commonmark.internal.Delimiter r0 = r0.next
            goto L10
        L2b:
            char r9 = r3.getOpeningCharacter()
            r11 = 0
            r7 = 0
            r10 = 0
            org.commonmark.internal.Delimiter r6 = r0.previous
        L34:
            if (r6 == 0) goto L54
            if (r6 == r15) goto L54
            java.lang.Character r12 = java.lang.Character.valueOf(r2)
            java.lang.Object r12 = r8.get(r12)
            if (r6 == r12) goto L54
            boolean r12 = r6.canOpen()
            if (r12 == 0) goto L6d
            char r12 = r6.delimiterChar
            if (r12 != r9) goto L6d
            r10 = 1
            int r11 = r3.process(r6, r0)
            if (r11 <= 0) goto L6d
            r7 = 1
        L54:
            if (r7 != 0) goto L70
            if (r10 != 0) goto L6a
            java.lang.Character r12 = java.lang.Character.valueOf(r2)
            org.commonmark.internal.Delimiter r13 = r0.previous
            r8.put(r12, r13)
            boolean r12 = r0.canOpen()
            if (r12 != 0) goto L6a
            r14.removeDelimiterKeepNode(r0)
        L6a:
            org.commonmark.internal.Delimiter r0 = r0.next
            goto L10
        L6d:
            org.commonmark.internal.Delimiter r6 = r6.previous
            goto L34
        L70:
            r4 = 0
        L71:
            if (r4 >= r11) goto L89
            java.util.List<org.commonmark.node.Text> r12 = r6.characters
            java.util.List<org.commonmark.node.Text> r13 = r6.characters
            int r13 = r13.size()
            int r13 = r13 + (-1)
            java.lang.Object r1 = r12.remove(r13)
            org.commonmark.node.Text r1 = (org.commonmark.node.Text) r1
            r1.unlink()
            int r4 = r4 + 1
            goto L71
        L89:
            r4 = 0
        L8a:
            if (r4 >= r11) goto L9b
            java.util.List<org.commonmark.node.Text> r12 = r0.characters
            r13 = 0
            java.lang.Object r1 = r12.remove(r13)
            org.commonmark.node.Text r1 = (org.commonmark.node.Text) r1
            r1.unlink()
            int r4 = r4 + 1
            goto L8a
        L9b:
            r14.removeDelimitersBetween(r6, r0)
            int r12 = r6.length()
            if (r12 != 0) goto La7
            r14.removeDelimiterAndNodes(r6)
        La7:
            int r12 = r0.length()
            if (r12 != 0) goto L10
            org.commonmark.internal.Delimiter r5 = r0.next
            r14.removeDelimiterAndNodes(r0)
            r0 = r5
            goto L10
        Lb5:
            org.commonmark.internal.Delimiter r12 = r14.lastDelimiter
            if (r12 == 0) goto Lc3
            org.commonmark.internal.Delimiter r12 = r14.lastDelimiter
            if (r12 == r15) goto Lc3
            org.commonmark.internal.Delimiter r12 = r14.lastDelimiter
            r14.removeDelimiterKeepNode(r12)
            goto Lb5
        Lc3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.commonmark.internal.InlineParserImpl.processDelimiters(org.commonmark.internal.Delimiter):void");
    }

    private void removeDelimitersBetween(Delimiter opener, Delimiter closer) {
        Delimiter delimiter = closer.previous;
        while (delimiter != null && delimiter != opener) {
            Delimiter previousDelimiter = delimiter.previous;
            removeDelimiterKeepNode(delimiter);
            delimiter = previousDelimiter;
        }
    }

    private void removeDelimiterAndNodes(Delimiter delim) {
        removeDelimiter(delim);
    }

    private void removeDelimiterKeepNode(Delimiter delim) {
        removeDelimiter(delim);
    }

    private void removeDelimiter(Delimiter delim) {
        if (delim.previous != null) {
            delim.previous.next = delim.next;
        }
        if (delim.next == null) {
            this.lastDelimiter = delim.previous;
            return;
        }
        delim.next.previous = delim.previous;
    }

    private void mergeChildTextNodes(Node node) {
        if (node.getFirstChild() != null) {
            mergeTextNodesInclusive(node.getFirstChild(), node.getLastChild());
        }
    }

    private void mergeTextNodesInclusive(Node fromNode, Node toNode) {
        Text first = null;
        Text last = null;
        int length = 0;
        for (Node node = fromNode; node != null; node = node.getNext()) {
            if (node instanceof Text) {
                Text text = (Text) node;
                if (first == null) {
                    first = text;
                }
                length += text.getLiteral().length();
                last = text;
            } else {
                mergeIfNeeded(first, last, length);
                first = null;
                last = null;
                length = 0;
                mergeChildTextNodes(node);
            }
            if (node == toNode) {
                break;
            }
        }
        mergeIfNeeded(first, last, length);
    }

    private void mergeIfNeeded(Text first, Text last, int textLength) {
        if (first != null && last != null && first != last) {
            StringBuilder sb = new StringBuilder(textLength);
            sb.append(first.getLiteral());
            SourceSpans sourceSpans = null;
            if (this.includeSourceSpans) {
                sourceSpans = new SourceSpans();
                sourceSpans.addAll(first.getSourceSpans());
            }
            Node node = first.getNext();
            Node stop = last.getNext();
            while (node != stop) {
                sb.append(((Text) node).getLiteral());
                if (sourceSpans != null) {
                    sourceSpans.addAll(node.getSourceSpans());
                }
                Node unlink = node;
                node = node.getNext();
                unlink.unlink();
            }
            String literal = sb.toString();
            first.setLiteral(literal);
            if (sourceSpans != null) {
                first.setSourceSpans(sourceSpans.getSourceSpans());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class DelimiterData {
        final boolean canClose;
        final boolean canOpen;
        final List<Text> characters;

        DelimiterData(List<Text> characters, boolean canOpen, boolean canClose) {
            this.characters = characters;
            this.canOpen = canOpen;
            this.canClose = canClose;
        }
    }
}
