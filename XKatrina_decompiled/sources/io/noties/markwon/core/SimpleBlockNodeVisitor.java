package io.noties.markwon.core;

import androidx.annotation.NonNull;
import io.noties.markwon.MarkwonVisitor;
import org.commonmark.node.Node;
/* loaded from: classes2.dex */
public class SimpleBlockNodeVisitor implements MarkwonVisitor.NodeVisitor<Node> {
    @Override // io.noties.markwon.MarkwonVisitor.NodeVisitor
    public void visit(@NonNull MarkwonVisitor markwonVisitor, @NonNull Node node) {
        markwonVisitor.blockStart(node);
        int length = markwonVisitor.length();
        markwonVisitor.visitChildren(node);
        markwonVisitor.setSpansForNodeOptional((MarkwonVisitor) node, length);
        markwonVisitor.blockEnd(node);
    }
}
