package org.commonmark.renderer.html;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.BlockQuote;
import org.commonmark.node.BulletList;
import org.commonmark.node.Code;
import org.commonmark.node.Document;
import org.commonmark.node.Emphasis;
import org.commonmark.node.FencedCodeBlock;
import org.commonmark.node.HardLineBreak;
import org.commonmark.node.Heading;
import org.commonmark.node.HtmlBlock;
import org.commonmark.node.HtmlInline;
import org.commonmark.node.Image;
import org.commonmark.node.IndentedCodeBlock;
import org.commonmark.node.Link;
import org.commonmark.node.ListBlock;
import org.commonmark.node.ListItem;
import org.commonmark.node.Node;
import org.commonmark.node.OrderedList;
import org.commonmark.node.Paragraph;
import org.commonmark.node.SoftLineBreak;
import org.commonmark.node.StrongEmphasis;
import org.commonmark.node.Text;
import org.commonmark.node.ThematicBreak;
import org.commonmark.renderer.NodeRenderer;
/* loaded from: classes2.dex */
public class CoreHtmlNodeRenderer extends AbstractVisitor implements NodeRenderer {
    protected final HtmlNodeRendererContext context;
    private final HtmlWriter html;

    public CoreHtmlNodeRenderer(HtmlNodeRendererContext context) {
        this.context = context;
        this.html = context.getWriter();
    }

    @Override // org.commonmark.renderer.NodeRenderer
    public Set<Class<? extends Node>> getNodeTypes() {
        return new HashSet(Arrays.asList(Document.class, Heading.class, Paragraph.class, BlockQuote.class, BulletList.class, FencedCodeBlock.class, HtmlBlock.class, ThematicBreak.class, IndentedCodeBlock.class, Link.class, ListItem.class, OrderedList.class, Image.class, Emphasis.class, StrongEmphasis.class, Text.class, Code.class, HtmlInline.class, SoftLineBreak.class, HardLineBreak.class));
    }

    @Override // org.commonmark.renderer.NodeRenderer
    public void render(Node node) {
        node.accept(this);
    }

    @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
    public void visit(Document document) {
        visitChildren(document);
    }

    @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
    public void visit(Heading heading) {
        String htag = "h" + heading.getLevel();
        this.html.line();
        this.html.tag(htag, getAttrs(heading, htag));
        visitChildren(heading);
        this.html.tag('/' + htag);
        this.html.line();
    }

    @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
    public void visit(Paragraph paragraph) {
        boolean inTightList = isInTightList(paragraph);
        if (!inTightList) {
            this.html.line();
            this.html.tag("p", getAttrs(paragraph, "p"));
        }
        visitChildren(paragraph);
        if (!inTightList) {
            this.html.tag("/p");
            this.html.line();
        }
    }

    @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
    public void visit(BlockQuote blockQuote) {
        this.html.line();
        this.html.tag("blockquote", getAttrs(blockQuote, "blockquote"));
        this.html.line();
        visitChildren(blockQuote);
        this.html.line();
        this.html.tag("/blockquote");
        this.html.line();
    }

    @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
    public void visit(BulletList bulletList) {
        renderListBlock(bulletList, "ul", getAttrs(bulletList, "ul"));
    }

    @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
    public void visit(FencedCodeBlock fencedCodeBlock) {
        String language;
        String literal = fencedCodeBlock.getLiteral();
        Map<String, String> attributes = new LinkedHashMap<>();
        String info = fencedCodeBlock.getInfo();
        if (info != null && !info.isEmpty()) {
            int space = info.indexOf(" ");
            if (space == -1) {
                language = info;
            } else {
                language = info.substring(0, space);
            }
            attributes.put("class", "language-" + language);
        }
        renderCodeBlock(literal, fencedCodeBlock, attributes);
    }

    @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
    public void visit(HtmlBlock htmlBlock) {
        this.html.line();
        if (this.context.shouldEscapeHtml()) {
            this.html.tag("p", getAttrs(htmlBlock, "p"));
            this.html.text(htmlBlock.getLiteral());
            this.html.tag("/p");
        } else {
            this.html.raw(htmlBlock.getLiteral());
        }
        this.html.line();
    }

    @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
    public void visit(ThematicBreak thematicBreak) {
        this.html.line();
        this.html.tag("hr", getAttrs(thematicBreak, "hr"), true);
        this.html.line();
    }

    @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
    public void visit(IndentedCodeBlock indentedCodeBlock) {
        renderCodeBlock(indentedCodeBlock.getLiteral(), indentedCodeBlock, Collections.emptyMap());
    }

    @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
    public void visit(Link link) {
        Map<String, String> attrs = new LinkedHashMap<>();
        String url = link.getDestination();
        if (this.context.shouldSanitizeUrls()) {
            url = this.context.urlSanitizer().sanitizeLinkUrl(url);
            attrs.put("rel", "nofollow");
        }
        attrs.put("href", this.context.encodeUrl(url));
        if (link.getTitle() != null) {
            attrs.put("title", link.getTitle());
        }
        this.html.tag("a", getAttrs(link, "a", attrs));
        visitChildren(link);
        this.html.tag("/a");
    }

