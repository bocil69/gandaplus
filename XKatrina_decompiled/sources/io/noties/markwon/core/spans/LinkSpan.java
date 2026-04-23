package io.noties.markwon.core.spans;

import android.text.TextPaint;
import android.text.style.URLSpan;
import android.view.View;
import androidx.annotation.NonNull;
import io.noties.markwon.LinkResolver;
import io.noties.markwon.core.MarkwonTheme;
/* loaded from: classes2.dex */
public class LinkSpan extends URLSpan {
    private final String link;
    private final LinkResolver resolver;
    private final MarkwonTheme theme;

    public LinkSpan(@NonNull MarkwonTheme markwonTheme, @NonNull String str, @NonNull LinkResolver linkResolver) {
        super(str);
        this.theme = markwonTheme;
        this.link = str;
        this.resolver = linkResolver;
    }

    @Override // android.text.style.URLSpan, android.text.style.ClickableSpan
    public void onClick(View view) {
        this.resolver.resolve(view, this.link);
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(@NonNull TextPaint textPaint) {
        this.theme.applyLinkStyle(textPaint);
    }

    @NonNull
    public String getLink() {
        return this.link;
    }
}
