package io.noties.markwon;

import android.text.Spanned;
import android.widget.TextView;
import androidx.annotation.NonNull;
import io.noties.markwon.MarkwonConfiguration;
import io.noties.markwon.MarkwonPlugin;
import io.noties.markwon.MarkwonSpansFactory;
import io.noties.markwon.MarkwonVisitor;
import io.noties.markwon.core.MarkwonTheme;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
/* loaded from: classes2.dex */
public abstract class AbstractMarkwonPlugin implements MarkwonPlugin {
    @Override // io.noties.markwon.MarkwonPlugin
    public void afterRender(@NonNull Node node, @NonNull MarkwonVisitor markwonVisitor) {
    }

    @Override // io.noties.markwon.MarkwonPlugin
    public void afterSetText(@NonNull TextView textView) {
    }

    @Override // io.noties.markwon.MarkwonPlugin
    public void beforeRender(@NonNull Node node) {
    }

    @Override // io.noties.markwon.MarkwonPlugin
    public void beforeSetText(@NonNull TextView textView, @NonNull Spanned spanned) {
    }

    @Override // io.noties.markwon.MarkwonPlugin
    public void configure(@NonNull MarkwonPlugin.Registry registry) {
    }

    @Override // io.noties.markwon.MarkwonPlugin
    public void configureConfiguration(@NonNull MarkwonConfiguration.Builder builder) {
    }

    @Override // io.noties.markwon.MarkwonPlugin
    public void configureParser(@NonNull Parser.Builder builder) {
    }

    @Override // io.noties.markwon.MarkwonPlugin
    public void configureSpansFactory(@NonNull MarkwonSpansFactory.Builder builder) {
    }

    @Override // io.noties.markwon.MarkwonPlugin
    public void configureTheme(@NonNull MarkwonTheme.Builder builder) {
    }

    @Override // io.noties.markwon.MarkwonPlugin
    public void configureVisitor(@NonNull MarkwonVisitor.Builder builder) {
    }

    @Override // io.noties.markwon.MarkwonPlugin
    @NonNull
    public String processMarkdown(@NonNull String str) {
        return str;
    }
}
