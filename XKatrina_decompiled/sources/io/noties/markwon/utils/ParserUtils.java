package io.noties.markwon.utils;

import androidx.annotation.NonNull;
import org.commonmark.node.Node;
/* loaded from: classes2.dex */
public abstract class ParserUtils {
    public static void moveChildren(@NonNull Node node, @NonNull Node node2) {
        Node next = node2.getNext();
        while (next != null) {
            Node next2 = next.getNext();
            node.appendChild(next);
            next = next2;
        }
    }

    private ParserUtils() {
    }
}
