package io.noties.markwon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.commonmark.node.Node;
import org.commonmark.node.Visitor;
/* loaded from: classes2.dex */
public interface MarkwonVisitor extends Visitor {

    /* loaded from: classes2.dex */
    public interface BlockHandler {
        void blockEnd(@NonNull MarkwonVisitor markwonVisitor, @NonNull Node node);

        void blockStart(@NonNull MarkwonVisitor markwonVisitor, @NonNull Node node);
    }

    /* loaded from: classes2.dex */
    public interface Builder {
        @NonNull
        Builder blockHandler(@NonNull BlockHandler blockHandler);

        @NonNull
        MarkwonVisitor build(@NonNull MarkwonConfiguration markwonConfiguration, @NonNull RenderProps renderProps);

        @NonNull
        <N extends Node> Builder on(@NonNull Class<N> cls, @Nullable NodeVisitor<? super N> nodeVisitor);
    }

    /* loaded from: classes2.dex */
    public interface NodeVisitor<N extends Node> {
        void visit(@NonNull MarkwonVisitor markwonVisitor, @NonNull N n);
    }

    void blockEnd(@NonNull Node node);

    void blockStart(@NonNull Node node);

    @NonNull
    SpannableBuilder builder();

    void clear();

    @NonNull
    MarkwonConfiguration configuration();

    void ensureNewLine();

    void forceNewLine();

    boolean hasNext(@NonNull Node node);

    int length();

    @NonNull
    RenderProps renderProps();

    void setSpans(int i, @Nullable Object obj);

    <N extends Node> void setSpansForNode(@NonNull Class<N> cls, int i);

    <N extends Node> void setSpansForNode(@NonNull N n, int i);

    <N extends Node> void setSpansForNodeOptional(@NonNull Class<N> cls, int i);

    <N extends Node> void setSpansForNodeOptional(@NonNull N n, int i);

    void visitChildren(@NonNull Node node);
}
