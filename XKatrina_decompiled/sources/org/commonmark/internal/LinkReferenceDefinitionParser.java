package org.commonmark.internal;

import java.util.ArrayList;
import java.util.List;
import org.commonmark.internal.inline.Position;
import org.commonmark.internal.inline.Scanner;
import org.commonmark.internal.util.Escaping;
import org.commonmark.internal.util.LinkScanner;
import org.commonmark.node.LinkReferenceDefinition;
import org.commonmark.node.SourceSpan;
import org.commonmark.parser.SourceLine;
import org.commonmark.parser.SourceLines;
/* loaded from: classes2.dex */
public class LinkReferenceDefinitionParser {
    private String destination;
    private StringBuilder label;
    private StringBuilder title;
    private char titleDelimiter;
    private State state = State.START_DEFINITION;
    private final List<SourceLine> paragraphLines = new ArrayList();
    private final List<LinkReferenceDefinition> definitions = new ArrayList();
    private final List<SourceSpan> sourceSpans = new ArrayList();
    private boolean referenceValid = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public enum State {
        START_DEFINITION,
        LABEL,
        DESTINATION,
        START_TITLE,
        TITLE,
        PARAGRAPH
    }

    public void parse(SourceLine line) {
        boolean success;
        this.paragraphLines.add(line);
        if (this.state != State.PARAGRAPH) {
            Scanner scanner = Scanner.of(SourceLines.of(line));
            while (scanner.hasNext()) {
                switch (this.state) {
                    case START_DEFINITION:
                        success = startDefinition(scanner);
                        continue;
                    case LABEL:
                        success = label(scanner);
                        continue;
                    case DESTINATION:
                        success = destination(scanner);
                        continue;
                    case START_TITLE:
                        success = startTitle(scanner);
                        continue;
                    case TITLE:
                        success = title(scanner);
                        continue;
                    default:
                        throw new IllegalStateException("Unknown parsing state: " + this.state);
                }
                if (!success) {
                    this.state = State.PARAGRAPH;
                    return;
                }
            }
        }
    }

    public void addSourceSpan(SourceSpan sourceSpan) {
        this.sourceSpans.add(sourceSpan);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SourceLines getParagraphLines() {
        return SourceLines.of(this.paragraphLines);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<SourceSpan> getParagraphSourceSpans() {
        return this.sourceSpans;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<LinkReferenceDefinition> getDefinitions() {
        finishReference();
        return this.definitions;
    }

    State getState() {
        return this.state;
    }

    private boolean startDefinition(Scanner scanner) {
        scanner.whitespace();
        if (!scanner.next('[')) {
            return false;
        }
        this.state = State.LABEL;
        this.label = new StringBuilder();
        if (!scanner.hasNext()) {
            this.label.append('\n');
        }
        return true;
    }

    private boolean label(Scanner scanner) {
        Position start = scanner.position();
        if (LinkScanner.scanLinkLabelContent(scanner)) {
            this.label.append(scanner.getSource(start, scanner.position()).getContent());
            if (!scanner.hasNext()) {
                this.label.append('\n');
                return true;
            } else if (scanner.next(']') && scanner.next(':') && this.label.length() <= 999) {
                String normalizedLabel = Escaping.normalizeLabelContent(this.label.toString());
                if (normalizedLabel.isEmpty()) {
                    return false;
                }
                this.state = State.DESTINATION;
                scanner.whitespace();
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean destination(Scanner scanner) {
        scanner.whitespace();
        Position start = scanner.position();
        if (LinkScanner.scanLinkDestination(scanner)) {
            String rawDestination = scanner.getSource(start, scanner.position()).getContent();
            if (rawDestination.startsWith("<")) {
                rawDestination = rawDestination.substring(1, rawDestination.length() - 1);
            }
            this.destination = rawDestination;
            int whitespace = scanner.whitespace();
            if (!scanner.hasNext()) {
                this.referenceValid = true;
                this.paragraphLines.clear();
            } else if (whitespace == 0) {
                return false;
            }
            this.state = State.START_TITLE;
            return true;
        }
        return false;
    }

    private boolean startTitle(Scanner scanner) {
        scanner.whitespace();
        if (!scanner.hasNext()) {
            this.state = State.START_DEFINITION;
        } else {
            this.titleDelimiter = (char) 0;
            char c = scanner.peek();
            switch (c) {
                case '\"':
                case '\'':
                    this.titleDelimiter = c;
                    break;
                case '(':
                    this.titleDelimiter = ')';
                    break;
            }
            if (this.titleDelimiter != 0) {
                this.state = State.TITLE;
                this.title = new StringBuilder();
                scanner.next();
                if (!scanner.hasNext()) {
                    this.title.append('\n');
                }
            } else {
                finishReference();
                this.state = State.START_DEFINITION;
            }
        }
        return true;
    }

    private boolean title(Scanner scanner) {
        Position start = scanner.position();
        if (LinkScanner.scanLinkTitleContent(scanner, this.titleDelimiter)) {
            this.title.append(scanner.getSource(start, scanner.position()).getContent());
            if (!scanner.hasNext()) {
                this.title.append('\n');
                return true;
            }
            scanner.next();
            scanner.whitespace();
            if (scanner.hasNext()) {
                return false;
            }
            this.referenceValid = true;
            finishReference();
            this.paragraphLines.clear();
            this.state = State.START_DEFINITION;
            return true;
        }
        return false;
    }

    private void finishReference() {
        if (this.referenceValid) {
            String d = Escaping.unescapeString(this.destination);
            String t = this.title != null ? Escaping.unescapeString(this.title.toString()) : null;
            LinkReferenceDefinition definition = new LinkReferenceDefinition(this.label.toString(), d, t);
            definition.setSourceSpans(this.sourceSpans);
            this.sourceSpans.clear();
            this.definitions.add(definition);
            this.label = null;
            this.referenceValid = false;
            this.destination = null;
            this.title = null;
        }
    }
}
