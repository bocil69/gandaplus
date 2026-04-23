package io.noties.markwon;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.noties.markwon.Markwon;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
/* loaded from: classes2.dex */
class MarkwonImpl extends Markwon {
    private final TextView.BufferType bufferType;
    private final MarkwonConfiguration configuration;
    private final boolean fallbackToRawInputWhenEmpty;
    private final Parser parser;
    private final List<MarkwonPlugin> plugins;
    @Nullable
    private final Markwon.TextSetter textSetter;
    private final MarkwonVisitorFactory visitorFactory;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MarkwonImpl(@NonNull TextView.BufferType bufferType, @Nullable Markwon.TextSetter textSetter, @NonNull Parser parser, @NonNull MarkwonVisitorFactory markwonVisitorFactory, @NonNull MarkwonConfiguration markwonConfiguration, @NonNull List<MarkwonPlugin> list, boolean z) {
        this.bufferType = bufferType;
        this.textSetter = textSetter;
        this.parser = parser;
        this.visitorFactory = markwonVisitorFactory;
        this.configuration = markwonConfiguration;
        this.plugins = list;
        this.fallbackToRawInputWhenEmpty = z;
    }

    @Override // io.noties.markwon.Markwon
    @NonNull
    public Node parse(@NonNull String str) {
        for (MarkwonPlugin markwonPlugin : this.plugins) {
            str = markwonPlugin.processMarkdown(str);
        }
        return this.parser.parse(str);
    }

    @Override // io.noties.markwon.Markwon
    @NonNull
    public Spanned render(@NonNull Node node) {
        for (MarkwonPlugin markwonPlugin : this.plugins) {
            markwonPlugin.beforeRender(node);
        }
        MarkwonVisitor create = this.visitorFactory.create();
        node.accept(create);
        for (MarkwonPlugin markwonPlugin2 : this.plugins) {
            markwonPlugin2.afterRender(node, create);
        }
        return create.builder().spannableStringBuilder();
    }

    @Override // io.noties.markwon.Markwon
    @NonNull
    public Spanned toMarkdown(@NonNull String str) {
        Spanned render = render(parse(str));
        return (TextUtils.isEmpty(render) && this.fallbackToRawInputWhenEmpty && !TextUtils.isEmpty(str)) ? new SpannableStringBuilder(str) : render;
    }

    @Override // io.noties.markwon.Markwon
    public void setMarkdown(@NonNull TextView textView, @NonNull String str) {
        setParsedMarkdown(textView, toMarkdown(str));
    }

    @Override // io.noties.markwon.Markwon
    public void setParsedMarkdown(@NonNull final TextView textView, @NonNull Spanned spanned) {
        for (MarkwonPlugin markwonPlugin : this.plugins) {
            markwonPlugin.beforeSetText(textView, spanned);
        }
        Markwon.TextSetter textSetter = this.textSetter;
        if (textSetter != null) {
            textSetter.setText(textView, spanned, this.bufferType, new Runnable() { // from class: io.noties.markwon.MarkwonImpl.1
                @Override // java.lang.Runnable
                public void run() {
                    for (MarkwonPlugin markwonPlugin2 : MarkwonImpl.this.plugins) {
                        markwonPlugin2.afterSetText(textView);
                    }
                }
            });
            return;
        }
        textView.setText(spanned, this.bufferType);
        for (MarkwonPlugin markwonPlugin2 : this.plugins) {
            markwonPlugin2.afterSetText(textView);
        }
    }

    @Override // io.noties.markwon.Markwon
    public boolean hasPlugin(@NonNull Class<? extends MarkwonPlugin> cls) {
        return getPlugin(cls) != null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [io.noties.markwon.MarkwonPlugin, java.lang.Object] */
    @Override // io.noties.markwon.Markwon
    @Nullable
    public <P extends MarkwonPlugin> P getPlugin(@NonNull Class<P> cls) {
        P p = null;
        for (MarkwonPlugin markwonPlugin : this.plugins) {
            if (cls.isAssignableFrom(markwonPlugin.getClass())) {
                p = markwonPlugin;
            }
        }
        return p;
    }

    @Override // io.noties.markwon.Markwon
    @NonNull
    public <P extends MarkwonPlugin> P requirePlugin(@NonNull Class<P> cls) {
        P p = (P) getPlugin(cls);
        if (p != null) {
            return p;
        }
        throw new IllegalStateException(String.format(Locale.US, "Requested plugin `%s` is not registered with this Markwon instance", cls.getName()));
    }

    @Override // io.noties.markwon.Markwon
    @NonNull
    public List<? extends MarkwonPlugin> getPlugins() {
        return Collections.unmodifiableList(this.plugins);
    }

    @Override // io.noties.markwon.Markwon
    @NonNull
    public MarkwonConfiguration configuration() {
        return this.configuration;
    }
}
