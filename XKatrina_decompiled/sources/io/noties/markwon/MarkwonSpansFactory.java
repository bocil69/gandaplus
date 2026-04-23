package io.noties.markwon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.commonmark.node.Node;
/* loaded from: classes2.dex */
public interface MarkwonSpansFactory {

    /* loaded from: classes2.dex */
    public interface Builder {
        @NonNull
        @Deprecated
        <N extends Node> Builder addFactory(@NonNull Class<N> cls, @NonNull SpanFactory spanFactory);

        @NonNull
        <N extends Node> Builder appendFactory(@NonNull Class<N> cls, @NonNull SpanFactory spanFactory);

        @NonNull
        MarkwonSpansFactory build();

        @Nullable
        <N extends Node> SpanFactory getFactory(@NonNull Class<N> cls);

        @NonNull
        <N extends Node> Builder prependFactory(@NonNull Class<N> cls, @NonNull SpanFactory spanFactory);

        @NonNull
        <N extends Node> SpanFactory requireFactory(@NonNull Class<N> cls);

        @NonNull
        <N extends Node> Builder setFactory(@NonNull Class<N> cls, @Nullable SpanFactory spanFactory);
    }

    @Nullable
    <N extends Node> SpanFactory get(@NonNull Class<N> cls);

    @NonNull
    <N extends Node> SpanFactory require(@NonNull Class<N> cls);
}
