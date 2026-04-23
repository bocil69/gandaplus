package org.commonmark.renderer.text;

import java.util.ArrayList;
import java.util.List;
import org.commonmark.Extension;
import org.commonmark.internal.renderer.NodeRendererMap;
import org.commonmark.node.Node;
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.Renderer;
/* loaded from: classes2.dex */
public class TextContentRenderer implements Renderer {
    private final List<TextContentNodeRendererFactory> nodeRendererFactories;
    private final boolean stripNewlines;

    /* loaded from: classes2.dex */
    public interface TextContentRendererExtension extends Extension {
        void extend(Builder builder);
    }

    private TextContentRenderer(Builder builder) {
        this.stripNewlines = builder.stripNewlines;
        this.nodeRendererFactories = new ArrayList(builder.nodeRendererFactories.size() + 1);
        this.nodeRendererFactories.addAll(builder.nodeRendererFactories);
        this.nodeRendererFactories.add(new TextContentNodeRendererFactory() { // from class: org.commonmark.renderer.text.TextContentRenderer.1
            @Override // org.commonmark.renderer.text.TextContentNodeRendererFactory
            public NodeRenderer create(TextContentNodeRendererContext context) {
                return new CoreTextContentNodeRenderer(context);
            }
        });
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override // org.commonmark.renderer.Renderer
    public void render(Node node, Appendable output) {
        RendererContext context = new RendererContext(new TextContentWriter(output));
        context.render(node);
    }

    @Override // org.commonmark.renderer.Renderer
    public String render(Node node) {
        StringBuilder sb = new StringBuilder();
        render(node, sb);
        return sb.toString();
    }

    /* loaded from: classes2.dex */
    public static class Builder {
        private boolean stripNewlines = false;
        private List<TextContentNodeRendererFactory> nodeRendererFactories = new ArrayList();

        public TextContentRenderer build() {
            return new TextContentRenderer(this);
        }

        public Builder stripNewlines(boolean stripNewlines) {
            this.stripNewlines = stripNewlines;
            return this;
        }

        public Builder nodeRendererFactory(TextContentNodeRendererFactory nodeRendererFactory) {
            this.nodeRendererFactories.add(nodeRendererFactory);
            return this;
        }

        public Builder extensions(Iterable<? extends Extension> extensions) {
            for (Extension extension : extensions) {
                if (extension instanceof TextContentRendererExtension) {
                    TextContentRendererExtension textContentRendererExtension = (TextContentRendererExtension) extension;
                    textContentRendererExtension.extend(this);
                }
            }
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class RendererContext implements TextContentNodeRendererContext {
        private final NodeRendererMap nodeRendererMap;
        private final TextContentWriter textContentWriter;

        private RendererContext(TextContentWriter textContentWriter) {
            this.nodeRendererMap = new NodeRendererMap();
            this.textContentWriter = textContentWriter;
            for (int i = TextContentRenderer.this.nodeRendererFactories.size() - 1; i >= 0; i--) {
                TextContentNodeRendererFactory nodeRendererFactory = (TextContentNodeRendererFactory) TextContentRenderer.this.nodeRendererFactories.get(i);
                NodeRenderer nodeRenderer = nodeRendererFactory.create(this);
                this.nodeRendererMap.add(nodeRenderer);
            }
        }

        @Override // org.commonmark.renderer.text.TextContentNodeRendererContext
        public boolean stripNewlines() {
            return TextContentRenderer.this.stripNewlines;
        }

        @Override // org.commonmark.renderer.text.TextContentNodeRendererContext
        public TextContentWriter getWriter() {
            return this.textContentWriter;
        }

        @Override // org.commonmark.renderer.text.TextContentNodeRendererContext
        public void render(Node node) {
            this.nodeRendererMap.render(node);
        }
    }
}
