package org.commonmark.internal.inline;

import org.commonmark.internal.util.Parsing;
import org.commonmark.node.Code;
import org.commonmark.node.Text;
import org.commonmark.parser.SourceLines;
/* loaded from: classes2.dex */
public class BackticksInlineParser implements InlineContentParser {
    @Override // org.commonmark.internal.inline.InlineContentParser
    public ParsedInline tryParse(InlineParserState inlineParserState) {
        Scanner scanner = inlineParserState.scanner();
        Position start = scanner.position();
        int openingTicks = scanner.matchMultiple('`');
        Position afterOpening = scanner.position();
        while (scanner.find('`') > 0) {
            Position beforeClosing = scanner.position();
            int count = scanner.matchMultiple('`');
            if (count == openingTicks) {
                Code node = new Code();
                String content = scanner.getSource(afterOpening, beforeClosing).getContent().replace('\n', ' ');
                if (content.length() >= 3 && content.charAt(0) == ' ' && content.charAt(content.length() - 1) == ' ' && Parsing.hasNonSpace(content)) {
                    content = content.substring(1, content.length() - 1);
                }
                node.setLiteral(content);
                return ParsedInline.of(node, scanner.position());
            }
        }
        SourceLines source = scanner.getSource(start, afterOpening);
        Text text = new Text(source.getContent());
        return ParsedInline.of(text, afterOpening);
    }
}
