package org.commonmark.renderer.html;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.commonmark.Extension;
import org.commonmark.internal.renderer.NodeRendererMap;
import org.commonmark.internal.util.Escaping;
import org.commonmark.node.Node;
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.Renderer;
/* loaded from: classes2.dex */
public class HtmlRenderer implements Renderer {
    private final List<AttributeProviderFactory> attributeProviderFactories;
    private final boolean escapeHtml;
    private final List<HtmlNodeRendererFactory> nodeRendererFactories;
    private final boolean percentEncodeUrls;
    private final boolean sanitizeUrls;
    private final String softbreak;
    private final UrlSanitizer urlSanitizer;

    /* loaded from: classes2.dex */
    public interface HtmlRendererExtension extends Extension {
        void extend(Builder builder);
    }

    private HtmlRenderer(Builder builder) {
        this.softbreak = builder.softbreak;
        this.escapeHtml = builder.escapeHtml;
        this.sanitizeUrls = builder.sanitizeUrls;
        this.percentEncodeUrls = builder.percentEncodeUrls;
        this.urlSanitizer = builder.urlSanitizer;
        this.attributeProviderFactories = new ArrayList(builder.attributeProviderFactories);
        this.nodeRendererFactories = new ArrayList(builder.nodeRendererFactories.size() + 1);
        this.nodeRendererFactories.addAll(builder.nodeRendererFactories);
        this.nodeRendererFactories.add(new HtmlNodeRendererFactory() { // from class: org.commonmark.renderer.html.HtmlRenderer.1
            @Override // org.commonmark.renderer.html.HtmlNodeRendererFactory
            public NodeRenderer create(HtmlNodeRendererContext context) {
                return new CoreHtmlNodeRenderer(context);
            }
        });
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override // org.commonmark.renderer.Renderer
    public void render(Node node, Appendable output) {
        if (node == null) {
            throw new NullPointerException("node must not be null");
        }
        RendererContext context = new RendererContext(new HtmlWriter(output));
        context.render(node);
    }

    @Override // org.commonmark.renderer.Renderer
    public String render(Node node) {
        if (node == null) {
            throw new NullPointerException("node must not be null");
        }
        StringBuilder sb = new StringBuilder();
        render(node, sb);
        return sb.toString();
    }

    /* loaded from: classes2.dex */
    public static class Builder {
        private String softbreak = "\n";
        private boolean escapeHtml = false;
        private boolean sanitizeUrls = false;
        private UrlSanitizer urlSanitizer = new DefaultUrlSanitizer();
        private boolean percentEncodeUrls = false;
        private List<AttributeProviderFactory> attributeProviderFactories = new ArrayList();
        private List<HtmlNodeRendererFactory> nodeRendererFactories = new ArrayList();

        public HtmlRenderer build() {
            return new HtmlRenderer(this);
        }

        public Builder softbreak(String softbreak) {
            this.softbreak = softbreak;
            return this;
        }

        public Builder escapeHtml(boolean escapeHtml) {
            this.escapeHtml = escapeHtml;
            return this;
        }

        public Builder sanitizeUrls(boolean sanitizeUrls) {
            this.sanitizeUrls = sanitizeUrls;
            return this;
        }

        public Builder urlSanitizer(UrlSanitizer urlSanitizer) {
            this.urlSanitizer = urlSanitizer;
            return this;
        }

        public Builder percentEncodeUrls(boolean percentEncodeUrls) {
            this.percentEncodeUrls = percentEncodeUrls;
            return this;
        }

        public Builder attributeProviderFactory(AttributeProviderFactory attributeProviderFactory) {
            if (attributeProviderFactory == null) {
                throw new NullPointerException("attributeProviderFactory must not be null");
            }
            this.attributeProviderFactories.add(attributeProviderFactory);
            return this;
        }

        public Builder nodeRendererFactory(HtmlNodeRendererFactory nodeRendererFactory) {
            if (nodeRendererFactory == null) {
                throw new NullPointerException("nodeRendererFactory must not be null");
            }
            this.nodeRendererFactories.add(nodeRendererFactory);
            return this;
        }

        public Builder extensions(Iterable<? extends Extension> extensions) {
            if (extensions == null) {
                throw new NullPointerException("extensions must not be null");
            }
            for (Extension extension : extensions) {
                if (extension instanceof HtmlRendererExtension) {
                    HtmlRendererExtension htmlRendererExtension = (HtmlRendererExtension) extension;
                    htmlRendererExtension.extend(this);
                }
            }
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class RendererContext implements HtmlNodeRendererContext, AttributeProviderContext {
        private final List<AttributeProvider> attributeProviders;
        private final HtmlWriter htmlWriter;
        private final NodeRendererMap nodeRendererMap;

        private RendererContext(HtmlWriter htmlWriter) {
            this.nodeRendererMap = new NodeRendererMap();
            this.htmlWriter = htmlWriter;
            this.attributeProviders = new ArrayList(HtmlRenderer.this.attributeProviderFactories.size());
            for (AttributeProviderFactory attributeProviderFactory : HtmlRenderer.this.attributeProviderFactories) {
                this.attributeProviders.add(attributeProviderFactory.create(this));
            }
            for (int i = HtmlRenderer.this.nodeRendererFactories.size() - 1; i >= 0; i--) {
                HtmlNodeRendererFactory nodeRendererFactory = (HtmlNodeRendererFactory) HtmlRenderer.this.nodeRendererFactories.get(i);
                NodeRenderer nodeRenderer = nodeRendererFactory.create(this);
                this.nodeRendererMap.add(nodeRenderer);
            }
        }

        @Override // org.commonmark.renderer.html.HtmlNodeRendererContext
        public boolean shouldEscapeHtml() {
            return HtmlRenderer.this.escapeHtml;
        }

        @Override // org.commonmark.renderer.html.HtmlNodeRendererContext
        public boolean shouldSanitizeUrls() {
            return HtmlRenderer.this.sanitizeUrls;
        }

        @Override // org.commonmark.renderer.html.HtmlNodeRendererContext
        public UrlSanitizer urlSanitizer() {
            return HtmlRenderer.this.urlSanitizer;
        }

        @Override // org.commonmark.renderer.html.HtmlNodeRendererContext
        public String encodeUrl(String url) {
            if (HtmlRenderer.this.percentEncodeUrls) {
                return Escaping.percentEncodeUrl(url);
            }
            return url;
        }

        @Override // org.commonmark.renderer.html.HtmlNodeRendererContext
        public Map<String, String> extendAttributes(Node node, String tagName, Map<String, String> attributes) {
            Map<String, String> attrs = new LinkedHashMap<>(attributes);
            setCustomAttributes(node, tagName, attrs);
            return attrs;
        }

        @Override // org.commonmark.renderer.html.HtmlNodeRendererContext
        public HtmlWriter getWriter() {
            return this.htmlWriter;
        }

        @Override // org.commonmark.renderer.html.HtmlNodeRendererContext
        public String getSoftbreak() {
            return HtmlRenderer.this.softbreak;
        }

        @Override // org.commonmark.renderer.html.HtmlNodeRendererContext
        public void render(Node node) {
            this.nodeRendererMap.render(node);
        }

        private void setCustomAttributes(Node node, String tagName, Map<String, String> attrs) {
            for (AttributeProvider attributeProvider : this.attributeProviders) {
                attributeProvider.setAttributes(node, tagName, attrs);
            }
        }
    }
}
