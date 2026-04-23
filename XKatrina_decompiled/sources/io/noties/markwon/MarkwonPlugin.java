package io.noties.markwon;

import android.text.Spanned;
import android.widget.TextView;
import androidx.annotation.NonNull;
import io.noties.markwon.MarkwonConfiguration;
import io.noties.markwon.MarkwonSpansFactory;
import io.noties.markwon.MarkwonVisitor;
import io.noties.markwon.core.MarkwonTheme;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
/* loaded from: classes2.dex */
public interface MarkwonPlugin {

    /* loaded from: classes2.dex */
    public interface Action<P extends MarkwonPlugin> {
        void apply(@NonNull P p);
    }

    /* loaded from: classes2.dex */
    public interface Registry {
        @NonNull
        <P extends MarkwonPlugin> P require(@NonNull Class<P> cls);

        <P extends MarkwonPlugin> void require(@NonNull Class<P> cls, @NonNull Action<? super P> action);
    }

    void afterRender(@NonNull Node node, @NonNull MarkwonVisitor markwonVisitor);

    void afterSetText(@NonNull TextView textView);

    void beforeRender(@NonNull Node node);

    void beforeSetText(@NonNull TextView textView, @NonNull Spanned spanned);

    void configure(@NonNull Registry registry);

    void configureConfiguration(@NonNull MarkwonConfiguration.Builder builder);

    void configureParser(@NonNull Parser.Builder builder);

    void configureSpansFactory(@NonNull MarkwonSpansFactory.Builder builder);

    void configureTheme(@NonNull MarkwonTheme.Builder builder);

    void configureVisitor(@NonNull MarkwonVisitor.Builder builder);

    @NonNull
    String processMarkdown(@NonNull String str);
}
