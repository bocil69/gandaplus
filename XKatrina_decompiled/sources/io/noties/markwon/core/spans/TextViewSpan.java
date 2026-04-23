package io.noties.markwon.core.spans;

import android.text.Spannable;
import android.text.Spanned;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;
/* loaded from: classes2.dex */
public class TextViewSpan {
    private final WeakReference<TextView> reference;

    @Nullable
    public static TextView textViewOf(@NonNull CharSequence charSequence) {
        if (charSequence instanceof Spanned) {
            return textViewOf((Spanned) charSequence);
        }
        return null;
    }

    @Nullable
    public static TextView textViewOf(@NonNull Spanned spanned) {
        TextViewSpan[] textViewSpanArr = (TextViewSpan[]) spanned.getSpans(0, spanned.length(), TextViewSpan.class);
        if (textViewSpanArr == null || textViewSpanArr.length <= 0) {
            return null;
        }
        return textViewSpanArr[0].textView();
    }

    public static void applyTo(@NonNull Spannable spannable, @NonNull TextView textView) {
        TextViewSpan[] textViewSpanArr = (TextViewSpan[]) spannable.getSpans(0, spannable.length(), TextViewSpan.class);
        if (textViewSpanArr != null) {
            for (TextViewSpan textViewSpan : textViewSpanArr) {
                spannable.removeSpan(textViewSpan);
            }
        }
        spannable.setSpan(new TextViewSpan(textView), 0, spannable.length(), 18);
    }

    public TextViewSpan(@NonNull TextView textView) {
        this.reference = new WeakReference<>(textView);
    }

    @Nullable
    public TextView textView() {
        return this.reference.get();
    }
}
