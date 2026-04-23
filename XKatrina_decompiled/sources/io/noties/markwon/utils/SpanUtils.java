package io.noties.markwon.utils;

import android.graphics.Canvas;
import android.text.Layout;
import android.text.Spanned;
import android.widget.TextView;
import androidx.annotation.NonNull;
import io.noties.markwon.core.spans.TextLayoutSpan;
import io.noties.markwon.core.spans.TextViewSpan;
/* loaded from: classes2.dex */
public abstract class SpanUtils {
    public static int width(@NonNull Canvas canvas, @NonNull CharSequence charSequence) {
        if (charSequence instanceof Spanned) {
            Spanned spanned = (Spanned) charSequence;
            Layout layoutOf = TextLayoutSpan.layoutOf(spanned);
            if (layoutOf != null) {
                return layoutOf.getWidth();
            }
            TextView textViewOf = TextViewSpan.textViewOf(spanned);
            if (textViewOf != null) {
                return (textViewOf.getWidth() - textViewOf.getPaddingLeft()) - textViewOf.getPaddingRight();
            }
        }
        return canvas.getWidth();
    }
}
