package org.commonmark.internal.util;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes2.dex */
public class Escaping {
    public static final String ENTITY = "&(?:#x[a-f0-9]{1,6}|#[0-9]{1,7}|[a-z][a-z0-9]{1,31});";
    public static final String ESCAPABLE = "[!\"#$%&'()*+,./:;<=>?@\\[\\\\\\]^_`{|}~-]";
    private static final Pattern BACKSLASH_OR_AMP = Pattern.compile("[\\\\&]");
    private static final Pattern ENTITY_OR_ESCAPED_CHAR = Pattern.compile("\\\\[!\"#$%&'()*+,./:;<=>?@\\[\\\\\\]^_`{|}~-]|&(?:#x[a-f0-9]{1,6}|#[0-9]{1,7}|[a-z][a-z0-9]{1,31});", 2);
    private static final Pattern ESCAPE_IN_URI = Pattern.compile("(%[a-fA-F0-9]{0,2}|[^:/?#@!$&'()*+,;=a-zA-Z0-9\\-._~])");
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final Pattern WHITESPACE = Pattern.compile("[ \t\r\n]+");
    private static final Replacer UNESCAPE_REPLACER = new Replacer() { // from class: org.commonmark.internal.util.Escaping.1
        @Override // org.commonmark.internal.util.Escaping.Replacer
        public void replace(String input, StringBuilder sb) {
            if (input.charAt(0) == '\\') {
                sb.append((CharSequence) input, 1, input.length());
            } else {
                sb.append(Html5Entities.entityToString(input));
            }
        }
    };
    private static final Replacer URI_REPLACER = new Replacer() { // from class: org.commonmark.internal.util.Escaping.2
        @Override // org.commonmark.internal.util.Escaping.Replacer
        public void replace(String input, StringBuilder sb) {
            if (input.startsWith("%")) {
                if (input.length() == 3) {
                    sb.append(input);
                    return;
                }
                sb.append("%25");
                sb.append((CharSequence) input, 1, input.length());
                return;
            }
            byte[] bytes = input.getBytes(Charset.forName("UTF-8"));
            for (byte b : bytes) {
                sb.append('%');
                sb.append(Escaping.HEX_DIGITS[(b >> 4) & 15]);
                sb.append(Escaping.HEX_DIGITS[b & 15]);
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public interface Replacer {
        void replace(String str, StringBuilder sb);
    }

    public static String escapeHtml(String input) {
        String replacement;
        StringBuilder sb = null;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            switch (c) {
                case '\"':
                    replacement = "&quot;";
                    break;
                case '&':
                    replacement = "&amp;";
                    break;
                case '<':
                    replacement = "&lt;";
                    break;
                case '>':
                    replacement = "&gt;";
                    break;
                default:
                    if (sb != null) {
                        sb.append(c);
                        continue;
                    }
            }
            if (sb == null) {
                sb = new StringBuilder();
                sb.append((CharSequence) input, 0, i);
            }
            sb.append(replacement);
        }
        return sb != null ? sb.toString() : input;
    }

    public static String unescapeString(String s) {
        if (BACKSLASH_OR_AMP.matcher(s).find()) {
            return replaceAll(ENTITY_OR_ESCAPED_CHAR, s, UNESCAPE_REPLACER);
        }
        return s;
    }

    public static String percentEncodeUrl(String s) {
        return replaceAll(ESCAPE_IN_URI, s, URI_REPLACER);
    }

    public static String normalizeLabelContent(String input) {
        String trimmed = input.trim();
        String caseFolded = trimmed.toLowerCase(Locale.ROOT).toUpperCase(Locale.ROOT);
        return WHITESPACE.matcher(caseFolded).replaceAll(" ");
    }

    private static String replaceAll(Pattern p, String s, Replacer replacer) {
        Matcher matcher = p.matcher(s);
        if (matcher.find()) {
            StringBuilder sb = new StringBuilder(s.length() + 16);
            int lastEnd = 0;
            do {
                sb.append((CharSequence) s, lastEnd, matcher.start());
                replacer.replace(matcher.group(), sb);
                lastEnd = matcher.end();
            } while (matcher.find());
            if (lastEnd != s.length()) {
                sb.append((CharSequence) s, lastEnd, s.length());
            }
            return sb.toString();
        }
        return s;
    }
}
