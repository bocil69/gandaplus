package org.commonmark.internal.inline;

import java.util.List;
import org.commonmark.internal.util.CharMatcher;
import org.commonmark.node.SourceSpan;
import org.commonmark.parser.SourceLine;
import org.commonmark.parser.SourceLines;
/* loaded from: classes2.dex */
public class Scanner {
    public static final char END = 0;
    private int index;
    private int lineIndex;
    private final List<SourceLine> lines;
    private SourceLine line = SourceLine.of("", null);
    private int lineLength = 0;

    Scanner(List<SourceLine> lines, int lineIndex, int index) {
        this.lines = lines;
        this.lineIndex = lineIndex;
        this.index = index;
        if (!lines.isEmpty()) {
            checkPosition(lineIndex, index);
            setLine(lines.get(lineIndex));
        }
    }

    public static Scanner of(SourceLines lines) {
        return new Scanner(lines.getLines(), 0, 0);
    }

    public char peek() {
        if (this.index < this.lineLength) {
            return this.line.getContent().charAt(this.index);
        }
        if (this.lineIndex < this.lines.size() - 1) {
            return '\n';
        }
        return (char) 0;
    }

    public int peekCodePoint() {
        if (this.index < this.lineLength) {
            char c = this.line.getContent().charAt(this.index);
            if (Character.isHighSurrogate(c) && this.index + 1 < this.lineLength) {
                char low = this.line.getContent().charAt(this.index + 1);
                if (Character.isLowSurrogate(low)) {
                    return Character.toCodePoint(c, low);
                }
                return c;
            }
            return c;
        } else if (this.lineIndex < this.lines.size() - 1) {
            return 10;
        } else {
            return 0;
        }
    }

    public int peekPreviousCodePoint() {
        if (this.index > 0) {
            int prev = this.index - 1;
            char c = this.line.getContent().charAt(prev);
            if (Character.isLowSurrogate(c) && prev > 0) {
                char high = this.line.getContent().charAt(prev - 1);
                if (Character.isHighSurrogate(high)) {
                    return Character.toCodePoint(high, c);
                }
                return c;
            }
            return c;
        } else if (this.lineIndex > 0) {
            return 10;
        } else {
            return 0;
        }
    }

    public boolean hasNext() {
        return this.index < this.lineLength || this.lineIndex < this.lines.size() + (-1);
    }

    public void next() {
        this.index++;
        if (this.index > this.lineLength) {
            this.lineIndex++;
            if (this.lineIndex < this.lines.size()) {
                setLine(this.lines.get(this.lineIndex));
            } else {
                setLine(SourceLine.of("", null));
            }
            this.index = 0;
        }
    }

    public boolean next(char c) {
        if (peek() == c) {
            next();
            return true;
        }
        return false;
    }

    public boolean next(String content) {
        if (this.index >= this.lineLength || this.index + content.length() > this.lineLength) {
            return false;
        }
        for (int i = 0; i < content.length(); i++) {
            if (this.line.getContent().charAt(this.index + i) != content.charAt(i)) {
                return false;
            }
        }
        this.index += content.length();
        return true;
    }

    public int matchMultiple(char c) {
        int count = 0;
        while (peek() == c) {
            count++;
            next();
        }
        return count;
    }

    public int match(CharMatcher matcher) {
        int count = 0;
        while (matcher.matches(peek())) {
            count++;
            next();
        }
        return count;
    }

    public int whitespace() {
        int count = 0;
        while (true) {
            switch (peek()) {
                case '\t':
                case '\n':
                case 11:
                case '\f':
                case '\r':
                case ' ':
                    count++;
                    next();
                default:
                    return count;
            }
        }
    }

    public int find(char c) {
        int count = 0;
        while (true) {
            char cur = peek();
            if (cur == 0) {
                return -1;
            }
            if (cur == c) {
                return count;
            }
            count++;
            next();
        }
    }

    public int find(CharMatcher matcher) {
        int count = 0;
        while (true) {
            char c = peek();
            if (c == 0) {
                return -1;
            }
            if (matcher.matches(c)) {
                return count;
            }
            count++;
            next();
        }
    }

    public Position position() {
        return new Position(this.lineIndex, this.index);
    }

    public void setPosition(Position position) {
        checkPosition(position.lineIndex, position.index);
        this.lineIndex = position.lineIndex;
        this.index = position.index;
        setLine(this.lines.get(this.lineIndex));
    }

    public SourceLines getSource(Position begin, Position end) {
        if (begin.lineIndex == end.lineIndex) {
            SourceLine line = this.lines.get(begin.lineIndex);
            CharSequence newContent = line.getContent().subSequence(begin.index, end.index);
            SourceSpan newSourceSpan = null;
            SourceSpan sourceSpan = line.getSourceSpan();
            if (sourceSpan != null) {
                newSourceSpan = SourceSpan.of(sourceSpan.getLineIndex(), sourceSpan.getColumnIndex() + begin.index, newContent.length());
            }
            return SourceLines.of(SourceLine.of(newContent, newSourceSpan));
        }
        SourceLines sourceLines = SourceLines.empty();
        SourceLine firstLine = this.lines.get(begin.lineIndex);
        sourceLines.addLine(firstLine.substring(begin.index, firstLine.getContent().length()));
        for (int line2 = begin.lineIndex + 1; line2 < end.lineIndex; line2++) {
            sourceLines.addLine(this.lines.get(line2));
        }
        SourceLine lastLine = this.lines.get(end.lineIndex);
        sourceLines.addLine(lastLine.substring(0, end.index));
        return sourceLines;
    }

    private void setLine(SourceLine line) {
        this.line = line;
        this.lineLength = line.getContent().length();
    }

    private void checkPosition(int lineIndex, int index) {
        if (lineIndex < 0 || lineIndex >= this.lines.size()) {
            throw new IllegalArgumentException("Line index " + lineIndex + " out of range, number of lines: " + this.lines.size());
        }
        SourceLine line = this.lines.get(lineIndex);
        if (index < 0 || index > line.getContent().length()) {
            throw new IllegalArgumentException("Index " + index + " out of range, line length: " + line.getContent().length());
        }
    }
}
