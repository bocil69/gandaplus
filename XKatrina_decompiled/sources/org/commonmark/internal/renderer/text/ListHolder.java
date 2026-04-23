package org.commonmark.internal.renderer.text;
/* loaded from: classes2.dex */
public abstract class ListHolder {
    private static final String INDENT_DEFAULT = "   ";
    private static final String INDENT_EMPTY = "";
    private final String indent;
    private final ListHolder parent;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ListHolder(ListHolder parent) {
        this.parent = parent;
        if (parent != null) {
            this.indent = parent.indent + INDENT_DEFAULT;
        } else {
            this.indent = "";
        }
    }

    public ListHolder getParent() {
        return this.parent;
    }

    public String getIndent() {
        return this.indent;
    }
}
