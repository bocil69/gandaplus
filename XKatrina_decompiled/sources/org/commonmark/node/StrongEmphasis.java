package org.commonmark.node;
/* loaded from: classes2.dex */
public class StrongEmphasis extends Node implements Delimited {
    private String delimiter;

    public StrongEmphasis() {
    }

    public StrongEmphasis(String delimiter) {
        this.delimiter = delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    @Override // org.commonmark.node.Delimited
    public String getOpeningDelimiter() {
        return this.delimiter;
    }

    @Override // org.commonmark.node.Delimited
    public String getClosingDelimiter() {
        return this.delimiter;
    }

    @Override // org.commonmark.node.Node
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
