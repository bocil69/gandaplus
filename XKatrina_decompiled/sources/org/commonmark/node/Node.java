package org.commonmark.node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes2.dex */
public abstract class Node {
    private Node parent = null;
    private Node firstChild = null;
    private Node lastChild = null;
    private Node prev = null;
    private Node next = null;
    private List<SourceSpan> sourceSpans = null;

    public abstract void accept(Visitor visitor);

    public Node getNext() {
        return this.next;
    }

    public Node getPrevious() {
        return this.prev;
    }

    public Node getFirstChild() {
        return this.firstChild;
    }

    public Node getLastChild() {
        return this.lastChild;
    }

    public Node getParent() {
        return this.parent;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void appendChild(Node child) {
        child.unlink();
        child.setParent(this);
        if (this.lastChild != null) {
            this.lastChild.next = child;
            child.prev = this.lastChild;
            this.lastChild = child;
            return;
        }
        this.firstChild = child;
        this.lastChild = child;
    }

    public void prependChild(Node child) {
        child.unlink();
        child.setParent(this);
        if (this.firstChild != null) {
            this.firstChild.prev = child;
            child.next = this.firstChild;
            this.firstChild = child;
            return;
        }
        this.firstChild = child;
        this.lastChild = child;
    }

    public void unlink() {
        if (this.prev != null) {
            this.prev.next = this.next;
        } else if (this.parent != null) {
            this.parent.firstChild = this.next;
        }
        if (this.next != null) {
            this.next.prev = this.prev;
        } else if (this.parent != null) {
            this.parent.lastChild = this.prev;
        }
        this.parent = null;
        this.next = null;
        this.prev = null;
    }

    public void insertAfter(Node sibling) {
        sibling.unlink();
        sibling.next = this.next;
        if (sibling.next != null) {
            sibling.next.prev = sibling;
        }
        sibling.prev = this;
        this.next = sibling;
        sibling.parent = this.parent;
        if (sibling.next == null) {
            sibling.parent.lastChild = sibling;
        }
    }

    public void insertBefore(Node sibling) {
        sibling.unlink();
        sibling.prev = this.prev;
        if (sibling.prev != null) {
            sibling.prev.next = sibling;
        }
        sibling.next = this;
        this.prev = sibling;
        sibling.parent = this.parent;
        if (sibling.prev == null) {
            sibling.parent.firstChild = sibling;
        }
    }

    public List<SourceSpan> getSourceSpans() {
        return this.sourceSpans != null ? Collections.unmodifiableList(this.sourceSpans) : Collections.emptyList();
    }

    public void setSourceSpans(List<SourceSpan> sourceSpans) {
        if (sourceSpans.isEmpty()) {
            this.sourceSpans = null;
        } else {
            this.sourceSpans = new ArrayList(sourceSpans);
        }
    }

    public void addSourceSpan(SourceSpan sourceSpan) {
        if (this.sourceSpans == null) {
            this.sourceSpans = new ArrayList();
        }
        this.sourceSpans.add(sourceSpan);
    }

    public String toString() {
        return getClass().getSimpleName() + "{" + toStringAttributes() + "}";
    }

    protected String toStringAttributes() {
        return "";
    }
}