    @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
    public void visit(ListItem listItem) {
        this.html.tag("li", getAttrs(listItem, "li"));
        visitChildren(listItem);
        this.html.tag("/li");
        this.html.line();
    }

    @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
    public void visit(OrderedList orderedList) {
        int start = orderedList.getStartNumber();
        Map<String, String> attrs = new LinkedHashMap<>();
        if (start != 1) {
            attrs.put("start", String.valueOf(start));
        }
        renderListBlock(orderedList, "ol", getAttrs(orderedList, "ol", attrs));
    }

    @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
    public void visit(Image image) {
        String url = image.getDestination();
        AltTextVisitor altTextVisitor = new AltTextVisitor();
        image.accept(altTextVisitor);
        String altText = altTextVisitor.getAltText();
        Map<String, String> attrs = new LinkedHashMap<>();
        if (this.context.shouldSanitizeUrls()) {
            url = this.context.urlSanitizer().sanitizeImageUrl(url);
        }
        attrs.put("src", this.context.encodeUrl(url));
        attrs.put("alt", altText);
        if (image.getTitle() != null) {
            attrs.put("title", image.getTitle());
        }
        this.html.tag("img", getAttrs(image, "img", attrs), true);
    }

    @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
    public void visit(Emphasis emphasis) {
        this.html.tag("em", getAttrs(emphasis, "em"));
        visitChildren(emphasis);
        this.html.tag("/em");
    }

    @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
    public void visit(StrongEmphasis strongEmphasis) {
        this.html.tag("strong", getAttrs(strongEmphasis, "strong"));
        visitChildren(strongEmphasis);
        this.html.tag("/strong");
    }

    @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
    public void visit(Text text) {
        this.html.text(text.getLiteral());
    }

    @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
    public void visit(Code code) {
        this.html.tag("code", getAttrs(code, "code"));
        this.html.text(code.getLiteral());
        this.html.tag("/code");
    }

    @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
    public void visit(HtmlInline htmlInline) {
        if (this.context.shouldEscapeHtml()) {
            this.html.text(htmlInline.getLiteral());
        } else {
            this.html.raw(htmlInline.getLiteral());
        }
    }

    @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
    public void visit(SoftLineBreak softLineBreak) {
        this.html.raw(this.context.getSoftbreak());
    }

    @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
    public void visit(HardLineBreak hardLineBreak) {
        this.html.tag("br", getAttrs(hardLineBreak, "br"), true);
        this.html.line();
    }

    @Override // org.commonmark.node.AbstractVisitor
    protected void visitChildren(Node parent) {
        Node node = parent.getFirstChild();
        while (node != null) {
            Node next = node.getNext();
            this.context.render(node);
            node = next;
        }
    }

    private void renderCodeBlock(String literal, Node node, Map<String, String> attributes) {
        this.html.line();
        this.html.tag("pre", getAttrs(node, "pre"));
        this.html.tag("code", getAttrs(node, "code", attributes));
        this.html.text(literal);
        this.html.tag("/code");
        this.html.tag("/pre");
        this.html.line();
    }

    private void renderListBlock(ListBlock listBlock, String tagName, Map<String, String> attributes) {
        this.html.line();
        this.html.tag(tagName, attributes);
        this.html.line();
        visitChildren(listBlock);
        this.html.line();
        this.html.tag('/' + tagName);
        this.html.line();
    }

    private boolean isInTightList(Paragraph paragraph) {
        Node parent = paragraph.getParent();
        if (parent != null) {
            Node gramps = parent.getParent();
            if (gramps instanceof ListBlock) {
                ListBlock list = (ListBlock) gramps;
                return list.isTight();
            }
        }
        return false;
    }

    private Map<String, String> getAttrs(Node node, String tagName) {
        return getAttrs(node, tagName, Collections.emptyMap());
    }

    private Map<String, String> getAttrs(Node node, String tagName, Map<String, String> defaultAttributes) {
        return this.context.extendAttributes(node, tagName, defaultAttributes);
    }

    /* loaded from: classes2.dex */
    private static class AltTextVisitor extends AbstractVisitor {
        private final StringBuilder sb;

        private AltTextVisitor() {
            this.sb = new StringBuilder();
        }

        String getAltText() {
            return this.sb.toString();
        }

        @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
        public void visit(Text text) {
            this.sb.append(text.getLiteral());
        }

        @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
        public void visit(SoftLineBreak softLineBreak) {
            this.sb.append('\n');
        }

        @Override // org.commonmark.node.AbstractVisitor, org.commonmark.node.Visitor
        public void visit(HardLineBreak hardLineBreak) {
            this.sb.append('\n');
        }
    }
}
