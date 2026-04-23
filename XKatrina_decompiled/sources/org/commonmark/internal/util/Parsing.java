package org.commonmark.internal.util;
/* loaded from: classes2.dex */
public class Parsing {
    private static final String ATTRIBUTE = "(?:\\s+[a-zA-Z_:][a-zA-Z0-9:._-]*(?:\\s*=\\s*(?:[^\"'=<>`\\x00-\\x20]+|'[^']*'|\"[^\"]*\"))?)";
    private static final String ATTRIBUTENAME = "[a-zA-Z_:][a-zA-Z0-9:._-]*";
    private static final String ATTRIBUTEVALUE = "(?:[^\"'=<>`\\x00-\\x20]+|'[^']*'|\"[^\"]*\")";
    private static final String ATTRIBUTEVALUESPEC = "(?:\\s*=\\s*(?:[^\"'=<>`\\x00-\\x20]+|'[^']*'|\"[^\"]*\"))";
    public static final String CLOSETAG = "</[A-Za-z][A-Za-z0-9-]*\\s*[>]";
    public static int CODE_BLOCK_INDENT = 4;
    private static final String DOUBLEQUOTEDVALUE = "\"[^\"]*\"";
    public static final String OPENTAG = "<[A-Za-z][A-Za-z0-9-]*(?:\\s+[a-zA-Z_:][a-zA-Z0-9:._-]*(?:\\s*=\\s*(?:[^\"'=<>`\\x00-\\x20]+|'[^']*'|\"[^\"]*\"))?)*\\s*/?>";
    private static final String SINGLEQUOTEDVALUE = "'[^']*'";
    private static final String TAGNAME = "[A-Za-z][A-Za-z0-9-]*";
    private static final String UNQUOTEDVALUE = "[^\"'=<>`\\x00-\\x20]+";

    public static int columnsToNextTabStop(int column) {
        return 4 - (column % 4);
    }

    public static int find(char c, CharSequence s, int startIndex) {
        int length = s.length();
        for (int i = startIndex; i < length; i++) {
            if (s.charAt(i) == c) {
                return i;
            }
        }
        return -1;
    }

    public static int findLineBreak(CharSequence s, int startIndex) {
        int length = s.length();
        for (int i = startIndex; i < length; i++) {
            switch (s.charAt(i)) {
                case '\n':
                case '\r':
                    return i;
                case 11:
                case '\f':
                default:
            }
        }
        return -1;
    }

    public static boolean isBlank(CharSequence s) {
        return findNonSpace(s, 0) == -1;
    }

    public static boolean hasNonSpace(CharSequence s) {
        int length = s.length();
        int skipped = skip(' ', s, 0, length);
        return skipped != length;
    }

    public static boolean isLetter(CharSequence s, int index) {
        int codePoint = Character.codePointAt(s, index);
        return Character.isLetter(codePoint);
    }

    public static boolean isSpaceOrTab(CharSequence s, int index) {
        if (index < s.length()) {
            switch (s.charAt(index)) {
                case '\t':
                case ' ':
                    return true;
            }
        }
        return false;
    }

    public static boolean isEscapable(char c) {
        switch (c) {
            case '!':
            case '\"':
            case '#':
            case '$':
            case '%':
            case '&':
            case '\'':
            case '(':
            case ')':
            case '*':
            case '+':
            case ',':
            case '-':
            case '.':
            case '/':
            case ':':
            case ';':
            case '<':
            case '=':
            case '>':
            case '?':
            case '@':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '_':
            case '`':
            case '{':
            case '|':
            case '}':
            case '~':
                return true;
            default:
                return false;
        }
    }

    public static boolean isPunctuationCodePoint(int codePoint) {
        switch (Character.getType(codePoint)) {
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 29:
            case 30:
                return true;
            case 25:
            case 26:
            case 27:
            case 28:
            default:
                switch (codePoint) {
                    case 36:
                    case 43:
                    case 60:
                    case 61:
                    case 62:
                    case 94:
                    case 96:
                    case 124:
                    case 126:
                        return true;
                    default:
                        return false;
                }
        }
    }

    public static boolean isWhitespaceCodePoint(int codePoint) {
        switch (codePoint) {
            case 9:
            case 10:
            case 12:
            case 13:
            case 32:
                return true;
            default:
                return Character.getType(codePoint) == 12;
        }
    }

    public static CharSequence prepareLine(CharSequence line) {
        StringBuilder sb = null;
        int length = line.length();
        for (int i = 0; i < length; i++) {
            char c = line.charAt(i);
            if (c == 0) {
                if (sb == null) {
                    sb = new StringBuilder(length);
                    sb.append(line, 0, i);
                }
                sb.append((char) 65533);
            } else if (sb != null) {
                sb.append(c);
            }
        }
        if (sb != null) {
            return sb.toString();
        }
        return line;
    }

    public static int skip(char skip, CharSequence s, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            if (s.charAt(i) != skip) {
                return i;
            }
        }
        return endIndex;
    }

    public static int skipBackwards(char skip, CharSequence s, int startIndex, int lastIndex) {
        for (int i = startIndex; i >= lastIndex; i--) {
            if (s.charAt(i) != skip) {
                return i;
            }
        }
        int i2 = lastIndex - 1;
        return i2;
    }

    public static int skipSpaceTab(CharSequence s, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            switch (s.charAt(i)) {
                case '\t':
                case ' ':
                default:
                    return i;
            }
        }
        return endIndex;
    }

    public static int skipSpaceTabBackwards(CharSequence s, int startIndex, int lastIndex) {
        for (int i = startIndex; i >= lastIndex; i--) {
            switch (s.charAt(i)) {
                case '\t':
                case ' ':
                default:
                    return i;
            }
        }
        int i2 = lastIndex - 1;
        return i2;
    }

    private static int findNonSpace(CharSequence s, int startIndex) {
        int length = s.length();
        for (int i = startIndex; i < length; i++) {
            switch (s.charAt(i)) {
                case '\t':
                case '\n':
                case 11:
                case '\f':
                case '\r':
                case ' ':
                default:
                    return i;
            }
        }
        return -1;
    }
}
