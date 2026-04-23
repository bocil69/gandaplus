package org.commonmark.internal.inline;

import org.commonmark.node.Node;
/* loaded from: classes2.dex */
public abstract class ParsedInline {
    public static ParsedInline none() {
        return null;
    }

    public static ParsedInline of(Node node, Position position) {
        if (node == null) {
            throw new NullPointerException("node must not be null");
        }
        if (position == null) {
            throw new NullPointerException("position must not be null");
        }
        return new ParsedInlineImpl(node, position);
    }
}
