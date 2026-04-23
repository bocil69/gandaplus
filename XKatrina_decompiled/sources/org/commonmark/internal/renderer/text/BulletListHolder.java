package org.commonmark.internal.renderer.text;

import org.commonmark.node.BulletList;
/* loaded from: classes2.dex */
public class BulletListHolder extends ListHolder {
    private final char marker;

    public BulletListHolder(ListHolder parent, BulletList list) {
        super(parent);
        this.marker = list.getBulletMarker();
    }

    public char getMarker() {
        return this.marker;
    }
}
