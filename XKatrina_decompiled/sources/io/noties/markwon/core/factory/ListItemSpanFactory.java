package io.noties.markwon.core.factory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.noties.markwon.MarkwonConfiguration;
import io.noties.markwon.RenderProps;
import io.noties.markwon.SpanFactory;
import io.noties.markwon.core.CoreProps;
import io.noties.markwon.core.spans.BulletListItemSpan;
import io.noties.markwon.core.spans.OrderedListItemSpan;
/* loaded from: classes2.dex */
public class ListItemSpanFactory implements SpanFactory {
    @Override // io.noties.markwon.SpanFactory
    @Nullable
    public Object getSpans(@NonNull MarkwonConfiguration markwonConfiguration, @NonNull RenderProps renderProps) {
        if (CoreProps.ListItemType.BULLET == CoreProps.LIST_ITEM_TYPE.require(renderProps)) {
            return new BulletListItemSpan(markwonConfiguration.theme(), CoreProps.BULLET_LIST_ITEM_LEVEL.require(renderProps).intValue());
        }
        return new OrderedListItemSpan(markwonConfiguration.theme(), String.valueOf(CoreProps.ORDERED_LIST_ITEM_NUMBER.require(renderProps)) + ". ");
    }
}
