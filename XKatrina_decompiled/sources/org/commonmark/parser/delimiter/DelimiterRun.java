package org.commonmark.parser.delimiter;

import org.commonmark.node.Text;
/* loaded from: classes2.dex */
public interface DelimiterRun {
    boolean canClose();

    boolean canOpen();

    Text getCloser();

    Iterable<Text> getClosers(int i);

    Text getOpener();

    Iterable<Text> getOpeners(int i);

    int length();

    int originalLength();
}
