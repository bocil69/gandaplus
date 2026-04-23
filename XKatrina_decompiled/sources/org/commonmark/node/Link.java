package org.commonmark.node;
/* loaded from: classes2.dex */
public class Link extends Node {
    private String destination;
    private String title;

    public Link() {
    }

    public Link(String destination, String title) {
        this.destination = destination;
        this.title = title;
    }

    @Override // org.commonmark.node.Node
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getDestination() {
        return this.destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override // org.commonmark.node.Node
    protected String toStringAttributes() {
        return "destination=" + this.destination + ", title=" + this.title;
    }
}
