package io.noties.markwon;

import androidx.annotation.NonNull;
import io.noties.markwon.MarkwonVisitor;
import org.commonmark.node.Node;
/* loaded from: classes2.dex */
public class BlockHandlerDef implements MarkwonVisitor.BlockHandler {
    @Override // io.noties.markwon.MarkwonVisitor.BlockHandler
    public void blockStart(@NonNull MarkwonVisitor markwonVisitor, @NonNull Node node) {
        markwonVisitor.ensureNewLine();
    }

    @Override // io.noties.markwon.MarkwonVisitor.BlockHandler
    public void blockEnd(@NonNull MarkwonVisitor markwonVisitor, @NonNull Node node) {
        if (markwonVisitor.hasNext(node)) {
            markwonVisitor.ensureNewLine();
            markwonVisitor.forceNewLine();
        }
    }
}
