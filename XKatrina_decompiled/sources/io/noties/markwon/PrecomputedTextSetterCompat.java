package io.noties.markwon;

import android.os.Build;
import android.text.Spanned;
import android.util.Log;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.PrecomputedTextCompat;
import io.noties.markwon.Markwon;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
/* loaded from: classes2.dex */
public class PrecomputedTextSetterCompat implements Markwon.TextSetter {
    private final Executor executor;

    @NonNull
    public static PrecomputedTextSetterCompat create(@NonNull Executor executor) {
        return new PrecomputedTextSetterCompat(executor);
    }

    PrecomputedTextSetterCompat(@NonNull Executor executor) {
        this.executor = executor;
    }

    @Override // io.noties.markwon.Markwon.TextSetter
    public void setText(@NonNull TextView textView, @NonNull final Spanned spanned, @NonNull final TextView.BufferType bufferType, @NonNull final Runnable runnable) {
        if (Build.VERSION.SDK_INT < 21) {
            applyText(textView, spanned, bufferType, runnable);
            return;
        }
        final WeakReference weakReference = new WeakReference(textView);
        this.executor.execute(new Runnable() { // from class: io.noties.markwon.PrecomputedTextSetterCompat.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    PrecomputedTextCompat precomputedText = PrecomputedTextSetterCompat.precomputedText((TextView) weakReference.get(), spanned);
                    if (precomputedText != null) {
                        PrecomputedTextSetterCompat.applyText((TextView) weakReference.get(), precomputedText, bufferType, runnable);
                    }
                } catch (Throwable th) {
                    Log.e("PrecomputdTxtSetterCmpt", "Exception during pre-computing text", th);
                    PrecomputedTextSetterCompat.applyText((TextView) weakReference.get(), spanned, bufferType, runnable);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public static PrecomputedTextCompat precomputedText(@Nullable TextView textView, @NonNull Spanned spanned) {
        PrecomputedTextCompat.Params build;
        if (textView == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 28) {
            build = new PrecomputedTextCompat.Params(textView.getTextMetricsParams());
        } else {
            PrecomputedTextCompat.Params.Builder builder = new PrecomputedTextCompat.Params.Builder(textView.getPaint());
            if (Build.VERSION.SDK_INT >= 23) {
                builder.setBreakStrategy(textView.getBreakStrategy()).setHyphenationFrequency(textView.getHyphenationFrequency());
            }
            build = builder.build();
        }
        return PrecomputedTextCompat.create(spanned, build);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void applyText(@Nullable final TextView textView, @NonNull final Spanned spanned, @NonNull final TextView.BufferType bufferType, @NonNull final Runnable runnable) {
        if (textView != null) {
            textView.post(new Runnable() { // from class: io.noties.markwon.PrecomputedTextSetterCompat.2
                @Override // java.lang.Runnable
                public void run() {
                    textView.setText(spanned, bufferType);
                    runnable.run();
                }
            });
        }
    }
}
