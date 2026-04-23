package org.commonmark.internal.renderer.text;

import org.commonmark.node.OrderedList;
/* loaded from: classes2.dex */
public class OrderedListHolder extends ListHolder {
    private int counter;
    private final char delimiter;

    public OrderedListHolder(ListHolder parent, OrderedList list) {
        super(parent);
        this.delimiter = list.getDelimiter();
        this.counter = list.getStartNumber();
    }

    public char getDelimiter() {
        return this.delimiter;
    }

    public int getCounter() {
        return this.counter;
    }

    public void increaseCounter() {
        this.counter++;
    }
}
