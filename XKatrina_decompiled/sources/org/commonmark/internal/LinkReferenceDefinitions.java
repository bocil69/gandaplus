package org.commonmark.internal;

import java.util.LinkedHashMap;
import java.util.Map;
import org.commonmark.internal.util.Escaping;
import org.commonmark.node.LinkReferenceDefinition;
/* loaded from: classes2.dex */
public class LinkReferenceDefinitions {
    private final Map<String, LinkReferenceDefinition> definitions = new LinkedHashMap();

    public void add(LinkReferenceDefinition definition) {
        String normalizedLabel = Escaping.normalizeLabelContent(definition.getLabel());
        if (!this.definitions.containsKey(normalizedLabel)) {
            this.definitions.put(normalizedLabel, definition);
        }
    }

    public LinkReferenceDefinition get(String label) {
        String normalizedLabel = Escaping.normalizeLabelContent(label);
        return this.definitions.get(normalizedLabel);
    }
}
