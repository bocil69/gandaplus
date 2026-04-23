package org.commonmark.renderer.html;
/* loaded from: classes2.dex */
public interface UrlSanitizer {
    String sanitizeImageUrl(String str);

    String sanitizeLinkUrl(String str);
}
