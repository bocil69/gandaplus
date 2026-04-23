package io.noties.markwon;

import androidx.annotation.NonNull;
import io.noties.markwon.MarkwonVisitor;
/* loaded from: classes2.dex */
abstract class MarkwonVisitorFactory {
    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public abstract MarkwonVisitor create();

    MarkwonVisitorFactory() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static MarkwonVisitorFactory create(@NonNull final MarkwonVisitor.Builder builder, @NonNull final MarkwonConfiguration markwonConfiguration) {
        return new MarkwonVisitorFactory() { // from class: io.noties.markwon.MarkwonVisitorFactory.1
            @Override // io.noties.markwon.MarkwonVisitorFactory
            @NonNull
            MarkwonVisitor create() {
                return MarkwonVisitor.Builder.this.build(markwonConfiguration, new RenderPropsImpl());
            }
        };
    }
}
