package org.commonmark.internal.inline;

import org.commonmark.internal.util.AsciiMatcher;
import org.commonmark.node.HtmlInline;
/* loaded from: classes2.dex */
public class HtmlInlineParser implements InlineContentParser {
    private static final AsciiMatcher asciiLetter = AsciiMatcher.builder().range('A', 'Z').range('a', 'z').build();
    private static final AsciiMatcher tagNameStart = asciiLetter;
    private static final AsciiMatcher tagNameContinue = tagNameStart.newBuilder().range('0', '9').c('-').build();
    private static final AsciiMatcher attributeStart = asciiLetter.newBuilder().c('_').c(':').build();
    private static final AsciiMatcher attributeContinue = attributeStart.newBuilder().range('0', '9').c('.').c('-').build();
    private static final AsciiMatcher attributeValueEnd = AsciiMatcher.builder().c(' ').c('\t').c('\n').c(11).c('\f').c('\r').c('\"').c('\'').c('=').c('<').c('>').c('`').build();

    @Override // org.commonmark.internal.inline.InlineContentParser
    public ParsedInline tryParse(InlineParserState inlineParserState) {
        Scanner scanner = inlineParserState.scanner();
        Position start = scanner.position();
        scanner.next();
        char c = scanner.peek();
        if (tagNameStart.matches(c)) {
            if (tryOpenTag(scanner)) {
                return htmlInline(start, scanner);
            }
        } else if (c == '/') {
            if (tryClosingTag(scanner)) {
                return htmlInline(start, scanner);
            }
        } else if (c == '?') {
            if (tryProcessingInstruction(scanner)) {
                return htmlInline(start, scanner);
            }
        } else if (c == '!') {
            scanner.next();
            char c2 = scanner.peek();
            if (c2 == '-') {
                if (tryComment(scanner)) {
                    return htmlInline(start, scanner);
                }
            } else if (c2 == '[') {
                if (tryCdata(scanner)) {
                    return htmlInline(start, scanner);
                }
            } else if (asciiLetter.matches(c2) && tryDeclaration(scanner)) {
                return htmlInline(start, scanner);
            }
        }
        return ParsedInline.none();
    }

    private static ParsedInline htmlInline(Position start, Scanner scanner) {
        String text = scanner.getSource(start, scanner.position()).getContent();
        HtmlInline node = new HtmlInline();
        node.setLiteral(text);
        return ParsedInline.of(node, scanner.position());
    }

    private static boolean tryOpenTag(Scanner scanner) {
        scanner.next();
        scanner.match(tagNameContinue);
        boolean whitespace = scanner.whitespace() >= 1;
        while (whitespace && scanner.match(attributeStart) >= 1) {
            scanner.match(attributeContinue);
            whitespace = scanner.whitespace() >= 1;
            if (scanner.next('=')) {
                scanner.whitespace();
                char valueStart = scanner.peek();
                if (valueStart == '\'') {
                    scanner.next();
                    if (scanner.find('\'') < 0) {
                        return false;
                    }
                    scanner.next();
                } else if (valueStart == '\"') {
                    scanner.next();
                    if (scanner.find('\"') < 0) {
                        return false;
                    }
                    scanner.next();
                } else if (scanner.find(attributeValueEnd) <= 0) {
                    return false;
                }
                whitespace = scanner.whitespace() >= 1;
            }
        }
        scanner.next('/');
        return scanner.next('>');
    }

    private static boolean tryClosingTag(Scanner scanner) {
        scanner.next();
        if (scanner.match(tagNameStart) >= 1) {
            scanner.match(tagNameContinue);
            scanner.whitespace();
            return scanner.next('>');
        }
        return false;
    }

    private static boolean tryProcessingInstruction(Scanner scanner) {
        scanner.next();
        while (scanner.find('?') > 0) {
            scanner.next();
            if (scanner.next('>')) {
                return true;
            }
        }
        return false;
    }

    private static boolean tryComment(Scanner scanner) {
        scanner.next();
        if (!scanner.next('-') || scanner.next('>') || scanner.next("->")) {
            return false;
        }
        while (scanner.find('-') >= 0) {
            if (scanner.next("--")) {
                return scanner.next('>');
            }
            scanner.next();
        }
        return false;
    }

    private static boolean tryCdata(Scanner scanner) {
        scanner.next();
        if (scanner.next("CDATA[")) {
            while (scanner.find(']') >= 0) {
                if (scanner.next("]]>")) {
                    return true;
                }
                scanner.next();
            }
        }
        return false;
    }

    private static boolean tryDeclaration(Scanner scanner) {
        scanner.match(asciiLetter);
        if (scanner.whitespace() > 0 && scanner.find('>') >= 0) {
            scanner.next();
            return true;
        }
        return false;
    }
}
