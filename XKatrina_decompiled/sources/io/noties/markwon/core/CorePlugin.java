package io.noties.markwon.core;

import android.text.Spannable;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import io.noties.markwon.AbstractMarkwonPlugin;
import io.noties.markwon.MarkwonConfiguration;
import io.noties.markwon.MarkwonSpansFactory;
import io.noties.markwon.MarkwonVisitor;
import io.noties.markwon.RenderProps;
import io.noties.markwon.SpanFactory;
import io.noties.markwon.core.CoreProps;
import io.noties.markwon.core.factory.BlockQuoteSpanFactory;
import io.noties.markwon.core.factory.CodeBlockSpanFactory;
import io.noties.markwon.core.factory.CodeSpanFactory;
import io.noties.markwon.core.factory.EmphasisSpanFactory;
import io.noties.markwon.core.factory.HeadingSpanFactory;
import io.noties.markwon.core.factory.LinkSpanFactory;
import io.noties.markwon.core.factory.ListItemSpanFactory;
import io.noties.markwon.core.factory.StrongEmphasisSpanFactory;
import io.noties.markwon.core.factory.ThematicBreakSpanFactory;
import io.noties.markwon.core.spans.OrderedListItemSpan;
import io.noties.markwon.core.spans.TextViewSpan;
import io.noties.markwon.image.ImageProps;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.commonmark.node.Block;
import org.commonmark.node.BlockQuote;
import org.commonmark.node.BulletList;
import org.commonmark.node.Code;
import org.commonmark.node.Emphasis;
import org.commonmark.node.FencedCodeBlock;
import org.commonmark.node.HardLineBreak;
import org.commonmark.node.Heading;
import org.commonmark.node.HtmlBlock;
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
/* loaded from: classes2.dex */
public class CorePlugin extends AbstractMarkwonPlugin {
    private boolean hasExplicitMovementMethod;
    private final List<OnTextAddedListener> onTextAddedListeners = new ArrayList(0);

    /* loaded from: classes2.dex */
    public interface OnTextAddedListener {
        void onTextAdded(@NonNull MarkwonVisitor markwonVisitor, @NonNull String str, int i);
    }

    @NonNull
    public static CorePlugin create() {
        return new CorePlugin();
    }

    @NonNull
    public static Set<Class<? extends Block>> enabledBlockTypes() {
        return new HashSet(Arrays.asList(BlockQuote.class, Heading.class, FencedCodeBlock.class, HtmlBlock.class, ThematicBreak.class, ListBlock.class, IndentedCodeBlock.class));
    }

    protected CorePlugin() {
    }

    @NonNull
    public CorePlugin hasExplicitMovementMethod(boolean z) {
        this.hasExplicitMovementMethod = z;
        return this;
    }

    @NonNull
    public CorePlugin addOnTextAddedListener(@NonNull OnTextAddedListener onTextAddedListener) {
        this.onTextAddedListeners.add(onTextAddedListener);
        return this;
    }

    @Override // io.noties.markwon.AbstractMarkwonPlugin, io.noties.markwon.MarkwonPlugin
    public void configureVisitor(@NonNull MarkwonVisitor.Builder builder) {
        text(builder);
        strongEmphasis(builder);
        emphasis(builder);
        blockQuote(builder);
        code(builder);
        fencedCodeBlock(builder);
        indentedCodeBlock(builder);
        image(builder);
        bulletList(builder);
        orderedList(builder);
        listItem(builder);
        thematicBreak(builder);
        heading(builder);
        softLineBreak(builder);
        hardLineBreak(builder);
        paragraph(builder);
        link(builder);
    }

    @Override // io.noties.markwon.AbstractMarkwonPlugin, io.noties.markwon.MarkwonPlugin
    public void configureSpansFactory(@NonNull MarkwonSpansFactory.Builder builder) {
        CodeBlockSpanFactory codeBlockSpanFactory = new CodeBlockSpanFactory();
        builder.setFactory(StrongEmphasis.class, new StrongEmphasisSpanFactory()).setFactory(Emphasis.class, new EmphasisSpanFactory()).setFactory(BlockQuote.class, new BlockQuoteSpanFactory()).setFactory(Code.class, new CodeSpanFactory()).setFactory(FencedCodeBlock.class, codeBlockSpanFactory).setFactory(IndentedCodeBlock.class, codeBlockSpanFactory).setFactory(ListItem.class, new ListItemSpanFactory()).setFactory(Heading.class, new HeadingSpanFactory()).setFactory(Link.class, new LinkSpanFactory()).setFactory(ThematicBreak.class, new ThematicBreakSpanFactory());
    }

    @Override // io.noties.markwon.AbstractMarkwonPlugin, io.noties.markwon.MarkwonPlugin
    public void beforeSetText(@NonNull TextView textView, @NonNull Spanned spanned) {
        OrderedListItemSpan.measure(textView, spanned);
        if (spanned instanceof Spannable) {
            TextViewSpan.applyTo((Spannable) spanned, textView);
        }
    }

