package org.commonmark.node;

import java.util.Iterator;
/* loaded from: classes2.dex */
public class Nodes {
    private Nodes() {
    }

    public static Iterable<Node> between(Node start, Node end) {
        return new NodeIterable(start.getNext(), end);
    }

    /* loaded from: classes2.dex */
    private static class NodeIterable implements Iterable<Node> {
        private final Node end;
        private final Node first;

        private NodeIterable(Node first, Node end) {
            this.first = first;
            this.end = end;
        }

        @Override // java.lang.Iterable
        public Iterator<Node> iterator() {
            return new NodeIterator(this.first, this.end);
        }
    }

    /* loaded from: classes2.dex */
    private static class NodeIterator implements Iterator<Node> {
        private final Node end;
        private Node node;

        private NodeIterator(Node first, Node end) {
            this.node = first;
            this.end = end;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return (this.node == null || this.node == this.end) ? false : true;
        }

        @Override // java.util.Iterator
        public Node next() {
            Node result = this.node;
            this.node = this.node.getNext();
            return result;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }
}
