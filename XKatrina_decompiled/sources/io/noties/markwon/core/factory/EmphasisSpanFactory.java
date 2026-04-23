package io.noties.markwon.core.factory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.noties.markwon.MarkwonConfiguration;
import io.noties.markwon.RenderProps;
import io.noties.markwon.SpanFactory;
import io.noties.markwon.core.spans.EmphasisSpan;
/* loaded from: classes2.dex */
public class EmphasisSpanFactory implements SpanFactory {
    @Override // io.noties.markwon.SpanFactory
    @Nullable
    public Object getSpans(@NonNull MarkwonConfiguration markwonConfiguration, @NonNull RenderProps renderProps) {
        return new EmphasisSpan();
    }
}