    @Override // io.noties.markwon.AbstractMarkwonPlugin, io.noties.markwon.MarkwonPlugin
    public void afterSetText(@NonNull TextView textView) {
        if (this.hasExplicitMovementMethod || textView.getMovementMethod() != null) {
            return;
        }
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void text(@NonNull MarkwonVisitor.Builder builder) {
        builder.on(Text.class, new MarkwonVisitor.NodeVisitor<Text>() { // from class: io.noties.markwon.core.CorePlugin.1
            @Override // io.noties.markwon.MarkwonVisitor.NodeVisitor
            public void visit(@NonNull MarkwonVisitor markwonVisitor, @NonNull Text text) {
                String literal = text.getLiteral();
                markwonVisitor.builder().append(literal);
                if (CorePlugin.this.onTextAddedListeners.isEmpty()) {
                    return;
                }
                int length = markwonVisitor.length() - literal.length();
                for (OnTextAddedListener onTextAddedListener : CorePlugin.this.onTextAddedListeners) {
                    onTextAddedListener.onTextAdded(markwonVisitor, literal, length);
                }
            }
        });
    }

    private static void strongEmphasis(@NonNull MarkwonVisitor.Builder builder) {
        builder.on(StrongEmphasis.class, new MarkwonVisitor.NodeVisitor<StrongEmphasis>() { // from class: io.noties.markwon.core.CorePlugin.2
            @Override // io.noties.markwon.MarkwonVisitor.NodeVisitor
            public void visit(@NonNull MarkwonVisitor markwonVisitor, @NonNull StrongEmphasis strongEmphasis) {
                int length = markwonVisitor.length();
                markwonVisitor.visitChildren(strongEmphasis);
                markwonVisitor.setSpansForNodeOptional((MarkwonVisitor) strongEmphasis, length);
            }
        });
    }

    private static void emphasis(@NonNull MarkwonVisitor.Builder builder) {
        builder.on(Emphasis.class, new MarkwonVisitor.NodeVisitor<Emphasis>() { // from class: io.noties.markwon.core.CorePlugin.3
            @Override // io.noties.markwon.MarkwonVisitor.NodeVisitor
            public void visit(@NonNull MarkwonVisitor markwonVisitor, @NonNull Emphasis emphasis) {
                int length = markwonVisitor.length();
                markwonVisitor.visitChildren(emphasis);
                markwonVisitor.setSpansForNodeOptional((MarkwonVisitor) emphasis, length);
            }
        });
    }

    private static void blockQuote(@NonNull MarkwonVisitor.Builder builder) {
        builder.on(BlockQuote.class, new MarkwonVisitor.NodeVisitor<BlockQuote>() { // from class: io.noties.markwon.core.CorePlugin.4
            @Override // io.noties.markwon.MarkwonVisitor.NodeVisitor
            public void visit(@NonNull MarkwonVisitor markwonVisitor, @NonNull BlockQuote blockQuote) {
                markwonVisitor.blockStart(blockQuote);
                int length = markwonVisitor.length();
                markwonVisitor.visitChildren(blockQuote);
                markwonVisitor.setSpansForNodeOptional((MarkwonVisitor) blockQuote, length);
                markwonVisitor.blockEnd(blockQuote);
            }
        });
    }

    private static void code(@NonNull MarkwonVisitor.Builder builder) {
        builder.on(Code.class, new MarkwonVisitor.NodeVisitor<Code>() { // from class: io.noties.markwon.core.CorePlugin.5
            @Override // io.noties.markwon.MarkwonVisitor.NodeVisitor
            public void visit(@NonNull MarkwonVisitor markwonVisitor, @NonNull Code code) {
                int length = markwonVisitor.length();
                markwonVisitor.builder().append((char) 160).append(code.getLiteral()).append((char) 160);
                markwonVisitor.setSpansForNodeOptional((MarkwonVisitor) code, length);
            }
        });
    }

    private static void fencedCodeBlock(@NonNull MarkwonVisitor.Builder builder) {
        builder.on(FencedCodeBlock.class, new MarkwonVisitor.NodeVisitor<FencedCodeBlock>() { // from class: io.noties.markwon.core.CorePlugin.6
            @Override // io.noties.markwon.MarkwonVisitor.NodeVisitor
            public void visit(@NonNull MarkwonVisitor markwonVisitor, @NonNull FencedCodeBlock fencedCodeBlock) {
                CorePlugin.visitCodeBlock(markwonVisitor, fencedCodeBlock.getInfo(), fencedCodeBlock.getLiteral(), fencedCodeBlock);
            }
        });
    }

    private static void indentedCodeBlock(@NonNull MarkwonVisitor.Builder builder) {
        builder.on(IndentedCodeBlock.class, new MarkwonVisitor.NodeVisitor<IndentedCodeBlock>() { // from class: io.noties.markwon.core.CorePlugin.7
            @Override // io.noties.markwon.MarkwonVisitor.NodeVisitor
            public void visit(@NonNull MarkwonVisitor markwonVisitor, @NonNull IndentedCodeBlock indentedCodeBlock) {
                CorePlugin.visitCodeBlock(markwonVisitor, null, indentedCodeBlock.getLiteral(), indentedCodeBlock);
            }
        });
    }

    private static void image(MarkwonVisitor.Builder builder) {
        builder.on(Image.class, new MarkwonVisitor.NodeVisitor<Image>() { // from class: io.noties.markwon.core.CorePlugin.8
            @Override // io.noties.markwon.MarkwonVisitor.NodeVisitor
            public void visit(@NonNull MarkwonVisitor markwonVisitor, @NonNull Image image) {
                SpanFactory spanFactory = markwonVisitor.configuration().spansFactory().get(Image.class);
                if (spanFactory == null) {
                    markwonVisitor.visitChildren(image);
                    return;
                }
                int length = markwonVisitor.length();
                markwonVisitor.visitChildren(image);
                if (length == markwonVisitor.length()) {
                    markwonVisitor.builder().append((char) 65532);
                }
                MarkwonConfiguration configuration = markwonVisitor.configuration();
                String process = configuration.imageDestinationProcessor().process(image.getDestination());
                RenderProps renderProps = markwonVisitor.renderProps();
                ImageProps.DESTINATION.set(renderProps, process);
                ImageProps.REPLACEMENT_TEXT_IS_LINK.set(renderProps, Boolean.valueOf(image.getParent() instanceof Link));
                ImageProps.IMAGE_SIZE.set(renderProps, null);
                markwonVisitor.setSpans(length, spanFactory.getSpans(configuration, renderProps));
            }
        });
    }

    @VisibleForTesting
    static void visitCodeBlock(@NonNull MarkwonVisitor markwonVisitor, @Nullable String str, @NonNull String str2, @NonNull Node node) {
        markwonVisitor.blockStart(node);
        int length = markwonVisitor.length();
        markwonVisitor.builder().append((char) 160).append('\n').append(markwonVisitor.configuration().syntaxHighlight().highlight(str, str2));
        markwonVisitor.ensureNewLine();
        markwonVisitor.builder().append((char) 160);
        CoreProps.CODE_BLOCK_INFO.set(markwonVisitor.renderProps(), str);
        markwonVisitor.setSpansForNodeOptional((MarkwonVisitor) node, length);
        markwonVisitor.blockEnd(node);
    }

    private static void bulletList(@NonNull MarkwonVisitor.Builder builder) {
        builder.on(BulletList.class, new SimpleBlockNodeVisitor());
    }

    private static void orderedList(@NonNull MarkwonVisitor.Builder builder) {
        builder.on(OrderedList.class, new SimpleBlockNodeVisitor());
    }

    private static void listItem(@NonNull MarkwonVisitor.Builder builder) {
        builder.on(ListItem.class, new MarkwonVisitor.NodeVisitor<ListItem>() { // from class: io.noties.markwon.core.CorePlugin.9
            @Override // io.noties.markwon.MarkwonVisitor.NodeVisitor
            public void visit(@NonNull MarkwonVisitor markwonVisitor, @NonNull ListItem listItem) {
                int length = markwonVisitor.length();
                markwonVisitor.visitChildren(listItem);
                Block parent = listItem.getParent();
                if (parent instanceof OrderedList) {
                    OrderedList orderedList = (OrderedList) parent;
                    int startNumber = orderedList.getStartNumber();
                    CoreProps.LIST_ITEM_TYPE.set(markwonVisitor.renderProps(), CoreProps.ListItemType.ORDERED);
                    CoreProps.ORDERED_LIST_ITEM_NUMBER.set(markwonVisitor.renderProps(), Integer.valueOf(startNumber));
                    orderedList.setStartNumber(orderedList.getStartNumber() + 1);
                } else {
                    CoreProps.LIST_ITEM_TYPE.set(markwonVisitor.renderProps(), CoreProps.ListItemType.BULLET);
                    CoreProps.BULLET_LIST_ITEM_LEVEL.set(markwonVisitor.renderProps(), Integer.valueOf(CorePlugin.listLevel(listItem)));
                }
                markwonVisitor.setSpansForNodeOptional((MarkwonVisitor) listItem, length);
                if (markwonVisitor.hasNext(listItem)) {
                    markwonVisitor.ensureNewLine();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int listLevel(@NonNull Node node) {
        int i = 0;
        for (Node parent = node.getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof ListItem) {
                i++;
            }
        }
        return i;
    }

    private static void thematicBreak(@NonNull MarkwonVisitor.Builder builder) {
        builder.on(ThematicBreak.class, new MarkwonVisitor.NodeVisitor<ThematicBreak>() { // from class: io.noties.markwon.core.CorePlugin.10
            @Override // io.noties.markwon.MarkwonVisitor.NodeVisitor
            public void visit(@NonNull MarkwonVisitor markwonVisitor, @NonNull ThematicBreak thematicBreak) {
                markwonVisitor.blockStart(thematicBreak);
                int length = markwonVisitor.length();
                markwonVisitor.builder().append((char) 160);
                markwonVisitor.setSpansForNodeOptional((MarkwonVisitor) thematicBreak, length);
                markwonVisitor.blockEnd(thematicBreak);
            }
        });
    }

    private static void heading(@NonNull MarkwonVisitor.Builder builder) {
        builder.on(Heading.class, new MarkwonVisitor.NodeVisitor<Heading>() { // from class: io.noties.markwon.core.CorePlugin.11
            @Override // io.noties.markwon.MarkwonVisitor.NodeVisitor
            public void visit(@NonNull MarkwonVisitor markwonVisitor, @NonNull Heading heading) {
                markwonVisitor.blockStart(heading);
                int length = markwonVisitor.length();
                markwonVisitor.visitChildren(heading);
                CoreProps.HEADING_LEVEL.set(markwonVisitor.renderProps(), Integer.valueOf(heading.getLevel()));
                markwonVisitor.setSpansForNodeOptional((MarkwonVisitor) heading, length);
                markwonVisitor.blockEnd(heading);
            }
        });
    }

    private static void softLineBreak(@NonNull MarkwonVisitor.Builder builder) {
        builder.on(SoftLineBreak.class, new MarkwonVisitor.NodeVisitor<SoftLineBreak>() { // from class: io.noties.markwon.core.CorePlugin.12
            @Override // io.noties.markwon.MarkwonVisitor.NodeVisitor
            public void visit(@NonNull MarkwonVisitor markwonVisitor, @NonNull SoftLineBreak softLineBreak) {
                markwonVisitor.builder().append(' ');
            }
        });
    }

    private static void hardLineBreak(@NonNull MarkwonVisitor.Builder builder) {
        builder.on(HardLineBreak.class, new MarkwonVisitor.NodeVisitor<HardLineBreak>() { // from class: io.noties.markwon.core.CorePlugin.13
            @Override // io.noties.markwon.MarkwonVisitor.NodeVisitor
            public void visit(@NonNull MarkwonVisitor markwonVisitor, @NonNull HardLineBreak hardLineBreak) {
                markwonVisitor.ensureNewLine();
            }
        });
    }

    private static void paragraph(@NonNull MarkwonVisitor.Builder builder) {
        builder.on(Paragraph.class, new MarkwonVisitor.NodeVisitor<Paragraph>() { // from class: io.noties.markwon.core.CorePlugin.14
            @Override // io.noties.markwon.MarkwonVisitor.NodeVisitor
            public void visit(@NonNull MarkwonVisitor markwonVisitor, @NonNull Paragraph paragraph) {
                boolean isInTightList = CorePlugin.isInTightList(paragraph);
                if (!isInTightList) {
                    markwonVisitor.blockStart(paragraph);
                }
                int length = markwonVisitor.length();
                markwonVisitor.visitChildren(paragraph);
                CoreProps.PARAGRAPH_IS_IN_TIGHT_LIST.set(markwonVisitor.renderProps(), Boolean.valueOf(isInTightList));
                markwonVisitor.setSpansForNodeOptional((MarkwonVisitor) paragraph, length);
                if (isInTightList) {
                    return;
                }
                markwonVisitor.blockEnd(paragraph);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isInTightList(@NonNull Paragraph paragraph) {
        Block parent = paragraph.getParent();
        if (parent != null) {
            Node parent2 = parent.getParent();
            if (parent2 instanceof ListBlock) {
                return ((ListBlock) parent2).isTight();
            }
            return false;
        }
        return false;
    }

    private static void link(@NonNull MarkwonVisitor.Builder builder) {
        builder.on(Link.class, new MarkwonVisitor.NodeVisitor<Link>() { // from class: io.noties.markwon.core.CorePlugin.15
            @Override // io.noties.markwon.MarkwonVisitor.NodeVisitor
            public void visit(@NonNull MarkwonVisitor markwonVisitor, @NonNull Link link) {
                int length = markwonVisitor.length();
                markwonVisitor.visitChildren(link);
                CoreProps.LINK_DESTINATION.set(markwonVisitor.renderProps(), link.getDestination());
                markwonVisitor.setSpansForNodeOptional((MarkwonVisitor) link, length);
            }
        });
    }
}
