package io.noties.markwon.utils;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.commonmark.node.Node;
import org.commonmark.node.Visitor;
/* loaded from: classes2.dex */
public abstract class DumpNodes {

    /* loaded from: classes2.dex */
    public interface NodeProcessor {
        @NonNull
        String process(@NonNull Node node);
    }

    @NonNull
    @CheckResult
    public static String dump(@NonNull Node node) {
        return dump(node, null);
    }

    @NonNull
    @CheckResult
    public static String dump(@NonNull Node node, @Nullable final NodeProcessor nodeProcessor) {
        if (nodeProcessor == null) {
            nodeProcessor = new NodeProcessorToString();
        }
        final Indent indent = new Indent();
        final StringBuilder sb = new StringBuilder();
        node.accept((Visitor) Proxy.newProxyInstance(Visitor.class.getClassLoader(), new Class[]{Visitor.class}, new InvocationHandler() { // from class: io.noties.markwon.utils.DumpNodes.1
            @Override // java.lang.reflect.InvocationHandler
            public Object invoke(Object obj, Method method, Object[] objArr) {
                Node node2 = (Node) objArr[0];
                Indent.this.appendTo(sb);
                sb.append(nodeProcessor.process(node2));
                if (node2.getFirstChild() != null) {
                    sb.append(" [\n");
                    Indent.this.increment();
                    DumpNodes.visitChildren((Visitor) obj, node2);
                    Indent.this.decrement();
                    Indent.this.appendTo(sb);
                    sb.append("]\n");
                    return null;
                }
                sb.append("\n");
                return null;
            }
        }));
        return sb.toString();
    }

    private DumpNodes() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class Indent {
        private int count;

        private Indent() {
        }

        void increment() {
            this.count++;
        }

        void decrement() {
            this.count--;
        }

        void appendTo(@NonNull StringBuilder sb) {
            for (int i = 0; i < this.count; i++) {
                sb.append(' ');
                sb.append(' ');
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void visitChildren(@NonNull Visitor visitor, @NonNull Node node) {
        Node firstChild = node.getFirstChild();
        while (firstChild != null) {
            Node next = firstChild.getNext();
            firstChild.accept(visitor);
            firstChild = next;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class NodeProcessorToString implements NodeProcessor {
        private NodeProcessorToString() {
        }

        @Override // io.noties.markwon.utils.DumpNodes.NodeProcessor
        @NonNull
        public String process(@NonNull Node node) {
            return node.toString();
        }
    }
}
