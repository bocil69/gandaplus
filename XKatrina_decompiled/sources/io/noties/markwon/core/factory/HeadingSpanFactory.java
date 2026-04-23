package io.noties.markwon.core.factory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.noties.markwon.MarkwonConfiguration;
import io.noties.markwon.RenderProps;
import io.noties.markwon.SpanFactory;
import io.noties.markwon.core.CoreProps;
import io.noties.markwon.core.spans.HeadingSpan;
/* loaded from: classes2.dex */
public class HeadingSpanFactory implements SpanFactory {
    @Override // io.noties.markwon.SpanFactory
    @Nullable
    public Object getSpans(@NonNull MarkwonConfiguration markwonConfiguration, @NonNull RenderProps renderProps) {
        return new HeadingSpan(markwonConfiguration.theme(), CoreProps.HEADING_LEVEL.require(renderProps).intValue());
    }
}
