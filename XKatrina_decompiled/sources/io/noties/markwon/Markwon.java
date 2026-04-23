package io.noties.markwon;

import android.content.Context;
import android.text.Spanned;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.noties.markwon.core.CorePlugin;
import java.util.List;
import org.commonmark.node.Node;
/* loaded from: classes2.dex */
public abstract class Markwon {

    /* loaded from: classes2.dex */
    public interface Builder {
        @NonNull
        Builder bufferType(@NonNull TextView.BufferType bufferType);

        @NonNull
        Markwon build();

        @NonNull
        Builder fallbackToRawInputWhenEmpty(boolean z);

        @NonNull
        Builder textSetter(@NonNull TextSetter textSetter);

        @NonNull
        Builder usePlugin(@NonNull MarkwonPlugin markwonPlugin);

        @NonNull
        Builder usePlugins(@NonNull Iterable<? extends MarkwonPlugin> iterable);
    }

    /* loaded from: classes2.dex */
    public interface TextSetter {
        void setText(@NonNull TextView textView, @NonNull Spanned spanned, @NonNull TextView.BufferType bufferType, @NonNull Runnable runnable);
    }

    @NonNull
    public abstract MarkwonConfiguration configuration();

    @Nullable
    public abstract <P extends MarkwonPlugin> P getPlugin(@NonNull Class<P> cls);

    @NonNull
    public abstract List<? extends MarkwonPlugin> getPlugins();

    public abstract boolean hasPlugin(@NonNull Class<? extends MarkwonPlugin> cls);

    @NonNull
    public abstract Node parse(@NonNull String str);

    @NonNull
    public abstract Spanned render(@NonNull Node node);

    @NonNull
    public abstract <P extends MarkwonPlugin> P requirePlugin(@NonNull Class<P> cls);

    public abstract void setMarkdown(@NonNull TextView textView, @NonNull String str);

    public abstract void setParsedMarkdown(@NonNull TextView textView, @NonNull Spanned spanned);

    @NonNull
    public abstract Spanned toMarkdown(@NonNull String str);

    @NonNull
    public static Markwon create(@NonNull Context context) {
        return builder(context).usePlugin(CorePlugin.create()).build();
    }

    @NonNull
    public static Builder builder(@NonNull Context context) {
        return new MarkwonBuilderImpl(context).usePlugin(CorePlugin.create());
    }

    @NonNull
    public static Builder builderNoCore(@NonNull Context context) {
        return new MarkwonBuilderImpl(context);
    }
}
