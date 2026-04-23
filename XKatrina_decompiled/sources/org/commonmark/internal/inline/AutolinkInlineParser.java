package org.commonmark.internal.inline;

import androidx.core.net.MailTo;
import java.util.regex.Pattern;
import org.commonmark.node.Link;
import org.commonmark.node.Text;
import org.commonmark.parser.SourceLines;
/* loaded from: classes2.dex */
public class AutolinkInlineParser implements InlineContentParser {
    private static final Pattern URI = Pattern.compile("^[a-zA-Z][a-zA-Z0-9.+-]{1,31}:[^<>\u0000- ]*$");
    private static final Pattern EMAIL = Pattern.compile("^([a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*)$");

    @Override // org.commonmark.internal.inline.InlineContentParser
    public ParsedInline tryParse(InlineParserState inlineParserState) {
        Scanner scanner = inlineParserState.scanner();
        scanner.next();
        Position textStart = scanner.position();
        if (scanner.find('>') > 0) {
            SourceLines textSource = scanner.getSource(textStart, scanner.position());
            String content = textSource.getContent();
            scanner.next();
            String destination = null;
            if (URI.matcher(content).matches()) {
                destination = content;
            } else if (EMAIL.matcher(content).matches()) {
                destination = MailTo.MAILTO_SCHEME + content;
            }
            if (destination != null) {
                Link link = new Link(destination, null);
                Text text = new Text(content);
                text.setSourceSpans(textSource.getSourceSpans());
                link.appendChild(text);
                return ParsedInline.of(link, scanner.position());
            }
        }
        return ParsedInline.none();
    }
}
