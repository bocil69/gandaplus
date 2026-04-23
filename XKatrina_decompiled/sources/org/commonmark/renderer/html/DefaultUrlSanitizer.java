package org.commonmark.renderer.html;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.http.HttpHost;
/* loaded from: classes2.dex */
public class DefaultUrlSanitizer implements UrlSanitizer {
    private Set<String> protocols;

    public DefaultUrlSanitizer() {
        this(Arrays.asList(HttpHost.DEFAULT_SCHEME_NAME, "https", "mailto"));
    }

    public DefaultUrlSanitizer(Collection<String> protocols) {
        this.protocols = new HashSet(protocols);
    }

    @Override // org.commonmark.renderer.html.UrlSanitizer
    public String sanitizeLinkUrl(String url) {
        String url2 = stripHtmlSpaces(url);
        int n = url2.length();
        for (int i = 0; i < n; i++) {
            switch (url2.charAt(i)) {
                case '#':
                case '/':
                case '?':
                    return url2;
                case ':':
                    String protocol = url2.substring(0, i).toLowerCase();
                    if (!this.protocols.contains(protocol)) {
                        return "";
                    }
                    return url2;
                default:
            }
        }
        return url2;
    }

    @Override // org.commonmark.renderer.html.UrlSanitizer
    public String sanitizeImageUrl(String url) {
        return sanitizeLinkUrl(url);
    }

    private String stripHtmlSpaces(String s) {
        int i = 0;
        int n = s.length();
        while (n > 0 && isHtmlSpace(s.charAt(n - 1))) {
            n--;
        }
        while (i < n && isHtmlSpace(s.charAt(i))) {
            i++;
        }
        return (i == 0 && n == s.length()) ? s : s.substring(i, n);
    }

    private boolean isHtmlSpace(int ch) {
        switch (ch) {
            case 9:
            case 10:
            case 12:
            case 13:
            case 32:
                return true;
            default:
                return false;
        }
    }
}
