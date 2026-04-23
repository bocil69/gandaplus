package org.commonmark.node;
/* loaded from: classes2.dex */
public class FencedCodeBlock extends Block {
    private char fenceChar;
    private int fenceIndent;
    private int fenceLength;
    private String info;
    private String literal;

    @Override // org.commonmark.node.Node
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public char getFenceChar() {
        return this.fenceChar;
    }

    public void setFenceChar(char fenceChar) {
        this.fenceChar = fenceChar;
    }

    public int getFenceLength() {
        return this.fenceLength;
    }

    public void setFenceLength(int fenceLength) {
        this.fenceLength = fenceLength;
    }

    public int getFenceIndent() {
        return this.fenceIndent;
    }

    public void setFenceIndent(int fenceIndent) {
        this.fenceIndent = fenceIndent;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLiteral() {
        return this.literal;
    }

    public void setLiteral(String literal) {
        this.literal = literal;
    }
}
