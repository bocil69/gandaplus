package io.noties.markwon;

import android.text.Spanned;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.text.PrecomputedTextCompat;
import io.noties.markwon.Markwon;
import java.util.concurrent.Executor;
/* loaded from: classes2.dex */
public class PrecomputedFutureTextSetterCompat implements Markwon.TextSetter {
    @Nullable
    private final Executor executor;

    @NonNull
    public static PrecomputedFutureTextSetterCompat create(@Nullable Executor executor) {
        return new PrecomputedFutureTextSetterCompat(executor);
    }

    @NonNull
    public static PrecomputedFutureTextSetterCompat create() {
        return new PrecomputedFutureTextSetterCompat(null);
    }

    PrecomputedFutureTextSetterCompat(@Nullable Executor executor) {
        this.executor = executor;
    }

    @Override // io.noties.markwon.Markwon.TextSetter
    public void setText(@NonNull TextView textView, @NonNull Spanned spanned, @NonNull TextView.BufferType bufferType, @NonNull Runnable runnable) {
        if (textView instanceof AppCompatTextView) {
            AppCompatTextView appCompatTextView = (AppCompatTextView) textView;
            appCompatTextView.setTextFuture(PrecomputedTextCompat.getTextFuture(spanned, appCompatTextView.getTextMetricsParamsCompat(), this.executor));
            runnable.run();
            return;
        }
        throw new IllegalStateException("TextView provided is not an instance of AppCompatTextView, cannot call setTextFuture(), textView: " + textView);
    }
}
