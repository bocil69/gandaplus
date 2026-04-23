package io.noties.markwon;

import androidx.annotation.NonNull;
import io.noties.markwon.MarkwonVisitor;
import org.commonmark.node.SoftLineBreak;
/* loaded from: classes2.dex */
public class SoftBreakAddsNewLinePlugin extends AbstractMarkwonPlugin {
    @NonNull
    public static SoftBreakAddsNewLinePlugin create() {
        return new SoftBreakAddsNewLinePlugin();
    }

    @Override // io.noties.markwon.AbstractMarkwonPlugin, io.noties.markwon.MarkwonPlugin
    public void configureVisitor(@NonNull MarkwonVisitor.Builder builder) {
        builder.on(SoftLineBreak.class, new MarkwonVisitor.NodeVisitor<SoftLineBreak>() { // from class: io.noties.markwon.SoftBreakAddsNewLinePlugin.1
            @Override // io.noties.markwon.MarkwonVisitor.NodeVisitor
            public void visit(@NonNull MarkwonVisitor markwonVisitor, @NonNull SoftLineBreak softLineBreak) {
                markwonVisitor.ensureNewLine();
            }
        });
    }
}
