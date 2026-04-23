package org.commonmark.internal;

import org.commonmark.internal.inline.Position;
import org.commonmark.node.Text;
/* loaded from: classes2.dex */
public class Bracket {
    public boolean allowed = true;
    public boolean bracketAfter = false;
    public final Position contentPosition;
    public final boolean image;
    public final Position markerPosition;
    public final Text node;
    public final Bracket previous;
    public final Delimiter previousDelimiter;

    public static Bracket link(Text node, Position markerPosition, Position contentPosition, Bracket previous, Delimiter previousDelimiter) {
        return new Bracket(node, markerPosition, contentPosition, previous, previousDelimiter, false);
    }

    public static Bracket image(Text node, Position markerPosition, Position contentPosition, Bracket previous, Delimiter previousDelimiter) {
        return new Bracket(node, markerPosition, contentPosition, previous, previousDelimiter, true);
    }

    private Bracket(Text node, Position markerPosition, Position contentPosition, Bracket previous, Delimiter previousDelimiter, boolean image) {
        this.node = node;
        this.markerPosition = markerPosition;
        this.contentPosition = contentPosition;
        this.image = image;
        this.previous = previous;
        this.previousDelimiter = previousDelimiter;
    }
}
